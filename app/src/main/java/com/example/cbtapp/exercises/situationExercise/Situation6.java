package com.example.cbtapp.exercises.situationExercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.R;

import java.util.ArrayList;

public class Situation6 extends Fragment {

    View v;
    Button addthoughtButton;
    RecyclerView recyclerView;
    ArrayList<Feel> thoughts;
    FeelingAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_situation3, container, false);

        thoughts = ((SituationActivity)getActivity()).getReactions();

        recyclerView = v.findViewById(R.id.recyclerview);
        adapter = new FeelingAdapter(getContext(), thoughts);
        addThoughtComponents();

        TextView titleText = v.findViewById(R.id.situationTxt2);
        titleText.setText("What physical reactions did you notice?");

        addthoughtButton = v.findViewById(R.id.button2);
        addthoughtButton.setOnClickListener(view -> {
            updateVars();
            thoughts.add(new Feel("Reaction " + (thoughts.size() + 1), 5, "Intensity"));
            addThoughtComponents();
        });

        return v;
    }

    void updateVars(){
        thoughts.clear();
        EditText et;
        SeekBar sb;

        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            v = recyclerView.getChildAt(i);
            et = v.findViewById(R.id.editTextfeeling);
            sb = v.findViewById(R.id.seekBar3);
            thoughts.add(new Feel(et.getText().toString(), sb.getProgress(), "Intensity"));
        }
    }

    @Override
    public void onStop() {
        updateVars();
        ((SituationActivity) getActivity()).setReactions(thoughts);
        super.onStop();
    }

    public void addThoughtComponents(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
