package com.vishadi.fitify2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class WaterActivity extends AppCompatActivity {
    private EditText weightEditText;
    private TextView waterIntakeTextView;
    private Button calculateButton;
    private Button reminderButton;
    private boolean isReminderEnabled = false;
    private double dailyWaterIntake = 0;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        weightEditText = findViewById(R.id.weight_editText);
        waterIntakeTextView = findViewById(R.id.waterIntakeTextView);
        calculateButton = findViewById(R.id.calculateButton);
        reminderButton = findViewById(R.id.reminderButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateWaterIntake();
            }
        });

        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleReminder();
            }
        });
    }

    private void calculateWaterIntake() {
        String weightString = weightEditText.getText().toString().trim();

        if (!weightString.isEmpty()) {
            double weight = Double.parseDouble(weightString);
            dailyWaterIntake = weight * 0.033; // Adjust the factor as per your needs

            waterIntakeTextView.setText(String.format("%.2f", dailyWaterIntake));

            if (isReminderEnabled) {
                startReminder();
            }
        }
    }

    private void toggleReminder() {
        isReminderEnabled = !isReminderEnabled;

        if (isReminderEnabled) {
            if (dailyWaterIntake > 0) {
                startReminder();
                reminderButton.setText("Disable Reminder");
                Toast.makeText(this, "Reminder Set", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please calculate water intake first!", Toast.LENGTH_SHORT).show();
            }
        } else {
            stopReminder();
            reminderButton.setText("Enable Reminder");
            Toast.makeText(this, "Reminder disabled!", Toast.LENGTH_SHORT).show();
        }
    }

    private void startReminder() {
        Intent intent = new Intent(this, ReminderReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MINUTE, 1);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1 * 60 * 1000, pendingIntent);
    }

    private void stopReminder() {
        if (alarmManager != null && pendingIntent != null) {
            alarmManager.cancel(pendingIntent);
        }
    }


}
