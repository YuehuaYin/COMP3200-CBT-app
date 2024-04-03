package com.example.cbtapp.exercises.situationExercise;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cbtapp.R;

public class Situation1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_situation1, container, false);

        TextView infoText = v.findViewById(R.id.textView);
        infoText.setText("Sometimes we encounter situations where we find it hard to understand why we were feeling, thinking or behaving in certain ways. This exercise prompts you to identify feelings, thoughts and behaviours that occurred due to a situation and asks you to rate each one. This will help you to process the situation and identify unhelpful negative cycles so you can work on stopping them. \n\nAt the end of the exercise, you can select a thought to challenge (will start the Thought Record exercise with the selected thought), which can be a way to stop the negative cycle of negative thoughts causing negative feelings and undesirable behaviours.");

        return v;
    }
}