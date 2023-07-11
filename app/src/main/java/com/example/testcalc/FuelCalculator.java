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

import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class FuelCalculator extends AppCompatActivity {

    private EditText editTextValueDate;
    private EditText editTextValueOdo;
    private EditText editTextValueAmount;
    private EditText editTextValueMj;
    Context context;
    Calendar calendar = Calendar.getInstance();
    String fileName = "TestCSVFile.csv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_calculator);
        Button buttonHome = findViewById(R.id.button_home);
        Button buttonDate = findViewById(R.id.button_date);
        Button buttonCalculate = findViewById(R.id.button_calculate);
        Button buttonSave = findViewById(R.id.button_save);

        editTextValueDate = findViewById(R.id.value_date);
        editTextValueOdo = findViewById(R.id.value_odo);
        editTextValueAmount = findViewById(R.id.value_amount);
        editTextValueMj = findViewById(R.id.value_mj);

        DataManipulator dataManipulator = new DataManipulator();


        TextView textViewValueConsuptionShort = findViewById(R.id.valueConsuptionShort);

        double consuptionLong = calculateConsuptionLong();

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FuelCalculator.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView textViewValueConsuptionLong = findViewById(R.id.valueConsuptionLong);
                textViewValueConsuptionLong.setText(String.valueOf(consuptionLong));
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ManipulateCSV manipulateCSV = new ManipulateCSV();
                File filesDir = getFilesDir();
                manipulateCSV.CSVManipulator("/data/data/com.example.testcalc/files/TestCSVFile.csv");


                String date = editTextValueDate.getText().toString();
                String odo = editTextValueOdo.getText().toString();
                String amount = editTextValueAmount.getText().toString();
                String mj = editTextValueMj.getText().toString();

                System.out.println("Date1: " + date);

                String combinedData =  date + ',' + odo + ',' + amount + ',' + mj + '\n';
                System.out.println("Combined Data: " + combinedData);

                manipulateCSV.saveDataToCSV(combinedData, fileName, filesDir);
                try {
                    manipulateCSV.readAndSortData();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (CsvException e) {
                    throw new RuntimeException(e);
                }
                //saveData(this);
                    //ManipulateCSV.saveDataToCSV(file, data);
                }
        });

    }

    private void writeToFile(String combinedData) {
        try {
            File csvFile = new File(getFilesDir(), fileName);
            FileWriter writer = new FileWriter(csvFile, true);

            writer.append(combinedData);

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveData(View.OnClickListener view) {
        String date = editTextValueDate.getText().toString();
        String odo = editTextValueOdo.getText().toString();
        String amount = editTextValueAmount.getText().toString();
        String mj = editTextValueMj.getText().toString();

        String latestDate = getLatestDateFromCSV();

        if (date.compareTo(latestDate) <= 0) {
            return;
        } StringBuilder csvString = new StringBuilder();
        String csvData = date + ',' + odo + ',' + amount + ',' + mj + '\n';
        System.out.println(csvData);

        try {
            File dir = getApplicationContext().getFilesDir();
            System.out.println(dir);
            File file = new File(dir, fileName);
            FileWriter csvWriter = new FileWriter(file, true);
            csvWriter.append(csvData);
            csvWriter.flush();
            csvWriter.close();
            Toast.makeText(this, "Data saved succesfully" + dir, Toast.LENGTH_LONG).show();
            System.out.println("Data saved succesfully" + dir);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show();
            System.out.println("Failed to save data");
        }
    }

    public double calculateConsuptionLong() {
        try {
            File dir = getApplicationContext().getFilesDir();
            File file = new File(dir, fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            double oldestOdo = 0.0;
            double newestOdo = 0.0;
            int count = 0;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 2) {
                    double valueOdo = Double.parseDouble(fields[1]);

                    if (count == 0) {
                        oldestOdo = valueOdo;
                    }
                    newestOdo = valueOdo;
                    count++;
                }
            }
            reader.close();

            if (count > 2) {
                return newestOdo - oldestOdo;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } return 0.0;
    }

    private void openDialog() {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                editTextValueDate.setText(String.valueOf(i)+"."+String.valueOf(i1 +1)+"."+String.valueOf(i2));

            }
        }, year, month, day);
        dialog.show();
    }

    private String getLatestDateFromCSV() {
        String latestDate = "";

        try {
            File dir = getApplicationContext().getFilesDir();
            File file = new File(dir, fileName);
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

}