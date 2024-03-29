package com.example.cbtapp.exercises.situationExercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.R;
import com.example.cbtapp.exercises.problemsolvingExercise.Solution;
import com.example.cbtapp.exercises.problemsolvingExercise.SolutionHolder;

import java.util.ArrayList;

public class FeelingAdapter extends RecyclerView.Adapter<FeelingHolder> {

    // reference: https://www.youtube.com/watch?v=aUFdgLSEl0g
    Context context;
    ArrayList<Feel> feelings;
    SelectListener listener;

    public FeelingAdapter(Context context, ArrayList<Feel> feelings) {
        this.context = context;
        this.feelings = feelings;
    }

    @Override
    public FeelingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FeelingHolder(LayoutInflater.from(context).inflate(R.layout.activity_feeling, parent, false));
    }

    @Override
    public void onBindViewHolder(FeelingHolder holder, int position) {
        holder.feelingText.setText(feelings.get(position).getText());
        holder.seekBar.setProgress(feelings.get(position).getIntensity());
    }

    @Override
    public int getItemCount() {
        return feelings.size();
    }
}
