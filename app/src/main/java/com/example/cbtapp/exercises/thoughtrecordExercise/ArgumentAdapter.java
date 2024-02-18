package com.example.cbtapp.exercises.thoughtrecordExercise;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.R;
import com.example.cbtapp.exercises.problemsolvingExercise.Solution;
import com.example.cbtapp.exercises.problemsolvingExercise.SolutionHolder;

import java.util.ArrayList;

public class ArgumentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    ArrayList<Argument> args;

    public ArgumentAdapter(Context context, ArrayList<Argument> args) {
        this.context = context;
        this.args = args;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println(viewType);
        // 0 = for, 1 = against
        if (viewType == 0){
            return new ArgumentHolder(LayoutInflater.from(context).inflate(R.layout.activity_argue_for, parent, false));
        }
        else {
            return new ArgumentHolder(LayoutInflater.from(context).inflate(R.layout.activity_argue_against, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Argument a = args.get(position);
        ((ArgumentHolder)holder).text.setText(a.getText());
    }

    @Override
    public int getItemCount() {
        return args.size();
    }

    @Override
    public int getItemViewType(int position) {
        return args.get(position).getType();
    }
}
