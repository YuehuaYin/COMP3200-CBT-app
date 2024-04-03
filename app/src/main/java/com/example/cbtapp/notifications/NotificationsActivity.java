package com.example.cbtapp.notifications;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
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

        recyclerView = findViewById(R.id.recyclerview);
        addSolutionComponents();

        newNotifButton = findViewById(R.id.button17);
        newNotifButton.setOnClickListener(view -> {
            notifList = (ArrayList<Solution>) DbCmd.getAllSolutions(this);
            notifList.add(new Solution("New notification"));
            adapter = new SolutionAdapter(this, notifList, findViewById(R.id.layout));
            addSolutionComponents();
        });
    }

    public void addSolutionComponents(){
        adapter = new SolutionAdapter(this, notifList, findViewById(R.id.layout));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}