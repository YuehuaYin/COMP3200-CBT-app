package com.example.cbtapp.activityLog;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cbtapp.exercises.problemsolvingExercise.Solution;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT date FROM activityLog")
    List<String> getAllDates();

    @Query("SELECT * FROM activitylog WHERE date==:date")
    List<ActivityLog> getLogs(String date);

    @Insert
    void insertLog(ActivityLog... activityLogs);

    @Insert
    void insertSol(Solution... solutions);

    @Query("SELECT * FROM solution")
    List<Solution> getAllSols();

    @Query("DELETE FROM activitylog")
    void deleteAllLogs();

    @Query("DELETE FROM solution")
    void deleteAllSols();

    @Query("DELETE FROM solution WHERE uid==:userId")
    void deleteSol(int userId);
}
