package com.example.testcalc;

import static com.example.testcalc.CsvRow.readCsv;
import static com.example.testcalc.DataManipulator.sdf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testcalc.databinding.ActivityMainBinding;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPortions = findViewById(R.id.button_portions);
        Button buttonFuel = findViewById(R.id.button_fuel);
        Button buttonSideDish = findViewById(R.id.button_side_dish);
        buttonPortions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PortionCalculatorLinear.class);
                startActivity(intent);
            }
        });

        buttonFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FuelCalculator.class);
                startActivity(intent);
            }
        });

        buttonSideDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SideDishCalculator.class);
                startActivity(intent);
            }
        });

        DataManipulator dataManipulator = new DataManipulator();
        dataManipulator.testSorting();

        List<String> dateStrings = Arrays.asList("2023-06-21", "2021-01-01", "2022-01-23");
        List<Date> sortedDates = null;
        try {
            sortedDates = dataManipulator.sortDates(dateStrings);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for (Date date : sortedDates) {
            System.out.println(sdf.format(date));
        }

        ManipulateCSV manipulateCSV = new ManipulateCSV();
        manipulateCSV.CSVManipulator("/data/data/com.example.testcalc/files/test-csv-adress.csv");
        try {
            manipulateCSV.readAndSortData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }

        dataManipulator.dateFormating("2023.6.2");
        dataManipulator.formatingDate("2023.7.1");

    }


}