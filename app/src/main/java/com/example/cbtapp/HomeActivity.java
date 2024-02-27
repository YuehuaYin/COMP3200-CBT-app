package com.example.cbtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.cbtapp.exercises.ExercisesHome;

public class HomeActivity extends AppCompatActivity {
    SwipeListener swipeListener;
    LinearLayout navBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navBar = findViewById(R.id.navBar);

        Button homeButton = navBar.findViewById(R.id.navHome);
        homeButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });

        Button activityButton = navBar.findViewById(R.id.navExercises);
        activityButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ExercisesHome.class);
            startActivity(intent);
        });

        swipeListener = new SwipeListener(findViewById(R.id.layout), navBar);
    }
}