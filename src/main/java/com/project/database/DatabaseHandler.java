package com.project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHandler {
    private static final Logger LOGGER = Logger.getLogger(DatabaseHandler.class.getName());
    private final String databaseURL = "jdbc:mysql://localhost:3306/marketing_db";
    private final String username = "root"; // Replace with your MySQL username
    private final String password = "becher0123645"; // Replace with your MySQL password

    public void storeData(String query) {
        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(databaseURL, username, password);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error storing data: {0}", e.getMessage());
        }
    }

    public ResultSet retrieveData(String query) {
        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(databaseURL, username, password);
            PreparedStatement pstmt = conn.prepareStatement(query);
            return pstmt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving data: {0}", e.getMessage());
            return null;
        }
    }
}