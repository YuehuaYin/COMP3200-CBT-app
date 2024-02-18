package com.example.cbtapp.exercises.thoughtrecordExercise;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.cbtapp.R;
import com.example.cbtapp.exercises.problemsolvingExercise.Solution;
import com.example.cbtapp.exercises.situationExercise.FeelingAdapter;
import com.example.cbtapp.exercises.situationExercise.SituationActivity;

import java.util.ArrayList;

public class ThoughtRecord3 extends Fragment {

    View v;
    EditText message;
    Button switchSidesButton;
    Boolean currentlyFor = true;
    Button tipButton;
    Button sendMessageButton;
    RecyclerView recyclerView;
    ArrayList<Argument> args;
    ArgumentAdapter adapter;
    TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_thought_record3, container, false);

        args = ((ThoughtRecordActivity)getActivity()).getArguments();

        recyclerView = v.findViewById(R.id.recyclerView);
        adapter = new ArgumentAdapter(getContext(), args);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        message = v.findViewById(R.id.editTextText5);

        sendMessageButton = v.findViewById(R.id.button11);
        sendMessageButton.setOnClickListener(view -> {
            args.add(new Argument(currentlyFor, message.getText().toString()));
            message.getText().clear();

            adapter.notifyItemInserted(args.size()-1);
        });

        title = v.findViewById(R.id.textView14);
        switchSidesButton = v.findViewById(R.id.button9);
        switchSidesButton.setOnClickListener(view -> {
            if (currentlyFor){
                currentlyFor = false;
                title.setText("Arguing against thought:");
            }
            else {
                currentlyFor = true;
                title.setText("Arguing for thought:");
            }
        });

        tipButton = v.findViewById(R.id.button);
        tipButton.setOnClickListener(view -> {
            ((ThoughtRecordActivity) getActivity()).showTip("Thought record tip");
        });

        return v;
    }

    @Override
    public void onStop() {
        ((ThoughtRecordActivity)getActivity()).setArguments(args);
        super.onStop();
    }
}