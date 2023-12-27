package com.vishadi.fitify2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SelectionActivity extends AppCompatActivity {

    private RadioGroup dietRadioGroup;
    private RadioGroup weightRadioGroup;
    private Button selectButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        dietRadioGroup = findViewById(R.id.dietRadioGroup);
        weightRadioGroup = findViewById(R.id.weightRadioGroup);
        selectButton = findViewById(R.id.selectButton);



        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOptions();
            }
        });
    }

    private void selectOptions() {
        int selectedDietId = dietRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedDietRadioButton = findViewById(selectedDietId);
        String dietOption = selectedDietRadioButton.getText().toString();

        int selectedWeightId = weightRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedWeightRadioButton = findViewById(selectedWeightId);
        String weightCategory = selectedWeightRadioButton.getText().toString();

        Intent intent;
        if (dietOption.equals("Vegetarian") && weightCategory.equals("Overweight")) {
            intent = new Intent(SelectionActivity.this, VegetarianOverWeightActivity.class);
        } else if (dietOption.equals("Non-vegetarian") && weightCategory.equals("Underweight")) {
            intent = new Intent(SelectionActivity.this, CategoryActivity.class);
        } else {
            intent = new Intent(SelectionActivity.this, OtherActivity.class);
        }

        startActivity(intent);
    }

}
