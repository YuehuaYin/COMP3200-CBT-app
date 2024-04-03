package com.example.cbtapp.exercises.thoughtrecordExercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cbtapp.R;

public class ThoughtRecord1 extends Fragment {
    View v;
    TextView titleText;
    TextView bodyText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_situation1, container, false);

        titleText = v.findViewById(R.id.textView2);
        titleText.setText("Thought Record");

        bodyText = v.findViewById(R.id.textView);
        bodyText.setText("When you feel low or anxious, you may experience unrealistic/extreme negative thoughts. These can be due to cognitive distortions such as overgeneralising or catastrophising. To help manage these thoughts, this exercise prompts you to argue for and against the negative thought as if you were using a text messaging app. \n\nThe aim of this is to help you see past the distortion and form a more balanced and reasonable thought based on facts.");

        return v;
    }
}
