package com.example.cbtapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cbtapp.activityLog.CalenderLog;
import com.example.cbtapp.exercises.ExercisesHome;
import com.example.cbtapp.stats.Stats;
import com.example.cbtapp.stats.StatsPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class HomeActivity extends AppCompatActivity {
    SwipeListener swipeListener;
    LinearLayout navBar;
    Button claimDaily;
    TextView currentPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navBar = findViewById(R.id.navBar);
        NavBar.setUpNavbar(this, navBar);
        swipeListener = new SwipeListener(findViewById(R.id.layout), navBar);

        File path = getApplicationContext().getFilesDir();
        path.mkdirs();

        try {
            Stats.readStats(new File(path, "StatsFile.txt"));
        } catch (IOException e){
            try {
                Stats.resetStats();
                FileOutputStream writer = openFileOutput("StatsFile.txt", MODE_PRIVATE);
                Stats.writeStats(writer);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }


        currentPoints = findViewById(R.id.currentpoints);
        updateCurrentPoints();

        claimDaily = findViewById(R.id.dailybonus);
        claimDaily.setOnClickListener(view -> {
            Stats.addPoints(10);
            try {
                Stats.resetStats();
                Stats.claimDaily();
                updateDailyButton();
                FileOutputStream writer = openFileOutput("StatsFile.txt", MODE_PRIVATE);
                Stats.writeStats(writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        /**
        File activityLog = new File(path, "ActivityLog.xml");
        try {
            log = XMLParser.importXmlFile(activityLog);
            rootElement = log.getDocumentElement();
        } catch (Exception e){
            try {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.newDocument();
                XMLParser.exportXmlFile(doc, activityLog);
            } catch (Exception e2){
                e2.printStackTrace();
            }
            e.printStackTrace();
        }
         **/
    }

    void updateDailyButton(){
        if (Stats.dailyBonusClaimed == 1) {
            claimDaily.setEnabled(false);
            claimDaily.setTextColor(Color.GRAY);
        }
    }

    void updateCurrentPoints(){
        currentPoints.setText("Points: " + Stats.currentPoints);
    }

}