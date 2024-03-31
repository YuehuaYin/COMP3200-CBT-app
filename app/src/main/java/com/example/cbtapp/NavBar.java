package com.example.cbtapp;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.cbtapp.activityLog.CalenderLog;
import com.example.cbtapp.collectables.CollectablesActivity;
import com.example.cbtapp.exercises.ExercisesHome;
import com.example.cbtapp.notifications.NotificationsActivity;
import com.example.cbtapp.stats.StatsPage;

public class NavBar {

    public static void setUpNavbar(Context context, LinearLayout navBar){

        Button homeButton = navBar.findViewById(R.id.navHome);
        homeButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, HomeActivity.class);
            context.startActivity(intent);
        });

        Button exercisesButton = navBar.findViewById(R.id.navExercises);
        exercisesButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, ExercisesHome.class);
            context.startActivity(intent);
        });

        Button goalsButton = navBar.findViewById(R.id.navGoals);
        goalsButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, NotificationsActivity.class);
            context.startActivity(intent);
        });

        Button statsButton = navBar.findViewById(R.id.navStats);
        statsButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, StatsPage.class);
            context.startActivity(intent);
        });

        Button activityButton = navBar.findViewById(R.id.navActivity);
        activityButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, CalenderLog.class);
            context.startActivity(intent);
        });

        Button collectButton = navBar.findViewById(R.id.navAchievements);
        collectButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, CollectablesActivity.class);
            context.startActivity(intent);
        });

        Button supportButton = navBar.findViewById(R.id.navSupport);
        supportButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, FurtherSupport.class);
            context.startActivity(intent);
        });
    }
}
