package com.example.sef_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        String DB_url = "jdbc:mysql://localhost:3306/sef";
        String DB_user = "root";
        String DB_password = "";
        try {
            connection = DriverManager.getConnection(DB_url, DB_user, DB_password);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
