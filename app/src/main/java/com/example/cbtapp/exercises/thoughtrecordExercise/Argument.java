package com.example.cbtapp.exercises.thoughtrecordExercise;

public class Argument {

    Boolean isFor;
    String text;

    public Argument(Boolean isFor, String text) {
        this.isFor = isFor;
        this.text = text;
    }

    public int getType(){
        if (isFor) {
            return 0;
        }
        else {
            return 1;
        }
    }
    public Boolean isFor() {
        return isFor;
    }

    public void setFor(Boolean aFor) {
        isFor = aFor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
