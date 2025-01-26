package com.project.analysis;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StatisticalAnalysis {
    private final List<Double> data;
    private String regressionModel;

    public StatisticalAnalysis(List<Double> data, String regressionModel) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data cannot be null or empty.");
        }
        if (regressionModel == null || regressionModel.trim().isEmpty()) {
            throw new IllegalArgumentException("Regression model cannot be null or empty.");
        }
        this.data = List.copyOf(data);
        this.regressionModel = regressionModel;
    }

    public String getRegressionModel() {
        return regressionModel;
    }

    public void setRegressionModel(String regressionModel) {
        if (regressionModel == null || regressionModel.trim().isEmpty()) {
            throw new IllegalArgumentException("Regression model cannot be null or empty.");
        }
        this.regressionModel = regressionModel;
    }

    public double calculateMean() {
        return data.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElseThrow(() -> new IllegalStateException("Unable to calculate mean: data is empty."));
    }

    public double calculateMedian() {
        List<Double> sortedData = List.copyOf(data);
        Collections.sort(sortedData);
        int middle = sortedData.size() / 2;
        if (sortedData.size() % 2 == 1) {
            return sortedData.get(middle);
        } else {
            return (sortedData.get(middle - 1) + sortedData.get(middle)) / 2.0;
        }
    }

    public double calculateMode() {
        Map<Double, Integer> frequencyMap = new HashMap<>();
        for (Double value : data) {
            frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
        }

        double mode = 0.0;
        int maxFrequency = 0;
        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mode = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        if (maxFrequency == 1) {
            throw new IllegalStateException("No mode found: all values are unique.");
        }
        return mode;
    }

    public double performCorrelationAnalysis(List<Double> otherData) {
        Objects.requireNonNull(otherData, "Other data cannot be null.");
        if (data.size() != otherData.size()) {
            throw new IllegalArgumentException("Data sets must be of the same size for correlation analysis.");
        }

        double meanX = calculateMean();
        double meanY = otherData.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElseThrow(() -> new IllegalStateException("Unable to calculate mean: other data is empty."));

        double covariance = 0.0;
        double varianceX = 0.0;
        double varianceY = 0.0;

        for (int i = 0; i < data.size(); i++) {
            double diffX = data.get(i) - meanX;
            double diffY = otherData.get(i) - meanY;
            covariance += diffX * diffY;
            varianceX += diffX * diffX;
            varianceY += diffY * diffY;
        }

        if (varianceX == 0 || varianceY == 0) {
            return 0.0; // Avoid division by zero
        }

        return covariance / (Math.sqrt(varianceX) * Math.sqrt(varianceY));
    }

    public void performRegression() {
        switch (regressionModel.toLowerCase()) {
            case "linear" -> performLinearRegression();
            case "logistic" -> performLogisticRegression();
            case "polynomial" -> performPolynomialRegression();
            default -> throw new IllegalArgumentException("Unsupported regression model: " + regressionModel);
        }
    }

    private void performLinearRegression() {
        System.out.println("Performing linear regression...");
        // Add linear regression implementation here
    }

    private void performLogisticRegression() {
        System.out.println("Performing logistic regression...");
        // Add logistic regression implementation here
    }

    private void performPolynomialRegression() {
        System.out.println("Performing polynomial regression...");
        // Add polynomial regression implementation here
    }
}