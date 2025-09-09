import java.sql.*;

public class DBUtil {
    private static final String URL = "root@localhost:3306";
    private static final String USER = "root";  // change to your MySQL username
    private static final String PASSWORD = "newunkownp@22";  // change to your MySQL password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
