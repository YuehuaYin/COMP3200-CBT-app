package com.example.cbtapp.activityLog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cbtapp.HomeActivity;
import com.example.cbtapp.NavBar;
import com.example.cbtapp.R;
import com.example.cbtapp.SwipeListener;
import com.example.cbtapp.exercises.ExercisesHome;
import com.example.cbtapp.exercises.situationExercise.FeelingAdapter;
import com.example.cbtapp.stats.StatsPage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class CalenderLog extends AppCompatActivity {
    SwipeListener swipeListener;
    LinearLayout navBar;
    RecyclerView logView;
    CalendarView calendarView;
    LogListAdapter adapter;
    ArrayList<ActivityLog> activityLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_log);

        navBar = findViewById(R.id.navBar);
        NavBar.setUpNavbar(this, navBar);
        swipeListener = new SwipeListener(findViewById(R.id.layout), navBar);

        logView = findViewById(R.id.logView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            loadActivityLogs(LocalDate.now());
        }

        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                LocalDate date = null;
                String monthStr = String.valueOf(month + 1);
                String dayStr = String.valueOf(dayOfMonth);

                if (month < 10) {
                    monthStr = "0" + monthStr;
                }
                if (dayOfMonth < 10){
                    dayStr = "0" + dayStr;
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    date = LocalDate.parse(year + "-" + monthStr + "-" + dayStr);
                }
                loadActivityLogs(date);
            }
        });

    }

    private void loadActivityLogs(LocalDate date){
        System.out.println(date);
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        activityLogs = new ArrayList<>(db.userDao().getLogs(date.toString()));
        if (activityLogs != null) {
            adapter = new LogListAdapter(getApplicationContext(), activityLogs);
            logView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            logView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


}