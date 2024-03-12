package com.example.cbtapp.exercises.situationExercise;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.HomeActivity;
import com.example.cbtapp.R;
import com.example.cbtapp.exercises.thoughtrecordExercise.ThoughtRecordActivity;
import com.example.cbtapp.stats.Stats;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class SituationChallenge extends Fragment implements SelectListener{

    View v;
    SituationActivity sitAct;
    RecyclerView recyclerView;
    ArrayList<Feel> thoughts;
    FeelingAdapterListener adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_situationchallenge, container, false);

        sitAct = ((SituationActivity)getActivity());

        thoughts= sitAct.getThoughts();

        recyclerView = v.findViewById(R.id.recyclerview);
        adapter = new FeelingAdapterListener(getContext(), thoughts, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return v;
    }

    @Override
    public void onItemClicked(int position) {
        Stats.addExercisesDone();
        Stats.addPoints(100);
        try {
            FileOutputStream writer = sitAct.openFileOutput("StatsFile.txt", MODE_PRIVATE);
            Stats.writeStats(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sitAct.saveActivity();

        Intent intent = new Intent(sitAct, ThoughtRecordActivity.class);
        intent.putExtra("Thought", thoughts.get(position).getText());
        intent.putExtra("Belief", thoughts.get(position).getIntensity());
        startActivity(intent);
    }
}