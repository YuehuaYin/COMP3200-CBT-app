package com.example.cbtapp.exercises.situationExercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import com.example.cbtapp.R;
import com.example.cbtapp.exercises.TipDialog;

import java.util.ArrayList;

public class Situation4 extends Fragment {
    View v;
    Button addthoughtButton;
    Button tipButton;
    ListView listview;
    ArrayList<String> thoughts;
    ArrayList<Integer> intensities;
    CustomAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_situation4, container, false);

        thoughts = ((SituationActivity)getActivity()).getThoughts();
        intensities = ((SituationActivity)getActivity()).getThoughtIntensities();

        listview = v.findViewById(R.id.listview1);
        adapter = new CustomAdapter(getContext(), thoughts, intensities);
        addThoughtComponents();

        addthoughtButton = v.findViewById(R.id.button2);
        addthoughtButton.setOnClickListener(view -> {
            updateVars();

            thoughts.add("Thought " + (thoughts.size() + 1));
            intensities.add(5);

            addThoughtComponents();
        });

        tipButton = v.findViewById(R.id.button3);
        tipButton.setOnClickListener(view -> {
            ((SituationActivity) getActivity()).showTip("Situation tip");
        });

        return v;
    }

    void updateVars(){
        thoughts.clear();
        intensities.clear();
        EditText et;
        SeekBar sb;

        for (int i = 0; i < listview.getCount(); i++) {
            v = listview.getChildAt(i);
            et = (EditText) v.findViewById(R.id.editTextfeeling);
            thoughts.add(et.getText().toString());

            sb = v.findViewById(R.id.seekBar3);
            intensities.add(sb.getProgress());
        }
    }

    @Override
    public void onStop() {
        updateVars();
        ((SituationActivity) getActivity()).setThoughts(thoughts);
        ((SituationActivity) getActivity()).setThoughtIntensities(intensities);
        super.onStop();
    }

    public void addThoughtComponents(){
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}