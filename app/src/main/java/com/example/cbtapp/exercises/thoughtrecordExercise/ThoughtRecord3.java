package com.example.cbtapp.exercises.thoughtrecordExercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.R;

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

        ThoughtRecordActivity thoughtRecordActivity = (ThoughtRecordActivity) getActivity();

        args = thoughtRecordActivity.getArguments();

        recyclerView = v.findViewById(R.id.recyclerview);
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

            if(!thoughtRecordActivity.getSwitchedSides()){
                thoughtRecordActivity.activateNextButton();
                thoughtRecordActivity.setSwitchedSides(true);
            }
        });

        tipButton = v.findViewById(R.id.button);
        tipButton.setOnClickListener(view -> thoughtRecordActivity.showTip("Try to find factual evidence that contradicts your negative thought. Try asking yourself these questions to help: \n-If a friend told you they were thinking this way, what would you say to them? \n-Do I have any past experience that might suggest my thought is not 100% true? \n-Are there any small pieces of information I may be ignoring? Or any strengths/qualities about myself? \n-If I were feeling positively, would I still think this way? Why/ why not?"));

        return v;
    }

    @Override
    public void onStop() {
        ((ThoughtRecordActivity)getActivity()).setArguments(args);
        super.onStop();
    }
}