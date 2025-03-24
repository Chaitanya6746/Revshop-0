	package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/revshop_database";
    private static final String username = "root";
    private static final String password = "chaitu@02";

    static {
        try {
            System.out.println("Loading MySQL JDBC Driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        if (connection != null) {
            System.out.println("Database connected successfully!");
        }
        return connection;
    }
}
