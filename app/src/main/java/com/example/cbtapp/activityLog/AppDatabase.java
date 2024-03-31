package com.example.cbtapp.activityLog;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.cbtapp.exercises.problemsolvingExercise.Solution;

@Database(entities = {ActivityLog.class, Solution.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    private static AppDatabase INSTANCE;
    public static AppDatabase getDbInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ActivityLogDB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
