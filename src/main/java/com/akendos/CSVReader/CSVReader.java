package com.akendos.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private String csvFile;
    private String cvsSplitBy;
    private List<String> lines;
    private ArrayList<String[]> columns;
    private String[] columnTitles;

    public CSVReader(String filePath) {
        csvFile = filePath;
        cvsSplitBy = ",";
        lines = new ArrayList<>();
    }

    public void loadFile() {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseColumns() {
        columnTitles = lines.get(0).split(cvsSplitBy);
        columns = new ArrayList<String[]>();

        for (int i = 1; i < lines.size(); i++) {
            columns.add(lines.get(i).split(cvsSplitBy));
        }

    }

    public String getField(int rowIndex, int colIndex) {
        if(rowIndex >= 0 && colIndex >= 0 && rowIndex < getNumRows() && colIndex < getNumCols()){
            return columns.get(rowIndex)[colIndex];
        }else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getNumRows() {
        return lines.size();
    }

    public int getNumCols() {
        return columns.size();
    }

}
