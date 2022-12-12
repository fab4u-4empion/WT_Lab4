package databseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    public static String URL = "jdbc:postgresql://localhost:5432/";

    public static String DATABASE_NAME = "wt4_database";

    public static String USERNAME = "postgres";

    public static String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        Connection connection;
        Properties properties = new Properties();
        properties.setProperty("user", USERNAME);
        properties.setProperty("password", PASSWORD);

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(URL+DATABASE_NAME, properties);
        return connection;
    }
}
