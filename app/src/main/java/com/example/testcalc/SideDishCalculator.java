package com.example.testcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SideDishCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_dish_calculator);
        EditText editTextWeightWholeDry = findViewById(R.id.sdc_edittext_whole_dry);
        EditText editTextWeightOneDry = findViewById(R.id.sdc_edittext_one_dry);
        EditText editTextWeightBrutto = findViewById(R.id.sdc_edittext_brutto);
        EditText editTextWeightPot = findViewById(R.id.sdc_edittext_weight_pot);
        Button buttonHome = findViewById(R.id.button_home);
        Button buttonCalculate = findViewById(R.id.button_calculate);
        TextView textViewResBrutto = findViewById(R.id.sdc_res_value_brutto);
        TextView textViewResOneBoiled = findViewById(R.id.sdc_res_value_one_said_boiled);
        TextView textViewResResidue = findViewById(R.id.sdc_res_value_residue);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SideDishCalculator.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float weightWholeDry = Float.parseFloat(String.valueOf(editTextWeightWholeDry.getText()));
                float weightOneDry = Float.parseFloat(String.valueOf(editTextWeightOneDry.getText()));
                float weightBrutto = Float.parseFloat(String.valueOf(editTextWeightBrutto.getText()));
                float weightPot = Float.parseFloat(String.valueOf(editTextWeightPot.getText()));
                float weightWholeBoiled = Calculate.calculateFood(weightBrutto, weightPot);
                float weightOneBoiled = Calculate.calculateOneSideDish(weightWholeBoiled, weightWholeDry, weightOneDry);
                float weightResidue = Calculate.calculateSideDishResidue(weightWholeBoiled, weightOneBoiled);
                textViewResBrutto.setText(String.valueOf(weightWholeBoiled));
                textViewResOneBoiled.setText(String.valueOf(weightOneBoiled));
                textViewResResidue.setText(String.valueOf(weightResidue));
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception ignored) {

                }
            }
        });
    }
}