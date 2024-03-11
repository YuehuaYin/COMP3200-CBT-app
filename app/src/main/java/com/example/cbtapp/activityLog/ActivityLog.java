package com.example.cbtapp.activityLog;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.time.LocalDate;

@Entity
public class ActivityLog {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "content")
    public String content;

}
