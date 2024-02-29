package com.example.cbtapp.activityLog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.cbtapp.HomeActivity;
import com.example.cbtapp.R;
import com.example.cbtapp.SwipeListener;
import com.example.cbtapp.exercises.ExercisesHome;
import com.example.cbtapp.stats.StatsPage;

public class CalenderLog extends AppCompatActivity {
    SwipeListener swipeListener;
    LinearLayout navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_log);
    }

    void setUpNavbar(){
        Button homeButton = navBar.findViewById(R.id.navHome);
        homeButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });

        Button exercisesButton = navBar.findViewById(R.id.navExercises);
        exercisesButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ExercisesHome.class);
            startActivity(intent);
        });

        Button statsButton = navBar.findViewById(R.id.navStats);
        statsButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, StatsPage.class);
            startActivity(intent);
        });

        Button activityButton = navBar.findViewById(R.id.navActivity);
        activityButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, CalenderLog.class);
            startActivity(intent);
        });

        swipeListener = new SwipeListener(findViewById(R.id.layout), navBar);
    }
}