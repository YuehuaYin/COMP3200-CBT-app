package com.example.cbtapp.exercises.problemsolvingExercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cbtapp.R;
import com.example.cbtapp.exercises.situationExercise.CustomAdapter;

import java.util.ArrayList;

public class Problem3 extends Fragment {

    View v;
    TextView titleText;
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

        titleText = v.findViewById(R.id.situationTxt2);
        titleText.setText("How does this problem make you feel?");

        feelings = ((ProblemActivity)getActivity()).getFeelings();
        intensities = ((ProblemActivity)getActivity()).getFeelingIntensities();

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
        ((ProblemActivity) getActivity()).setFeelings(feelings);
        ((ProblemActivity) getActivity()).setFeelingIntensities(intensities);
        super.onStop();
    }

    public void addFeelingComponents(){
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
