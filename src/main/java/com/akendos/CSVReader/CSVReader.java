package com.akendos.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {

    private String csvFile;
    private String cvsSplitBy;
    private List<String> lines;
    private List<String[]> cells;
    private List<String> columnTitles;

    public CSVReader(String filePath) {
        csvFile = filePath;
        cvsSplitBy = ",";
        lines = new ArrayList<>();
    }

    public void loadAndParseFile(){
        loadFile();
        parseCells();
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

    public void parseCells() {
        columnTitles = new ArrayList<String>(Arrays.asList(lines.get(0).split(cvsSplitBy)));//TODO: extract method
        List<String[]> auxCells = new ArrayList<String[]>();
        cells = new ArrayList<String[]>();

        for (int i = 1; i < lines.size(); i++) {
            auxCells.add(lines.get(i).split(cvsSplitBy));
        }

        for (int j = 0; j < getNumCols() ; j++) {
            cells.add(new String[getNumRows()]);
            for (int i = 0; i < getNumRows() ; i++) {
                cells.get(j)[i] = auxCells.get(i)[j];
            }
        }

    }

    public String getField(int rowIndex, int colIndex) {
        if(rowIndex >= 0 && colIndex >= 0 && rowIndex < getNumRows() && colIndex < getNumCols()){
            return cells.get(colIndex)[rowIndex];
        }else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getNumRows() {
        return lines.size()-1;
    }

    public int getNumCols() {
        return columnTitles.size();
    }

    public String printColumnTitles() {
        String columns = "";

        for (int i = 0; i < getNumCols(); i++) {
            columns += columnTitles.get(i);
            if(i < getNumCols()-1){
                columns += cvsSplitBy;
            }
        }

        return columns;
    }

    public boolean deleteColumnByTitle(String columnTitle) {
        int columnIndex = columnTitles.indexOf(columnTitle);
        cells.remove(columnIndex);
        columnTitles.remove(columnIndex);

        return columnIndex != -1;
    }
}
