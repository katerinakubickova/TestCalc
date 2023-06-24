package com.example.testcalc;

import android.annotation.SuppressLint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CsvRow {
    private String date;
    private String name;
    private String address;
    private String agreement;

    public CsvRow(String date, String name, String address, String agreement) {
        this.date = date;
        this.name = name;
        this.address = address;
        this.agreement = agreement;

    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public String getAgreement() {
        return agreement;
    }

    public String getName() {
        return name;
    }

    static List<CsvRow> readCsv(String filePath) throws IOException {

        List<CsvRow> rows = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(",");
            CsvRow row = new CsvRow(columns[0], columns[1], columns[2], columns[3]);
            rows.add(row);
        }
        reader.close();
        return rows;
    }

    public static void sortCsvByDate(List<CsvRow> rows) {
        rows.sort(Comparator.comparing(CsvRow::getDate));
    }

    private static void writeCsv(List<CsvRow> rows, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (CsvRow row : rows) {
            writer.write(row.getDate() + "," + row.getName() + "," + row.getAddress() + "," + row.getAgreement());

        }
        writer.close();
    }


}
