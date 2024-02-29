package com.example.cbtapp.stats;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cbtapp.HomeActivity;
import com.example.cbtapp.R;
import com.example.cbtapp.SwipeListener;
import com.example.cbtapp.activityLog.CalenderLog;
import com.example.cbtapp.activityLog.XMLParser;
import com.example.cbtapp.exercises.ExercisesHome;

import org.w3c.dom.Document;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;

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

        setUpNavbar();

        Button resetButton = findViewById(R.id.button12);
        resetButton.setOnClickListener(view -> resetDataButton());
    }

    void resetDataButton(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Are you sure?");
        builder.setMessage("This will delete all stats and journal entries.");

        builder.setPositiveButton("Yes", (dialogInterface, i) -> resetData());

        builder.setNegativeButton("No", (dialogInterface, i) -> {});

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void resetData(){
        File path = getApplicationContext().getFilesDir();

        Stats.readStats(new File(path, "StatsFile.txt"));
        try {
            Stats.resetStats();
            FileOutputStream writer = openFileOutput("StatsFile.txt", MODE_PRIVATE);
            Stats.writeStats(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File activityLog = new File(path, "ActivityLog.xml");
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            XMLParser.exportXmlFile(doc, activityLog);
        } catch (Exception e){
            e.printStackTrace();
        }

        updateStats();
    }

    void updateStats(){
        pointsText.setText("Points: " + Stats.points);
        exercisesText.setText("CBT exercises completed: " + Stats.exercisesDone);
        tasksText.setText("Tasks completed: " + Stats.tasksCompleted);
        currentStreakText.setText("Current streak: " + Stats.currentStreak);
        highestStreakText.setText("Highest streak: " + Stats.highestStreak);
    }

    void setUpNavbar(){
        navBar = findViewById(R.id.navBar);

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