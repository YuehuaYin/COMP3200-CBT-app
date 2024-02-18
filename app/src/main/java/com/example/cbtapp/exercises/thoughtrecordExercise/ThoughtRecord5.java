package com.example.cbtapp.exercises.thoughtrecordExercise;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cbtapp.R;

import java.time.format.DateTimeFormatter;
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            text = ("Date: " + thoughtAct.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n\n");
        }
        text += "Thought: " + thoughtAct.getThoughtText() +
                "\nBelief in thought: " + thoughtAct.getBelief() +
                "\n\nArguments:";

        txtView.setText(text);

        ArrayList<Argument> args = thoughtAct.getArguments();
        for (Argument a:args) {

            Spannable word = new SpannableString(a.getText());

            if (a.isFor()){
                word.setSpan(new ForegroundColorSpan(Color.RED), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            else {
                word.setSpan(new ForegroundColorSpan(Color.GREEN), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            txtView.append(word);
        }

        txtView.append("\n\nBalanced thought: " + thoughtAct.getBalancedThought() +
                "\nBelief in balanced thought: " + thoughtAct.getBalancedBelief());

        return v;
    }
}
