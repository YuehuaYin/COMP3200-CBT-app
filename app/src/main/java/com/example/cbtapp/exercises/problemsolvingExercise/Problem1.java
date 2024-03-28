package com.example.cbtapp.exercises.problemsolvingExercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cbtapp.R;

public class Problem1 extends Fragment {
    View v;
    TextView titleText;
    TextView bodyText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_situation1, container, false);

        titleText = v.findViewById(R.id.textView2);
        titleText.setText("Problem Solving");

        bodyText = v.findViewById(R.id.textView);
        return v;
    }
}
