package workshop.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DBService {
    private static String DB_URL = "jdbc:mysql://localhost:3306/";
    private static String DB_PARAMS = "?useSSL=false&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String DB_USER = "root";
    private static String DB_PASS = "coderslab";

    public static Connection getConnection(String db) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return DriverManager.getConnection(DB_URL + db + DB_PARAMS, DB_USER, DB_PASS);
    }

    public static void delete(Integer id, String db, String query) throws SQLException{
        try (Connection connection = DBService.getConnection(db);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cos sie nie powiodło");
        }
    }
}
