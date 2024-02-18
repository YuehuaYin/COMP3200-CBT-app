package com.example.cbtapp.exercises.situationExercise;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;

import com.example.cbtapp.R;

import java.util.ArrayList;

public class Situation3 extends Fragment {

    View v;
    Button addFeelingButton;
    ListView listview;
    ArrayList<String> feelings;
    ArrayList<Integer> intensities;
    CustomAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_situation3, container, false);

        feelings = ((SituationActivity)getActivity()).getFeelings();
        intensities = ((SituationActivity)getActivity()).getFeelingIntensities();

        listview = v.findViewById(R.id.listview1);
        adapter = new CustomAdapter(getContext(), feelings, intensities);
        addFeelingComponents();

        addFeelingButton = v.findViewById(R.id.button2);
        addFeelingButton.setOnClickListener(view -> {
            updateVars();

            feelings.add("Feeling " + (feelings.size() + 1));
            intensities.add(5);

            addFeelingComponents();
        });

        return v;
    }

    void updateVars(){
        feelings.clear();
        intensities.clear();
        EditText et;
        SeekBar sb;

        for (int i = 0; i < listview.getCount(); i++) {
            v = listview.getChildAt(i);
            et = (EditText) v.findViewById(R.id.editTextfeeling);
            feelings.add(et.getText().toString());

            sb = v.findViewById(R.id.seekBar3);
            intensities.add(sb.getProgress());
        }
    }

    @Override
    public void onStop() {
        updateVars();
        ((SituationActivity) getActivity()).setFeelings(feelings);
        ((SituationActivity) getActivity()).setFeelingIntensities(intensities);
        super.onStop();
    }

    public void addFeelingComponents(){
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}