package com.example.cbtapp.stats;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cbtapp.FurtherSupport;
import com.example.cbtapp.HomeActivity;
import com.example.cbtapp.NavBar;
import com.example.cbtapp.R;
import com.example.cbtapp.SwipeListener;
import com.example.cbtapp.activityLog.CalenderLog;
import com.example.cbtapp.activityLog.XMLParser;
import com.example.cbtapp.exercises.ExercisesHome;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

        navBar = findViewById(R.id.navBar);
        NavBar.setUpNavbar(this, navBar);
        swipeListener = new SwipeListener(findViewById(R.id.layout), navBar);

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
            Element rootElement = doc.createElement("log");
            doc.appendChild(rootElement);
            Element testElement = doc.createElement("Date");
            testElement.setAttribute("date", "29/02/24");
            rootElement.appendChild(testElement);
            XMLParser.exportXmlFile(doc, activityLog);
        } catch (Exception e){
            e.printStackTrace();
        }

        updateStats();
    }

    void updateStats(){
        pointsText.setText("Current points: " + Stats.currentPoints);
        exercisesText.setText("Total points earned: " + Stats.totalPoints);
        tasksText.setText("CBT exercises completed: " + Stats.exercisesDone);
        currentStreakText.setText("Current streak: " + Stats.currentStreak);
        highestStreakText.setText("Highest streak: " + Stats.highestStreak);
    }

}