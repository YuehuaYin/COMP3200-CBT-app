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
        bodyText.setText("When dealing with a current and realistic worry, a problem solving approach can be helpful for making the problem seem more manageable. First identify the problem, then identify how this problem is making you feel. Afterwards, list possible solutions and add notifications to prompt you to achieve them.");

        return v;
    }
}
