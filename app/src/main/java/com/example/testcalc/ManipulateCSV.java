package com.example.testcalc;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ManipulateCSV extends AppCompatActivity {

    private Context applicationContext;

    private String getLatestDateFromCSV() {
        String latestDate = "";

        try {
            File dir = getApplicationContext().getFilesDir();
            File file = new File(dir, "data.csv");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0) {
                    String currentDate = fields[0];
                    if (currentDate.compareTo(latestDate) > 0) {
                        latestDate = currentDate;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return latestDate;
    }

    public Context getApplicationContext() {
        Context applicationContext = null;
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void saveDataToCSV(String combinedData, String fileName, File filesDir) {
        try {
            File csvFile = new File(filesDir, fileName);
            FileWriter writer = new FileWriter(csvFile, true);

            writer.append(combinedData);

            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private String filePath;
    private SimpleDateFormat dateFormat;
    public void CSVManipulator(String filePath) {
        this.filePath = filePath;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void readAndSortData() throws IOException, CsvException {
        //Read data from CSV file
        try {

            CSVReader reader = new CSVReader(new FileReader(filePath));
            List<String[]> data = reader.readAll();
            reader.close();

            //Exclude the header row before sorting
            List<String[]> header = data.stream().limit(1).collect(Collectors.toList());
            List<String[]> rows = data.stream().skip(1).collect(Collectors.toList());

            //Sort the rows based on the first column
            rows.sort(Comparator.comparing(row -> {
                Date date = null;
                try {
                    date = dateFormat.parse(row[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                    date = new Date(Long.MIN_VALUE);
                }
                return date;
            }));

            //Include the header row again after sorting
            rows.addAll(0, header);

            //Write the sorted data back to CSV and don't add doublequotes to the every records
            CSVWriter writer = new CSVWriter(new FileWriter(filePath), CSVWriter.DEFAULT_SEPARATOR, '\0', CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(rows);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
