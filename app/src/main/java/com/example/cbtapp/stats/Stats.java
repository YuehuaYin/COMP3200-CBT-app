package com.example.cbtapp.stats;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Stats {
    public static int level;
    public static int currentPoints;
    public static int exercisesDone;
    public static int tasksCompleted;
    public static int currentStreak;
    public static int highestStreak;

    public static void readStats (File statsFile) throws IOException {
        byte[] content = new byte[(int) statsFile.length()];
        FileInputStream reader = new FileInputStream(statsFile);
        reader.read(content);
        String[] strings = new String(content).split(" ");
        level = Integer.parseInt(strings[0]);
        currentPoints = Integer.parseInt(strings[1]);
        exercisesDone = Integer.parseInt(strings[2]);
        tasksCompleted = Integer.parseInt(strings[3]);
        currentStreak = Integer.parseInt(strings[4]);
        highestStreak = Integer.parseInt(strings[5]);
    }

    public static boolean addPoints(int p){
        currentPoints += p;
        if (currentPoints >= 100){
            level += 1;
            currentPoints -= 100;
            return true;
        }
        else {
            return false;
        }
    }

    public static void addTaskComplete(){
        tasksCompleted += 1;
    }

    public static void addExercisesDone(){
        exercisesDone += 1;
    }

    public static void addStreak(){
        currentStreak += 1;
        highestStreak = Math.max(currentStreak, highestStreak);
    }

    public static void resetStreak(){
        currentStreak = 0;
    }

    public static void resetStats(){
        level = 0;
        currentPoints = 0;
        exercisesDone = 0;
        tasksCompleted = 0;
        currentStreak = 0;
        highestStreak = 0;
    }

    public static void writeStats(Context context) throws IOException {
        FileOutputStream writer = context.openFileOutput("StatsFile.txt", MODE_PRIVATE);
        String toWrite = level + " " + currentPoints + " " + exercisesDone + " " + tasksCompleted + " " + currentStreak + " " + highestStreak;
        writer.write((toWrite).getBytes());
        writer.close();
    }

}
