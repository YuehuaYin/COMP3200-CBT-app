package com.example.cbtapp.stats;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cbtapp.NavBar;
import com.example.cbtapp.R;
import com.example.cbtapp.SwipeListener;
import com.example.cbtapp.activityLog.DbCmd;
import com.example.cbtapp.collectables.CollectablesActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class StatsPage extends AppCompatActivity {

    SwipeListener swipeListener;
    LinearLayout navBar;
    TextView pointsText;
    TextView exercisesText;
    TextView tasksText;
    TextView currentStreakText;
    TextView highestStreakText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_page);

        pointsText = findViewById(R.id.textView28);
        exercisesText = findViewById(R.id.textView29);
        tasksText = findViewById(R.id.textView30);
        currentStreakText = findViewById(R.id.textView31);
        highestStreakText = findViewById(R.id.textView32);

        updateStats();

        navBar = findViewById(R.id.navBar);
        NavBar.setUpNavbar(this, navBar);
        swipeListener = new SwipeListener(findViewById(R.id.layout), navBar);

        Button resetButton = findViewById(R.id.button12);
        resetButton.setOnClickListener(view -> resetDataButton());
    }

    void resetDataButton(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Are you sure?");
        builder.setMessage("This will delete all data in the app.");

        builder.setPositiveButton("Yes", (dialogInterface, i) -> resetData());

        builder.setNegativeButton("No", (dialogInterface, i) -> {});

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void resetData() {
        try {
            Stats.resetStats();
            FileOutputStream writer = openFileOutput("StatsFile.txt", MODE_PRIVATE);
            Stats.writeStats(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateStats();

        SharedPreferences sp = getSharedPreferences("CollectableProgress", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        List<String> spNameList = Arrays.asList("collect1", "collect2", "collect3", "collect4", "collect5", "collect6", "collect7", "collect8", "collect9");
        for (String n:spNameList) {
            editor.putBoolean(n, false);
        }
        editor.putInt("repeats", 0);

        DbCmd.deleteAllLogs(this);
    }

    void updateStats(){
        pointsText.setText("Current level: " + Stats.level);
        exercisesText.setText("Current points: " + Stats.currentPoints);
        tasksText.setText("CBT exercises completed: " + Stats.exercisesDone);
        // "Tasks completed: " + Stats.tasksCompleted
        currentStreakText.setText("Current streak: " + Stats.currentStreak);
        highestStreakText.setText("Highest streak: " + Stats.highestStreak);
        // "Collectable progress: " +
    }

}