package com.project.datasource;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader extends DataSource {
    private String sheetName;

    public ExcelReader(String filePath, String sheetName) {
        this.filePath = filePath;
        this.sheetName = sheetName;
    }

    @Override
    public void readData() {
        try (FileInputStream file = new FileInputStream(filePath)) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet(sheetName);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    // Process the data
                }
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