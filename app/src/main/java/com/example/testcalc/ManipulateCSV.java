package com.example.testcalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.*;
import java.io.*;
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

    public static void sortCsvFileByDate(File csvFile) throws IOException {
        //Read data from CSV file
        List<String[]> rows = new ArrayList<>();

    }

    public void testSorting() {
        List<Integer> numbers = Arrays.asList(5, 3, 2, 4, 1);
        List<Integer> sortedList = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedList);
    }

}
