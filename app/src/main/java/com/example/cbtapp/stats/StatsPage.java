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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StatsPage extends AppCompatActivity {

    SwipeListener swipeListener;
    LinearLayout navBar;
    TextView levelText;
    TextView pointsText;
    TextView exercisesText;
    TextView tasksText;
    TextView currentStreakText;
    TextView highestStreakText;
    TextView collectableText;
    Integer collectableNum;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_page);

        levelText = findViewById(R.id.textView24);
        pointsText = findViewById(R.id.textView28);
        exercisesText = findViewById(R.id.textView29);
        tasksText = findViewById(R.id.textView30);
        currentStreakText = findViewById(R.id.textView31);
        highestStreakText = findViewById(R.id.textView32);
        collectableText = findViewById(R.id.textView23);

        sp = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        editor = sp.edit();

        collectableNum = getcollectableprogress();

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
            Stats.writeStats(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        collectableNum = 0;
        updateStats();

        List<String> spNameList = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            spNameList = Arrays.asList("collect1", "collect2", "collect3", "collect4", "collect5", "collect6", "collect7", "collect8", "collect9", LocalDate.now().toString());
        }
        for (String n:spNameList) {
            editor.putBoolean(n, false);
        }
        editor.putInt("repeats", 0);
        editor.commit();

        DbCmd.deleteAllLogs(this);
    }

    Integer getcollectableprogress(){
        Boolean collect1 = sp.getBoolean("collect1", false);
        Boolean collect2 = sp.getBoolean("collect2", false);
        Boolean collect3 = sp.getBoolean("collect3", false);
        Boolean collect4 = sp.getBoolean("collect4", false);
        Boolean collect5 = sp.getBoolean("collect5", false);
        Boolean collect6 = sp.getBoolean("collect6", false);
        Boolean collect7 = sp.getBoolean("collect7", false);
        Boolean collect8 = sp.getBoolean("collect8", false);
        Boolean collect9 = sp.getBoolean("collect9", false);

        List<Boolean> boolList = Arrays.asList(collect1, collect2, collect3, collect4, collect5, collect6, collect7, collect8, collect9);
        return Collections.frequency(boolList,true);
    }

    void updateStats(){
        levelText.setText("Current level: " + Stats.level);
        pointsText.setText("Current points: " + Stats.currentPoints);
        exercisesText.setText("CBT exercises completed: " + Stats.exercisesDone);
        tasksText.setText("Tasks completed: " + Stats.tasksCompleted);
        currentStreakText.setText("Current streak: " + Stats.currentStreak);
        highestStreakText.setText("Highest streak: " + Stats.highestStreak);
        collectableText.setText("Collectable progress: " + collectableNum + "/9");
    }

}