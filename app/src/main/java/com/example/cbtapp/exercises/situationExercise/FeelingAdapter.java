package com.example.cbtapp.exercises.situationExercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.R;

import java.util.ArrayList;

public class FeelingAdapter extends RecyclerView.Adapter<FeelingHolder> {

    // reference: https://www.youtube.com/watch?v=aUFdgLSEl0g
    Context context;
    ArrayList<Feel> feelings;

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
        holder.seekBarTxt.setText(feelings.get(position).getSeekBarTxt());
    }

    @Override
    public int getItemCount() {
        return feelings.size();
    }
}
