package com.example.cbtapp.exercises.problemsolvingExercise;

import java.util.ArrayList;

public class Solution {

    String text;
    ArrayList<String> problems = new ArrayList<>();
    Boolean hasNotif = false;

    public Solution(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Boolean getHasNotif() {
        return hasNotif;
    }

    public void setHasNotif(Boolean hasNotif) {
        this.hasNotif = hasNotif;
    }

    public ArrayList<String> getProblems() {
        return problems;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setProblems(ArrayList<String> problems) {
        this.problems = problems;
    }
}
