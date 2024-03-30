package com.example.cbtapp.goals;

import java.util.List;

public class Notification {
    Integer hour;
    Integer minute;
    Integer weeksRepeating;
    List<Boolean> daysSelected;

    public Notification(Integer hour, Integer minute, Integer weeksRepeating, List<Boolean> daysSelected) {
        this.hour = hour;
        this.minute = minute;
        this.weeksRepeating = weeksRepeating;
        this.daysSelected = daysSelected;
    }
}
