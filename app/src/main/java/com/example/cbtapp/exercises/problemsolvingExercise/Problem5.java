package com.example.cbtapp.exercises.problemsolvingExercise;

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
import com.example.cbtapp.exercises.situationExercise.SituationActivity;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Problem5 extends Fragment {
    View v;
    TextView txtView;
    ProblemActivity probAct;
    Button challengeButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_situation5, container, false);

        challengeButton = v.findViewById(R.id.button4);
        challengeButton.setVisibility(View.GONE);

        probAct = ((ProblemActivity) getActivity());
        txtView = v.findViewById(R.id.textView3);

        String text = "";

        txtView.setMovementMethod(new ScrollingMovementMethod());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            text = ("Date: " + probAct.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n\n");
        }

        text += "Problem: " + probAct.getProblemText() +
            "\n\nSolutions: ";

        ArrayList<Solution> solutions = probAct.getSolutions();
        for (Solution s: solutions) {
            text += "\n" + s.getText();
            if (s.hasNotif){
                text += "\t\t\t\t\t\t\t\t\tNotification: ";
            }
        }

        txtView.setText(text);

        return v;
    }
}
