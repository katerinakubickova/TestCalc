package com.example.testcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testcalc.databinding.ActivityMainBinding;

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

        ManipulateCSV manipulateCSV = new ManipulateCSV();

        manipulateCSV.testSorting();

    }

}