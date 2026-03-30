import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo06 {
    //合法角色
    static final String[] roles = {"teacher", "student"};
//尽量不要让数据库去做数据的合法性校验，让应用层（Java程序）

    //注册一个用户
    //user_id自增长，不用填
    //username检查是否重复，如果重复，禁止注册
    //password密码是敏感数据，加密后再写入数据库（安装用于加密的中间件）
    //real_name 允许为空，注册时可不填，更新时再填
    //role角色
    //status用户状态，默认1
    //create_time用当前时间
    //update_time注册时为空，更新时指定
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scanner.next();
        while (exists(username)) {
            System.out.println(" 用户名已存在，请重新输入");
            username = scanner.next();
        }
        System.out.println("输入密码");
        String password = scanner.next();
        while (!passwordValid(password)) {
            System.out.println(" 密码长度不得低于5位");
            password = scanner.next();
        }
        System.out.println("请输入角色（teacher|student）");
        String role = scanner.next();
        while (!roleValid(role)){
            System.out.println("角色不合法，请重新输入");
            role=scanner.next();
        }
        try (Connection connection = JdbcUtil.open()) {
            //插入注册用户的必填字段
            String sql = "insert into user(username,password,role)values(?,?,?)";
            //预编译SQL语句
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //填充sql语句中的所有？参数
            preparedStatement.setObject(1, username);
            //DigestUtils.md5Hex(password)返回md5加密后的字符串
            preparedStatement.setObject(2, DigestUtils.md5Hex(password));
            preparedStatement.setObject(3, role);
            //执行sql语句
            //如果执行DDL查询语句，用ps.executeQuery()返回参数
            //如果执行DML查询语句，用ps.executeQuery()返回受影响行数
            int rows = preparedStatement.executeUpdate();
            //返回受影响行数可接受可不接受，只要没发生异常就可以当作插入成功
//            preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("注册成功！");
            }
        } catch (SQLException e) {
            System.out.println("注册失败！");
            e.printStackTrace();
        }
    }

    /**
     * 检查用过户是否已存在
     *
     * @param username用户名（数据库唯一）
     * @return 是否已存在
     */
    static boolean exists(String username) {
        try (Connection connection = JdbcUtil.open()) {
            //根据用户名查有没有这一行
            String sql = "select 1 from user where username =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            //需要去除查询数据，返回有没有这一行
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    static boolean passwordValid(String password) {
        return password.length() >= 5;
    }

    static boolean roleValid(String role) {
        for (int i = 0; i < roles.length; i++) {
            if (roles[i].equals(role)) {
                return true;
            }
        }
        return false;
    }


}
