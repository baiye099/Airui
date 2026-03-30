import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Demo04 {
    public static void main(String[] args) {
        // 模拟用户登录的程序
        Scanner s = new Scanner(System.in);
        System.out.println("输入用户名:");
        String username = s.next();
        System.out.println("输入密码");
        String password = s.next();

        try (Connection conn = JdbcUtil.open()) {
            // 这是一个带参数的sql语句, 后面拼接的username就是sql语句的参数
            // SQL 注入攻击, 攻击者输入一段SQL代码, 与程序中的SQL代码拼接在一起, 执行出不符合预期的效果
            String sql = "select * from user where username = '" + username + "'";
            System.out.println(sql);
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            // 这个结果集rs最多只有一行数据, 因为username是唯一的
            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    System.out.println("登录成功!");
                } else {
                    System.out.println("密码错误!");
                }
            } else {
                System.out.println("用户不存在!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
