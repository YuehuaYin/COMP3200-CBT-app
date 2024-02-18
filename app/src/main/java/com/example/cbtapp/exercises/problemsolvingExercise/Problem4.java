package com.example.cbtapp.exercises.problemsolvingExercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.R;

import java.util.ArrayList;
import java.util.Collections;

public class Problem4 extends Fragment {

    // reference:
    View v;
    RecyclerView recyclerView;
    ArrayList<Solution> solutions;
    Button addSolutions;
    Button tipButton;
    SolutionAdapter adapter;
    int dragItemIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_problem4, container, false);

        solutions = ((ProblemActivity) getActivity()).getSolutions();

        recyclerView = v.findViewById(R.id.recyclerview);
        adapter = new SolutionAdapter(getContext(), solutions);
        addSolutionComponents();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.START);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                updateVars();

                dragItemIndex = viewHolder.getAdapterPosition();
                int targetIndex = target.getAdapterPosition();

                Collections.swap(solutions, dragItemIndex, targetIndex);
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    solutions.remove(viewHolder.getAdapterPosition());
                    adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);

        addSolutions = v.findViewById(R.id.button2);
        addSolutions.setOnClickListener(view -> {
            updateVars();

            solutions.add(new Solution("Possible solution"));

            addSolutionComponents();
        });

        tipButton = v.findViewById(R.id.button3);
        tipButton.setOnClickListener(view -> {
            ((ProblemActivity) getActivity()).showTip("Problem tip");
        });

        return v;
    }

    void updateVars(){
        solutions.clear();
        EditText et;

        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            v = recyclerView.getChildAt(i);
            et = (EditText) v.findViewById(R.id.txt);
            solutions.add(new Solution(et.getText().toString()));
        }
    }

    @Override
    public void onStop() {
        updateVars();
        ((ProblemActivity) getActivity()).setSolutions(solutions);
        super.onStop();
    }

    public void addSolutionComponents(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
