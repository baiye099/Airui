import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo01 {

    private static final String url = "jdbc:mysql://192.168.231.131/jdbc_student_system?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";//数据库连接地址
    private static final String driver = "com.mysql.cj.jdbc.Driver";//数据库驱动路径
    private static final String username = "root";//数据库用户名
    private static final String password = "123456";//数据库密码

    public static void main(String[] args) {
        try {
            //1.加载驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载失败，请检查是否集成了数据包");
            e.printStackTrace();
            e.printStackTrace();
        }
        //2.使用驱动管理器建立数据库连接,并使用connection存储连接对象
        //这一步的底层基于TCP协议，三次握手建立客户端与服务端的socket连接
        try (Connection connection=DriverManager.getConnection(url,username,password);){
            System.out.println("数据库连接成功");

        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }


    }

}
