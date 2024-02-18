package com.example.cbtapp.exercises.thoughtrecordExercise;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cbtapp.HomeActivity;
import com.example.cbtapp.R;
import com.example.cbtapp.exercises.ExercisesHome;
import com.example.cbtapp.exercises.TipDialog;
import com.example.cbtapp.exercises.situationExercise.SituationChallenge;

import java.time.LocalDate;
import java.util.ArrayList;

public class ThoughtRecordActivity extends AppCompatActivity {

    String thoughtText;
    LocalDate date;
    int belief = 5;
    String balancedThought;
    int balancedBelief = 5;
    int currentStep;
    FragmentManager fragmentManager;
    ArrayList<Argument> arguments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                thoughtText = extras.getString("Thought");
                belief = extras.getInt("Belief");
            }
        }

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, ThoughtRecord1.class, null)
                .setReorderingAllowed(true)
                .addToBackStack(null) // Name can be null
                .commit();

        currentStep = 1;

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(view -> {

            Class switchTo = null;

            switch (currentStep){
                case 1:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        date = LocalDate.now();
                    }

                    switchTo = ThoughtRecord2.class;
                    currentStep++;
                    break;
                case 2:
                    switchTo = ThoughtRecord3.class;
                    currentStep++;
                    break;
                case 3:
                    switchTo = ThoughtRecord4.class;
                    currentStep++;
                    break;
                case 4:
                    switchTo = ThoughtRecord5.class;
                    nextButton.setText("Finish");
                    currentStep++;
                    break;
                case 5:
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                    break;
            }

            if (switchTo != null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, switchTo, null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null) // Name can be null
                        .commit();
            }
        });


        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> {

            Class switchTo = null;

            switch (currentStep){
                case 1:
                    Intent intent = new Intent(this, ExercisesHome.class);
                    startActivity(intent);
                    break;
                case 2:
                    switchTo = ThoughtRecord1.class;
                    currentStep--;
                    break;
                case 3:
                    switchTo = ThoughtRecord2.class;
                    currentStep--;
                    break;
                case 4:
                    switchTo = ThoughtRecord3.class;
                    currentStep--;
                    break;
                case 5:
                    switchTo = ThoughtRecord4.class;
                    nextButton.setText("Next");
                    currentStep--;
                    break;
            }

            if (switchTo != null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, switchTo, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // Name can be null
                        .commit();
            }
        });
    }

    public void showTip(String tips) {
        TipDialog tipDialog = new TipDialog();
        tipDialog.setMessage(tips);
        tipDialog.show(getSupportFragmentManager(), "tip dialog");
    }

    public ArrayList<Argument> getArguments() {
        return arguments;
    }

    public void setArguments(ArrayList<Argument> arguments) {
        this.arguments = arguments;
    }

    public String getThoughtText() {
        return thoughtText;
    }

    public void setThoughtText(String thoughtText) {
        this.thoughtText = thoughtText;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getBelief() {
        return belief;
    }

    public void setBelief(int belief) {
        this.belief = belief;
    }

    public String getBalancedThought() {
        return balancedThought;
    }

    public void setBalancedThought(String balancedThought) {
        this.balancedThought = balancedThought;
    }

    public int getBalancedBelief() {
        return balancedBelief;
    }

    public void setBalancedBelief(int balancedBelief) {
        this.balancedBelief = balancedBelief;
    }
}
