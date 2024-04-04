package com.example.cbtapp.exercises.situationExercise;

public class Feel {

    String text;
    int intensity;
    String seekBarTxt;

    public Feel(String text, int intensity, String seekBarTxt) {
        this.text = text;
        this.intensity = intensity;
        this.seekBarTxt = seekBarTxt;
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

    public String getSeekBarTxt() {
        return seekBarTxt;
    }
}
