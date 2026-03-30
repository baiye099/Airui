import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = scanner.next();
        System.out.println("请输入密码");
        String password = scanner.next();
        try (Connection connection = JdbcUtil.open()) {
            //安全写SQL参数，金庸字符串拼接SQL语句，改用？作为占位符
            String sql = "select *from user where username=?";
            //预编译SQL语句，返回预编译的执行对象（替代普通的执行对象）
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //用变量username替代sql语句中第一个？参数
            //这种先预编译再填充的方式可以防止SQL注入攻击
            //攻击者输入的任何内容都不会识别位SQL代码，只会识别为普通字符串
            //而拼接的方式，可能会让输入的内容识别为SQL代码
            //sql语句中有几个？参数，这里就用ps.setObject填几次
            preparedStatement.setObject(1, username);

            //执行SQL语句
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString("password").equals(DigestUtils.md5Hex(password))) {
                    //数据库存储的用户密码比对用户输入的密码
                    System.out.println("登录成功");
                } else {
                    System.out.println("密码错误");
                }
            } else {
                //查不到这个用户
                System.out.println("用户名不存在");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
