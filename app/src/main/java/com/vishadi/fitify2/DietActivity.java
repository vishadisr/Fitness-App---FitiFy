package com.vishadi.fitify2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DietActivity extends AppCompatActivity {

    private EditText weight_text, height_text;
    private TextView textView;
    private Button btn_calculate,btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        weight_text = findViewById(R.id.bmi_weight);
        height_text = findViewById(R.id.bmi_height);
        btn_calculate = findViewById(R.id.button_cal);
        btn_next = findViewById(R.id.next);
        textView = findViewById(R.id.result);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectionActivity();

            }
        });

    }
    private void calculateBMI() {
        double weight = Double.parseDouble(weight_text.getText().toString());
        double height = Double.parseDouble(height_text.getText().toString());

        double bmi = weight / (height * height);

        String interpretation;
        if (bmi < 18.5) {
            interpretation = "Underweight";
        } else if (bmi < 25) {
            interpretation = "Normal weight";
        } else if (bmi < 30) {
            interpretation = "Overweight";
        } else {
            interpretation = "Obese";
        }

        String result = "BMI: " + bmi + "\nInterpretation: " + interpretation;
        textView.setText(result);
    }

    public void openSelectionActivity() {
        Intent intent = new Intent(this, SelectionActivity.class);
        startActivity(intent);
    }

}