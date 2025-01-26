package com.project.model;

public interface MachineLearningModel {
    void trainModel();
    double[] predict(double[][] inputData);
}