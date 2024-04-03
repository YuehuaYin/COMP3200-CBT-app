package com.example.cbtapp.exercises.situationExercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.R;

import java.util.ArrayList;

public class Situation3 extends Fragment {

    View v;
    Button addFeelingButton;
    RecyclerView recyclerView;
    ArrayList<Feel> feelings;
    FeelingAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_situation3, container, false);

        feelings = ((SituationActivity)getActivity()).getFeelings();

        recyclerView = v.findViewById(R.id.recyclerview);
        adapter = new FeelingAdapter(getContext(), feelings);
        addFeelingComponents();

        addFeelingButton = v.findViewById(R.id.button2);
        addFeelingButton.setOnClickListener(view -> {
            updateVars();

            feelings.add(new Feel("Feeling " + (feelings.size() + 1), 5));
            addFeelingComponents();
        });

        return v;
    }

    void updateVars(){
        feelings.clear();
        EditText et;
        SeekBar sb;

        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            v = recyclerView.getChildAt(i);
            et = v.findViewById(R.id.editTextfeeling);
            sb = v.findViewById(R.id.seekBar3);
            feelings.add(new Feel(et.getText().toString(), sb.getProgress()));
        }
    }

    @Override
    public void onStop() {
        updateVars();
        ((SituationActivity) getActivity()).setFeelings(feelings);
        super.onStop();
    }

    public void addFeelingComponents(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}