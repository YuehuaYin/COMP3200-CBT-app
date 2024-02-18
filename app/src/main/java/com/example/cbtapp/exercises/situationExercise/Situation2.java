package com.example.cbtapp.exercises.situationExercise;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.cbtapp.R;

public class Situation2 extends Fragment {

    View v;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_situation2, container, false);

        editText = v.findViewById(R.id.editTextText2);
        editText.setText(((SituationActivity)getActivity()).getSitText());

        return v;
    }

    @Override
    public void onStop() {
        ((SituationActivity) getActivity()).setSitText(editText.getText().toString());
        super.onStop();
    }
}