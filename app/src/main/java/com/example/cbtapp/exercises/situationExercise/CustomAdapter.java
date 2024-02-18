package com.example.cbtapp.exercises.situationExercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.cbtapp.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    // reference: https://www.youtube.com/watch?v=aUFdgLSEl0g
    Context context;
    ArrayList<String> strings;
    ArrayList<Integer> integers;
    LayoutInflater inflater;
    public CustomAdapter(Context _context, ArrayList<String> _strings, ArrayList<Integer> _integers){
        context = _context;
        strings = _strings;
        integers = _integers;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_feeling, null);
        EditText etText = view.findViewById(R.id.editTextfeeling);
        SeekBar skbar = view.findViewById(R.id.seekBar3);
        etText.setText(strings.get(i));
        skbar.setProgress(integers.get(i));
        return view;
    }
}
