package com.example.cbtapp.notifications;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.NavBar;
import com.example.cbtapp.R;
import com.example.cbtapp.SwipeListener;
import com.example.cbtapp.activityLog.DbCmd;
import com.example.cbtapp.exercises.problemsolvingExercise.Solution;
import com.example.cbtapp.exercises.problemsolvingExercise.SolutionAdapter;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {

    Button newNotifButton;
    Button recommededButton;
    RecyclerView recyclerView;
    ArrayList<Solution> notifList;
    SolutionAdapter adapter;
    LinearLayout navBar;
    SwipeListener swipeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        navBar = findViewById(R.id.navBar);
        NavBar.setUpNavbar(this, navBar);
        swipeListener = new SwipeListener(findViewById(R.id.layout), navBar);

        notifList = (ArrayList<Solution>) DbCmd.getAllSolutions(this);
        if(notifList == null){
            notifList = new ArrayList<>();
        }

        recyclerView = findViewById(R.id.recyclerView4);
        adapter = new SolutionAdapter(this, notifList, findViewById(R.id.layout));
        addSolutionComponents();

        newNotifButton = findViewById(R.id.button17);
        newNotifButton.setOnClickListener(view -> {
            updateVars();
            notifList.add(new Solution("New notification"));
            addSolutionComponents();
        });

        recommededButton = findViewById(R.id.button19);
        recommededButton.setOnClickListener(v -> {

        });
    }

    void updateVars(){
        notifList.clear();
        EditText et;

        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View v = recyclerView.getChildAt(i);
            et = v.findViewById(R.id.txt);
            notifList.add(new Solution(et.getText().toString()));
        }
    }

    public void addSolutionComponents(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}