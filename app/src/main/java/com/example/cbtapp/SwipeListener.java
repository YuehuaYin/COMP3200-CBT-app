package com.example.cbtapp;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

// based on https://www.youtube.com/watch?v=vNJyU-XW8_Y
public class SwipeListener implements View.OnTouchListener{

    GestureDetector gestureDetector;

    public SwipeListener (View view, LinearLayout navBar){

        navBar.setVisibility(View.GONE);

        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDown(@NonNull MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                float xDiff = e2.getX() - e1.getX();
                if (Math.abs(xDiff) > 100 && Math.abs(velocityX) > 100) {
                    if (xDiff < 0) {
                        System.out.println("swiped left");
                        navBar.setVisibility(View.GONE);
                    } else {
                        System.out.println("swiped right");
                        navBar.setVisibility(View.VISIBLE);
                    }
                }
                return true;
            }
        };
        gestureDetector = new GestureDetector(listener);
        view.setOnTouchListener(this);
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }
}
