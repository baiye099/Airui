import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo03 {
    public static void main(String[] args) {
        try (Connection connection = JdbcUtil.open()) {
            String sql = "select*from class";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int classId = resultSet.getInt("class_id");
                String className = resultSet.getString("class_name");
                String classGrade = resultSet.getString("class_Grade");
                String major = resultSet.getString("major");
                String creatTime = resultSet.getString("create_time");
                String updateTime = resultSet.getString("update_time");
                System.out.println(String.format("%d\t%s\t%s\t%s\t%s\t%s", classId, className, classGrade, major, creatTime, updateTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
