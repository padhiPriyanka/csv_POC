package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HDGFile {
    private static final String FILE_NAME = "HDG_invout_HDG-162_20220526_053119033.csv";

    private static final String OUTPUT_FILE = "output.csv";

    public static void main(String[] args) {
        System.out.println("Reading csv file");
        readAll(FILE_NAME,OUTPUT_FILE);
    }

    //TO read data from csv file
    private static void readAll(String inputFile,String outputFile) {
        String searchTerm = "DIREC02";

        List<String[]> data = new ArrayList<>();
        try {

            FileReader fileReader = new FileReader(inputFile);

            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();

            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData) {
                for (String cell : row) {
                    if (cell.equals(searchTerm)) {

                        System.out.println(Arrays.toString(row));

                        data.add(row);
                    }
                }
            }
            writeInCsv(outputFile,data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //To write data into csv file

    private static void writeInCsv(String file, List<String[]> list) {

        try {
            FileWriter fw = new FileWriter(file);
            CSVWriter csvWriter = new CSVWriter(fw);

            List<String[]> op = list;

            String[] header = {"VenCode","InvNum","InvDate","FiscalYear","FiscalMonth","TransactionAmount","ID1099Amount","InvoiceAmount","Description","AccountNum","LineDescription"};
            csvWriter.writeNext(header);
            csvWriter.writeAll(op);
            csvWriter.flush();
            csvWriter.close();

            System.out.println("Data entered into csv file");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

