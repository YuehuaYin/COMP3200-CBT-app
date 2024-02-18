package com.example.cbtapp.exercises.thoughtrecordExercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cbtapp.R;

public class ThoughtRecord4 extends Fragment {

    View v;
    TextView title;
    EditText thoughtText;
    SeekBar intensityBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_thought_record2, container, false);

        title = v.findViewById(R.id.textView7);
        title.setText("Enter balanced thought:");

        thoughtText = v.findViewById(R.id.editTextText4);
        if (((ThoughtRecordActivity)getActivity()).getBalancedThought() != null) {
            thoughtText.setText(((ThoughtRecordActivity) getActivity()).getBalancedThought());
        }

        intensityBar = v.findViewById(R.id.seekBar);
        intensityBar.setProgress(((ThoughtRecordActivity)getActivity()).getBalancedBelief());

        return v;
    }

    @Override
    public void onStop() {
        ((ThoughtRecordActivity)getActivity()).setBalancedThought(thoughtText.getText().toString());
        ((ThoughtRecordActivity)getActivity()).setBalancedBelief(intensityBar.getProgress());
        super.onStop();
    }
}
