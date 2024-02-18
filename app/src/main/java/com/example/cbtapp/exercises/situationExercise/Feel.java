package com.example.cbtapp.exercises.situationExercise;

public class Feel {

    String text;
    int intensity;

    public Feel(String text, int intensity) {
        this.text = text;
        this.intensity = intensity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }
}
