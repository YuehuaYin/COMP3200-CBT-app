package com.example.cbtapp.exercises.problemsolvingExercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.R;

import java.util.ArrayList;

public class SolutionAdapter extends RecyclerView.Adapter<SolutionHolder> {

    Context context;
    ArrayList<Solution> solutions;

    public SolutionAdapter(Context context, ArrayList<Solution> solutions) {
        this.context = context;
        this.solutions = solutions;
    }

    @Override
    public SolutionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SolutionHolder(LayoutInflater.from(context).inflate(R.layout.problem_solution, parent, false));
    }

    @Override
    public void onBindViewHolder(SolutionHolder holder, int position) {
        Solution s = solutions.get(position);
        holder.editText.setText(s.getText());
        if (s.hasNotif) {
            holder.button.setText("Change notification");
        }
        else {
            holder.button.setText("Add notification");
        }
    }

    @Override
    public int getItemCount() {
        return solutions.size();
    }
}
