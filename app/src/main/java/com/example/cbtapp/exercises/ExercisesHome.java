package com.example.cbtapp.exercises;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.cbtapp.HomeActivity;
import com.example.cbtapp.NavBar;
import com.example.cbtapp.R;
import com.example.cbtapp.SwipeListener;
import com.example.cbtapp.activityLog.CalenderLog;
import com.example.cbtapp.exercises.problemsolvingExercise.ProblemActivity;
import com.example.cbtapp.exercises.situationExercise.SituationActivity;
import com.example.cbtapp.exercises.thoughtrecordExercise.ThoughtRecordActivity;
import com.example.cbtapp.stats.StatsPage;

public class ExercisesHome extends AppCompatActivity {
    SwipeListener swipeListener;
    LinearLayout navBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_home);

        Button situationButton = findViewById(R.id.button5);
        situationButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SituationActivity.class);
            startActivity(intent);
        });

        Button recordButton = findViewById(R.id.button7);
        recordButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ThoughtRecordActivity.class);
            startActivity(intent);
        });

        Button problemButton = findViewById(R.id.button6);
        problemButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ProblemActivity.class);
            startActivity(intent);
        });

        navBar = findViewById(R.id.navBar);
        NavBar.setUpNavbar(this, navBar);
        swipeListener = new SwipeListener(findViewById(R.id.layout), navBar);
    }
}