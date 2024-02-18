package com.example.cbtapp.exercises.problemsolvingExercise;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.R;

public class SolutionHolder extends RecyclerView.ViewHolder {

    EditText editText;
    Button button;
    public SolutionHolder(View itemView) {
        super(itemView);
        editText = itemView.findViewById(R.id.txt);
        button = itemView.findViewById(R.id.button8);
    }
}