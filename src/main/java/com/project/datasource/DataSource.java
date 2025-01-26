package com.project.datasource;

public abstract class DataSource {
    protected String sourceName;
    protected String filePath;

    public abstract void readData();
    public abstract void validateData();
}