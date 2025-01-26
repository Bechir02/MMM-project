package com.project.datasource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader extends DataSource {
    private char delimiter;

    public CSVReader(String filePath, char delimiter) {
        this.filePath = filePath;
        this.delimiter = delimiter;
    }

    @Override
    public void readData() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(String.valueOf(delimiter));
                // Process the data
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validateData() {
        // Implement data validation logic
    }
}