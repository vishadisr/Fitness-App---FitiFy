package com.vishadi.fitify2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textView;
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private Boolean isTemparatureSensorAvailable;

    private Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        textView = findViewById(R.id.textView13);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {

            tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isTemparatureSensorAvailable = true;
        } else {
            textView.setText("Temperature Sensor is not available");
            isTemparatureSensorAvailable = false;
        }


        button = (Button) findViewById(R.id.buttonOut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openMapsActivity();
                }
        });

        button = (Button) findViewById(R.id.buttonStay);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashboardActivity();
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.values[0]<20) {
            textView.setText( event.values[0] +" °C \n"+
                    "Weather is not good for Exercise in outside!!!");
        } else if (event.values[0] >= 20 && event.values[0] <= 35) {
            textView.setText( event.values[0] +" °C \n"+
                    "weather is good for taking a walk or run outside!!!");
        }
        else {
            textView.setText( event.values[0] +" °C \n"+
                    "Weather is too hot to go outside!!!");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isTemparatureSensorAvailable) {
            sensorManager.registerListener(this,tempSensor, sensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isTemparatureSensorAvailable) {
            sensorManager.unregisterListener(this);
        }
    }

    public void openMapsActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void openDashboardActivity() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}