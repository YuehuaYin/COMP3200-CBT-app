package com.example.cbtapp.exercises.thoughtrecordExercise;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.cbtapp.R;

public class ThoughtRecord2 extends Fragment {

    View v;
    EditText thoughtText;
    SeekBar intensityBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_thought_record2, container, false);

        thoughtText = v.findViewById(R.id.editTextText4);
        thoughtText.setText(((ThoughtRecordActivity)getActivity()).getThoughtText());

        intensityBar = v.findViewById(R.id.seekBar);
        intensityBar.setProgress(((ThoughtRecordActivity)getActivity()).getBelief());

        return v;
    }

    @Override
    public void onStop() {
        ((ThoughtRecordActivity)getActivity()).setThoughtText(thoughtText.getText().toString());
        ((ThoughtRecordActivity)getActivity()).setBelief(intensityBar.getProgress());
        super.onStop();
    }
}