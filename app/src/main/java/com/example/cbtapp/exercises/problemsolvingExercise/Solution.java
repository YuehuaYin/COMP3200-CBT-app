package com.example.cbtapp.exercises.problemsolvingExercise;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Entity
public class Solution {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "text")
    String text;

    @ColumnInfo(name = "hasNotif")
    public Boolean hasNotif = false;

    @ColumnInfo(name = "notifhour")
    public Integer notifhour;

    @ColumnInfo(name = "notifmin")
    public Integer notifmin;

    @ColumnInfo(name = "notifrepeating")
    public Integer notifrepeating = 1;

    @ColumnInfo(name = "notifmon")
    public Boolean notifmon = false;

    @ColumnInfo(name = "notiftue")
    public Boolean notiftue = false;

    @ColumnInfo(name = "notifwed")
    public Boolean notifwed = false;

    @ColumnInfo(name = "notifthur")
    public Boolean notifthur = false;

    @ColumnInfo(name = "notiffri")
    public Boolean notiffri = false;

    @ColumnInfo(name = "notifsat")
    public Boolean notifsat = false;

    @ColumnInfo(name = "notifsun")
    public Boolean notifsun = false;

    @ColumnInfo(name = "notifsetday")
    public int notifsetday;

    public Solution(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public void setNotifVars(Integer hour, Integer min, Integer repeating, List<Boolean> days){
        this.notifhour = hour;
        this.notifmin = min;
        this.notifrepeating = repeating;
        notifsun = days.get(0);
        notifmon = days.get(1);
        notiftue = days.get(2);
        notifwed = days.get(3);
        notifthur = days.get(4);
        notiffri = days.get(5);
        notifsat = days.get(6);
        Calendar calendar = Calendar.getInstance();
        notifsetday = calendar.get(Calendar.DAY_OF_WEEK);
    }

    public void resetNotif(){
        notifhour = null;
        notifmin = null;
        notifrepeating = null;
        notifmon = false;
        notiftue = false;
        notifwed = false;
        notifthur = false;
        notiffri = false;
        notifsat = false;
        notifsun = false;
        hasNotif = false;
    }

    public List<Boolean> getDaysSelected(){
        return Arrays.asList(notifsun, notifmon, notiftue, notifwed, notifthur, notiffri, notifsat);
    }
}
