package org.example;

import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String FILE_NAME = "HDG_invout_HDG-162_20220526_053119033.csv";
    static List<String[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("Reading csv file");
        readData(FILE_NAME);
        writeDataInCSV();
    }

    private static List readData(String file) {
        String serachTerm = "DIREC02";
        List<String[]> content = new ArrayList<>();
//        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";

            while ((line = br.readLine()) != null) {
                content.add(line.split(","));
            }

            for (String[] data : content) {
                for (String values : data) {
                    if (values.equals(serachTerm)) {

                        list.add(data);
                        System.out.println(Arrays.toString(data));

                    }
                }
            }
//            System.out.println(list.size());
//            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void writeDataInCSV() throws IOException {

        File file = new File("output.csv");
        try {
            FileWriter fileWriter = new FileWriter(file);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            List<String[]> op = new ArrayList<>();
            op = list;
//            System.out.println(op);
//        BufferedWriter bw = new BufferedWriter(fw);
//        bw.write("VenCode,InvNum,InvDate,FiscalYear,FiscalMonth,TransactionAmount,ID1099Amount,InvoiceAmount,Description,AccountNum,LineDescription");

            String[] header = {"VenCode","InvNum","InvDate","FiscalYear","FiscalMonth","TransactionAmount","ID1099Amount","InvoiceAmount","Description","AccountNum","LineDescription"};
            csvWriter.writeNext(header);
            csvWriter.writeAll(op);
            csvWriter.close();

            System.out.println("Data entered into csv file");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}