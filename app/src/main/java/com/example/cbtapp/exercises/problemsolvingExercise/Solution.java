package com.example.cbtapp.exercises.problemsolvingExercise;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.lang.reflect.Array;
import java.util.Arrays;
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
    public Integer notifrepeating;

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
        notifmon = days.get(0);
        notiftue = days.get(1);
        notifwed = days.get(2);
        notifthur = days.get(3);
        notiffri = days.get(4);
        notifsat = days.get(5);
        notifsun = days.get(6);
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
        return Arrays.asList(notifmon, notiftue, notifwed, notifthur, notiffri, notifsat, notifsun);
    }
}
