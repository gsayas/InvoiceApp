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

    public CSVReader(String filePath) {
        csvFile = filePath;
        cvsSplitBy = ",";
        lines = new ArrayList<>();
    }

    public void loadFile() {
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                lines.add(line);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumRows() {
        return lines.size();
    }

    public int getNumCols() {
        return 0;
    }

}
