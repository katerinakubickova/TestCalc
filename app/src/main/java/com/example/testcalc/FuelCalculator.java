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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FuelCalculator extends AppCompatActivity {

    private EditText editTextValueDate;
    private EditText editTextValueOdo;
    private EditText editTextValueAmount;
    private EditText editTextValueMj;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_calculator);
        Button buttonHome = findViewById(R.id.button_home);
        Button buttonDate = findViewById(R.id.button_date);
        Button buttonSpocitat = findViewById(R.id.button_calculate);

        editTextValueDate = findViewById(R.id.value_date);
        editTextValueOdo = findViewById(R.id.value_odo);
        editTextValueAmount = findViewById(R.id.value_amount);
        editTextValueMj = findViewById(R.id.value_mj);

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

        buttonSpocitat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(this);
            }
        });

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
            File file = new File(dir, "data.csv");
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

    private void openDialog() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                editTextValueDate.setText(String.valueOf(i)+"."+String.valueOf(i1)+"."+String.valueOf(i2));

            }
        }, 2023, 5, 10);
        dialog.show();
    }

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

}