package com.example.cbtapp.stats;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Stats {
    public static int points;
    public static int tasksCompleted;
    public static int exercisesDone;
    public static int currentStreak;
    public static int highestStreak;

    public static void readStats (File statsFile) {
        try {
            byte[] content = new byte[(int) statsFile.length()];
            FileInputStream reader = new FileInputStream(statsFile);
            reader.read(content);
            String[] strings = new String(content).split(" ");
            points = Integer.parseInt(strings[0]);
            tasksCompleted = Integer.parseInt(strings[1]);
            exercisesDone = Integer.parseInt(strings[2]);
            currentStreak = Integer.parseInt(strings[3]);
            highestStreak = Integer.parseInt(strings[4]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addPoints(int p){
        points += p;
    }

    public static void addTaskCompleted(){
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
        points = 0;
        tasksCompleted = 0;
        exercisesDone = 0;
        currentStreak = 0;
        highestStreak = 0;
    }

    public static void writeStats(FileOutputStream writer) throws IOException {
        String toWrite = points + " " + tasksCompleted + " " + exercisesDone + " " + currentStreak + " " + highestStreak;
        writer.write((toWrite).getBytes());
        writer.close();
    }

    /**
     FileOutputStream writer = openFileOutput("StatsFile.txt", MODE_PRIVATE);
     **/

}
