package com.example.cbtapp.activityLog;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM activityLog")
    List<ActivityLog> getAllLogs();

    @Query("SELECT date FROM activityLog")
    List<String> getAllDates();

    @Query("SELECT * FROM activitylog WHERE date==:date")
    List<ActivityLog> getLogs(String date);

    @Insert
    void insertLog(ActivityLog... activityLogs);

    @Query("DELETE FROM activitylog")
    void deleteAll();
}
