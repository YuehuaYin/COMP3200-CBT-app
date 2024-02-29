package com.example.cbtapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cbtapp.activityLog.CalenderLog;
import com.example.cbtapp.activityLog.XMLParser;
import com.example.cbtapp.exercises.ExercisesHome;
import com.example.cbtapp.stats.Stats;
import com.example.cbtapp.stats.StatsPage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class HomeActivity extends AppCompatActivity {
    Document log;
    Element rootElement;
    SwipeListener swipeListener;
    LinearLayout navBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setUpNavbar();

        File path = getApplicationContext().getFilesDir();
        path.mkdirs();

        Stats.readStats(new File(path, "StatsFile.txt"));

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

    public  void addNewLog(){

    }

    public void addToLog(String type, String text){

    }
}