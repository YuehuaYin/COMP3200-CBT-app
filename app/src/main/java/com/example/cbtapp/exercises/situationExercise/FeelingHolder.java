package com.example.cbtapp.exercises.situationExercise;

import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.R;

public class FeelingHolder extends RecyclerView.ViewHolder{

    EditText feelingText;
    SeekBar seekBar;
    TextView seekBarTxt;
    public FeelingHolder(View itemView) {
        super(itemView);
        feelingText = itemView.findViewById(R.id.editTextfeeling);
        seekBar = itemView.findViewById(R.id.seekBar3);
        seekBarTxt = itemView.findViewById(R.id.textView6);
    }
}
