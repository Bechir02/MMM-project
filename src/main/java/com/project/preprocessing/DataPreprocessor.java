package com.project.preprocessing;

import java.util.List;

public class DataPreprocessor {
    private String missingValueStrategy;

    public DataPreprocessor(String missingValueStrategy) {
        this.missingValueStrategy = missingValueStrategy;
    }

    public void handleMissingValues(List<Double> data) {
        if (missingValueStrategy.equals("mean")) {
            double mean = calculateMean(data);
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i) == null) {
                    data.set(i, mean);
                }
            }
        }
        // Add other strategies like median, mode, etc.
    }

    public void normalizeData(List<Double> data) {
        double max = data.stream().max(Double::compare).orElse(1.0);
        double min = data.stream().min(Double::compare).orElse(0.0);
        for (int i = 0; i < data.size(); i++) {
            data.set(i, (data.get(i) - min) / (max - min));
        }
    }

    private double calculateMean(List<Double> data) {
        return data.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
}