package com.example.cbtapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cbtapp.collectables.CollectableRoll;
import com.example.cbtapp.exercises.TipDialog;
import com.example.cbtapp.stats.Stats;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class HomeActivity extends AppCompatActivity {
    SwipeListener swipeListener;
    LinearLayout navBar;
    Button claimDaily;
    TextView currentPoints, currentLevel, currentStreak;
    Boolean dailyBonusClaimed;
    ProgressBar progressCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navBar = findViewById(R.id.navBar);
        NavBar.setUpNavbar(this, navBar);
        swipeListener = new SwipeListener(findViewById(R.id.layout), navBar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                if (extras.getBoolean("levelup", false)) {
                    gachatime();
                }
            }
        }

        File path = getApplicationContext().getFilesDir();
        path.mkdirs();

        try {
            Stats.readStats(new File(path, "StatsFile.txt"));
        } catch (Exception e){
            try {
                Stats.resetStats();
                Stats.writeStats(this);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        currentLevel = findViewById(R.id.currentlevel);
        updateCurrentLevel();

        currentPoints = findViewById(R.id.currentpoints);
        progressCircle = findViewById(R.id.progressBar2);
        updateCurrentPoints();

        SharedPreferences sp = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        // check if logged in yesterday: if not, reset streak to 0 else maintain
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            System.out.println(LocalDate.now().toString());
            dailyBonusClaimed = sp.getBoolean(LocalDate.now().toString(), false);
            LocalDate yesterday = LocalDate.now().minusDays(1);
            if (sp.getBoolean(yesterday.toString(), false)) {
                Stats.resetStreak();
                editor.putBoolean(yesterday.toString(), true);
            }
        }

        currentStreak = findViewById(R.id.textView20);
        updateCurrentStreak();

        claimDaily = findViewById(R.id.dailybonus);
        updateDailyButton();
        claimDaily.setOnClickListener(view -> {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                editor.putBoolean(LocalDate.now().toString(), true);
                editor.commit();
            }
            dailyBonusClaimed = true;

            Stats.addStreak();
            if (Stats.addPoints(10)){
                gachatime();
            }

            try {
                Stats.writeStats(this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            updateCurrentStreak();
            updateDailyButton();
            updateCurrentLevel();
            updateCurrentPoints();
        });

        Button infoButton = findViewById(R.id.button14);
        infoButton.setOnClickListener(v -> showAppInfo());
    }

    private void showAppInfo() {
        TipDialog tipDialog = new TipDialog();
        tipDialog.setTitle("Information:");
        tipDialog.setMessage("placeholder text");
        tipDialog.show(getSupportFragmentManager(), "tip dialog");
    }

    void updateCurrentStreak() {
        currentStreak.setText("Current streak: " + Stats.currentStreak);
    }

    void updateDailyButton(){
        if (dailyBonusClaimed) {
            claimDaily.setEnabled(false);
            claimDaily.setTextColor(Color.GRAY);
        }
    }

    void gachatime(){
        findViewById(R.id.layout).post(() -> CollectableRoll.GACHATIME(getApplicationContext(), findViewById(R.id.layout), "Level up! You got:"));
    }

    void updateCurrentPoints(){
        currentPoints.setText(Stats.currentPoints + "/100");
        progressCircle.setProgress(Stats.currentPoints);
    }

    void updateCurrentLevel(){
        currentLevel.setText("Level " + Stats.level);
    }

}