package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.List;

public class OpenCsv {

    private static final String FILE_NAME = "table_1.csv";

    public static void main(String[] args) {
        System.out.println("Reading csv file");
        readAll(FILE_NAME);
    }

    private static void readAll(String file) {
        try {

            FileReader fileReader = new FileReader(file);

            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();

            List<String[]> allData = csvReader.readAll();

            for (String[] row :allData) {
                for (String cell:row) {
                    System.out.print(cell +"\t");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
