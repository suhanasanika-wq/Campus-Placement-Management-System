import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/campus_placement";

    private static final String USER = "root";

    private static final String PASSWORD = "mysql";

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Explicitly register the MySQL driver
            DriverManager.registerDriver(
                new com.mysql.cj.jdbc.Driver()
            );

        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {

        try {
            Connection connection = getConnection();

            System.out.println("Database connected successfully!");

            connection.close();

        } catch (SQLException e) {

            System.out.println("Database connection failed!");

            e.printStackTrace();
        }
    }
}