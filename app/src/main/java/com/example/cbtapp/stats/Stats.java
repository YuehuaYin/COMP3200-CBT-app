package com.example.cbtapp.stats;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Stats {
    public static int totalPoints;
    public static int currentPoints;
    //public static int tasksCompleted;
    public static int exercisesDone;
    public static int currentStreak;
    public static int highestStreak;
    public static int dailyBonusClaimed;

    public static void readStats (File statsFile) throws IOException {
        byte[] content = new byte[(int) statsFile.length()];
        FileInputStream reader = new FileInputStream(statsFile);
        reader.read(content);
        String[] strings = new String(content).split(" ");
        currentPoints = Integer.parseInt(strings[0]);
        totalPoints = Integer.parseInt(strings[1]);
        exercisesDone = Integer.parseInt(strings[2]);
        currentStreak = Integer.parseInt(strings[3]);
        highestStreak = Integer.parseInt(strings[4]);
        //dailyBonusClaimed = 0;
        dailyBonusClaimed = Integer.parseInt(strings[5]);
    }

    public static void addPoints(int p){
        currentPoints += p;
        totalPoints += p;
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

    public static void claimDaily(){
        dailyBonusClaimed = 1;
    }

    public static void resetDaily(){
        dailyBonusClaimed = 0;
    }

    public static void resetStats(){
        currentPoints = 0;
        totalPoints = 0;
        exercisesDone = 0;
        currentStreak = 0;
        highestStreak = 0;
    }

    public static void writeStats(FileOutputStream writer) throws IOException {
        String toWrite = currentPoints + " " + totalPoints + " " + exercisesDone + " " + currentStreak + " " + highestStreak + " " + dailyBonusClaimed;
        writer.write((toWrite).getBytes());
        writer.close();
    }

    /**
     FileOutputStream writer = openFileOutput("StatsFile.txt", MODE_PRIVATE);
     **/

}
