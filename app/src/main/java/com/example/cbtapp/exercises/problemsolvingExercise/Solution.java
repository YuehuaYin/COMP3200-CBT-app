package com.example.cbtapp.exercises.problemsolvingExercise;

import com.example.cbtapp.goals.Notification;

import java.util.ArrayList;

public class Solution {

    String text;
    public Boolean hasNotif = false;
    public Notification notification = null;

    public Solution(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
