package com.example.cbtapp.activityLog;

import android.os.Build;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.NavBar;
import com.example.cbtapp.R;
import com.example.cbtapp.SwipeListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalenderLog extends AppCompatActivity {
    SwipeListener swipeListener;
    LinearLayout navBar;
    RecyclerView logView;
    CalendarView calendarView;
    LogListAdapter adapter;
    ArrayList<ActivityLog> activityLogs;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_log);

        navBar = findViewById(R.id.navBar);
        NavBar.setUpNavbar(this, navBar);
        swipeListener = new SwipeListener(findViewById(R.id.layout), navBar);

        db = AppDatabase.getDbInstance(getApplicationContext());

        logView = findViewById(R.id.logView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            loadActivityLogs(LocalDate.now());
        }

        // TODO: make dates with activity have a background
        List<String> datesWithLog = new ArrayList<>(db.userDao().getAllDates());

        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
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
        });

    }

    private void loadActivityLogs(LocalDate date){
        activityLogs = new ArrayList<>(db.userDao().getLogs(date.toString()));
        if (activityLogs != null) {
            adapter = new LogListAdapter(getApplicationContext(), activityLogs);
            logView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            logView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


}