package com.example.cbtapp.exercises.situationExercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.R;

import java.util.ArrayList;

public class FeelingAdapterListener extends RecyclerView.Adapter<FeelingAdapterListener.FeelingHolder2> {

    // reference: https://www.youtube.com/watch?v=7GPUpvcU1FE
    Context context;
    ArrayList<Feel> feelings;
    SelectListener listener;

    public FeelingAdapterListener(Context context, ArrayList<Feel> feelings, SelectListener listener) {
        this.context = context;
        this.feelings = feelings;
        this.listener = listener;
    }

    @Override
    public FeelingHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FeelingHolder2(LayoutInflater.from(context).inflate(R.layout.activity_feeling, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(FeelingHolder2 holder, int position) {
        holder.feelingText.setText(feelings.get(position).getText());
        holder.feelingText.setFocusable(false);
        holder.seekBar.setProgress(feelings.get(position).getIntensity());
        holder.seekBar.setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return feelings.size();
    }

    public static class FeelingHolder2 extends RecyclerView.ViewHolder {

        EditText feelingText;
        SeekBar seekBar;

        public FeelingHolder2(View itemView, SelectListener listener) {
            super(itemView);
            feelingText = itemView.findViewById(R.id.editTextfeeling);
            seekBar = itemView.findViewById(R.id.seekBar3);

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClicked(position);
                    }
                }
            });
        }
    }
}
