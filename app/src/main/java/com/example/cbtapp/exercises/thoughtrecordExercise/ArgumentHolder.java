package com.example.cbtapp.exercises.thoughtrecordExercise;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.R;

public class ArgumentHolder extends RecyclerView.ViewHolder {

    TextView text;
    public ArgumentHolder(View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.textView15);
    }
}
