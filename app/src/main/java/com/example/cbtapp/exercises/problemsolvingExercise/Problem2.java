package com.example.cbtapp.exercises.problemsolvingExercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cbtapp.R;

public class Problem2 extends Fragment {

    View v;
    TextView titleText;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_situation2, container, false);

        titleText = v.findViewById(R.id.situationPrompt);
        titleText.setText("What is the problem?");

        editText = v.findViewById(R.id.editTextText2);
        editText.setText(((ProblemActivity)getActivity()).getProblemText());
        editText.setHint("Enter problem");

        return v;
    }

    @Override
    public void onStop() {
        ((ProblemActivity) getActivity()).setProblemText(editText.getText().toString());
        super.onStop();
    }
}
