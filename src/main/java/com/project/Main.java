package com.project;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.database.DatabaseHandler;

public class Main {
    public static void main(String[] args) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        // Insert data into the database
        dbHandler.storeData("INSERT INTO sales (channel, spend, sales) VALUES ('TV', 1000.00, 5000.00)");

        // Retrieve data from the database
        try (ResultSet resultSet = dbHandler.retrieveData("SELECT * FROM sales")) {
            while (resultSet.next()) {
                System.out.println("Channel: " + resultSet.getString("channel") +
                                   ", Spend: " + resultSet.getDouble("spend") +
                                   ", Sales: " + resultSet.getDouble("sales"));
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }
}