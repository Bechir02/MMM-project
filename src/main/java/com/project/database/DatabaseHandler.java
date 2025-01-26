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
    private final String username = "root";
    private final String password = "yourpassword"; // Replace with your MySQL root password

    public void storeData(String query) {
        try (Connection conn = DriverManager.getConnection(databaseURL, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error storing data: {0}", e.getMessage());
        }
    }

    public ResultSet retrieveData(String query) {
        try (Connection conn = DriverManager.getConnection(databaseURL, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            return pstmt.executeQuery();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving data: {0}", e.getMessage());
            return null;
        }
    }
}