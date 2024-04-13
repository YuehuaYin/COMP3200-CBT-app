package com.example.cbtapp.activityLog;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cbtapp.exercises.problemsolvingExercise.Solution;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM activitylog WHERE date==:date")
    List<ActivityLog> getLogs(String date);

    @Insert
    void insertLog(ActivityLog... activityLogs);

    @Query("DELETE FROM activitylog")
    void deleteAllLogs();

    @Query("SELECT * FROM solution")
    List<Solution> getAllSols();

    @Insert
    void insertSol(Solution... solutions);

    @Query("DELETE FROM solution")
    void deleteAllSols();

    @Query("DELETE FROM solution WHERE uid==:userId")
    void deleteSol(int userId);

    @Query("SELECT * FROM solution WHERE text==:solText")
    Solution getSol(String solText);
}
