package com.project.controller;

import java.util.List;

import com.project.analysis.StatisticalAnalysis;
import com.project.datasource.DataSource;
import com.project.preprocessing.DataPreprocessor;
import com.project.visualization.Visualization;

public class Controller {
    private final String userId;

    public Controller(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty.");
        }
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void uploadData(DataSource dataSource) {
        if (dataSource == null) {
            throw new IllegalArgumentException("Data source cannot be null.");
        }
        dataSource.readData();
        dataSource.validateData();
    }

    public void runAnalysis(List<Double> data, String regressionModel) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data cannot be null or empty.");
        }
        if (regressionModel == null || regressionModel.trim().isEmpty()) {
            throw new IllegalArgumentException("Regression model cannot be null or empty.");
        }

        DataPreprocessor preprocessor = new DataPreprocessor("mean");
        preprocessor.handleMissingValues(data);
        preprocessor.normalizeData(data);

        StatisticalAnalysis analysis = new StatisticalAnalysis(data, regressionModel);
        analysis.calculateMean();
        analysis.performRegression();
    }

    public void viewReports() {
        try {
            Visualization visualization = new Visualization("Sales Data");
            visualization.generateBarChart();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to generate visualization: " + e.getMessage(), e);
        }
    }
}