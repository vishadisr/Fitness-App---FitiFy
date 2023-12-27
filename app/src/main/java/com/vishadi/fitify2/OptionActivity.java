package com.vishadi.fitify2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);



        button = (Button) findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMaleActivity();
            }
        });

        button = (Button) findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFemaleActivity();
            }
        });
    }

    public void openMaleActivity() {
        Intent intent = new Intent(this, MaleActivity.class);
        startActivity(intent);
    }

    public void openFemaleActivity() {
        Intent intent = new Intent(this, MaleActivity.class);
        startActivity(intent);
    }


}