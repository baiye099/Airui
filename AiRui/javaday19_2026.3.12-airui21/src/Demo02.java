import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo02 {
    public static void main(String[] args) {
        //每执行一个数据库操作都需要连接数据库，操作完成后要关掉数据库连接
        //1.创建数据库连接
        //新式写法不需要自己手动关闭写法
        try (Connection connection = JdbcUtil.open()) {
            //2.创建sql语句
            String sql = "select * from student";//这是Java程序和数据库服务之间的对话，Java会将代码发送给数据库执行
            //3.创建执行对象，预编译SQL语句(底层是一个输出流)
            Statement statement = connection.createStatement();
            //4.执行SQL语句（用输出流把sql语句传输给数据库服务器）
            //并使用ResultSet类接收数据库服务器返回的查询结果
            ResultSet resultSet = statement.executeQuery(sql);
            //5.操作数据库返回的结果
            while (resultSet.next()){
                int studentId=resultSet.getInt("student_id");
                String studentNo=resultSet.getString("student_no");
                String name=resultSet.getString("name");
                String gender=resultSet.getString("gender");
                String birthday=resultSet.getString("birthday");
                System.out.println(String.format("%d\t%s\t%s\t%s\t%s\n",studentId,studentNo,name,gender,birthday));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
       /*//老式try-catch写法，需要自己手动关掉
        Connection connection = JdbcUtil.open();
        try {
            connection = JdbcUtil.open();
        } catch (Exception e) {

        } finally {
            JdbcUtil.close(connection);
        }*/


    }
}
