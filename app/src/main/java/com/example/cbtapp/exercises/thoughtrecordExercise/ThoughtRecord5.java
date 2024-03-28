package com.example.cbtapp.exercises.thoughtrecordExercise;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cbtapp.R;

import java.util.ArrayList;

public class ThoughtRecord5 extends Fragment {

    View v;
    TextView txtView;
    Button challengeButton;
    ThoughtRecordActivity thoughtAct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_situation5, container, false);

        challengeButton = v.findViewById(R.id.button4);
        challengeButton.setVisibility(View.GONE);

        thoughtAct = ((ThoughtRecordActivity)getActivity());
        txtView = v.findViewById(R.id.textView3);

        String text = "";

        txtView.setMovementMethod(new ScrollingMovementMethod());

        text += "Thought: " + thoughtAct.getThoughtText() +
                "\nBelief in thought: " + thoughtAct.getBelief() +
                "\n\nArguments:";

        ArrayList<Argument> args = thoughtAct.getArguments();
        for (Argument a:args) {
            if (a.isFor()){
                text += "\nFor: " + a.getText();
            }
            else {
                text += "\nAgainst: " + a.getText();
            }
        }

        text += "\n\nBalanced thought: " + thoughtAct.getBalancedThought() +
                "\nBelief in balanced thought: " + thoughtAct.getBalancedBelief();

        txtView.setText(text);

        thoughtAct.setContent(text);

        return v;
    }
}
