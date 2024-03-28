package com.example.cbtapp.goals;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cbtapp.R;

public class GoalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        findViewById(R.id.layout).post(() -> NotificationHelper.NotificationSetterPopup(getApplicationContext(), findViewById(R.id.layout)));
    }
}