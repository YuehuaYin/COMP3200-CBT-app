package com.example.cbtapp.exercises.situationExercise;

import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cbtapp.R;
import com.example.cbtapp.activityLog.ActivityLog;
import com.example.cbtapp.activityLog.AppDatabase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Situation5 extends Fragment {

    View v;
    TextView txtView;
    Button challengeButton;
    SituationActivity sitAct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_situation5, container, false);

        sitAct = ((SituationActivity)getActivity());
        txtView = v.findViewById(R.id.textView3);
        challengeButton = v.findViewById(R.id.button4);

        String text = "";

        txtView.setMovementMethod(new ScrollingMovementMethod());
        text += "Situation: " + sitAct.getSitText() +
                "\n\nFeelings: ";

        ArrayList<Feel> feelings = sitAct.getFeelings();
        for (int i = 0; i < feelings.size(); i++) {
            text += "\n" + feelings.get(i).getText() + "\t\t\t\t\t\t\t\t\tIntensity: " + feelings.get(i).getIntensity();
        }

        text += "\n\nThoughts:";
        ArrayList<Feel> thoughts = sitAct.getThoughts();
        for (int i = 0; i < thoughts.size(); i++) {
            text += "\n" + thoughts.get(i).getText() + "\t\t\t\t\t\t\t\t\tIntensity: " + thoughts.get(i).getIntensity();
        }

        txtView.setText(text);
        sitAct.setContent(text);

        challengeButton.setOnClickListener(view -> sitAct.switchChallengeFragment());

        return v;
    }
}