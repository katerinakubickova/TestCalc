package com.example.testcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PortionCalculatorLinear extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portion_calculator_linear);
        EditText editTextWeightBrutto = findViewById(R.id.pc_edittext_brutto);
        EditText editTextWeightPot = findViewById(R.id.pc_edittext_pot);
        EditText editTextCount = findViewById(R.id.pc_edittext_count);
        Button buttonHome = findViewById(R.id.button_home);
        Button buttonCalculate = findViewById(R.id.button_calculate);
        TextView textViewWeightFood = findViewById(R.id.pc_res_value_brutto);
        TextView textViewMyPortion = findViewById(R.id.pc_res_value_kaca);
        TextView textViewMartinPortion = findViewById(R.id.pc_res_value_marta);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PortionCalculatorLinear.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float weightSum = Float.parseFloat(String.valueOf(editTextWeightBrutto.getText()));
                float weightPot = Float.parseFloat(String.valueOf(editTextWeightPot.getText()));
                float countPortion = Float.parseFloat(String.valueOf(editTextCount.getText()));
                float weightFood = weightSum - weightPot;
                float myPortion = weightFood / 10 * 4 / countPortion;
                float martinPortion = weightFood / 10 * 6 / countPortion;
                textViewWeightFood.setText(String.valueOf(weightFood));
                textViewMyPortion.setText(String.valueOf(myPortion));
                textViewMartinPortion.setText(String.valueOf(martinPortion));
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception ignored) {
                }
            }
        });
    }
}