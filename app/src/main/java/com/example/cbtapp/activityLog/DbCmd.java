package com.example.cbtapp.activityLog;

import android.content.Context;

import com.example.cbtapp.exercises.problemsolvingExercise.Solution;

import java.time.LocalDate;
import java.util.List;

public class DbCmd {

    public static void saveActivityLog(LocalDate date, String type, String content, Context context){
        AppDatabase db = AppDatabase.getDbInstance(context.getApplicationContext());
        ActivityLog activityLog = new ActivityLog();
        activityLog.date = date.toString();
        activityLog.type = type;
        activityLog.content = content;
        db.userDao().insertLog(activityLog);
    }

    public static void deleteAllLogs(Context context){
        AppDatabase db = AppDatabase.getDbInstance(context.getApplicationContext());
        db.userDao().deleteAllLogs();
    }

    public static void saveSolution(Solution solution, Context context){
        AppDatabase db = AppDatabase.getDbInstance(context.getApplicationContext());
        db.userDao().insertSol(solution);
    }

    public static void deleteAllSolutions(Context context){
        AppDatabase db = AppDatabase.getDbInstance(context.getApplicationContext());
        db.userDao().deleteAllSols();
    }

    public static void deleteSolution(int uid, Context context){
        AppDatabase db = AppDatabase.getDbInstance(context.getApplicationContext());
        db.userDao().deleteSol(uid);
    }

    public static List<Solution> getAllSolutions(Context context){
        AppDatabase db = AppDatabase.getDbInstance(context.getApplicationContext());
        return db.userDao().getAllSols();
    }
}
