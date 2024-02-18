package com.example.cbtapp.exercises.situationExercise;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.cbtapp.HomeActivity;
import com.example.cbtapp.R;
import com.example.cbtapp.exercises.ExercisesHome;
import com.example.cbtapp.exercises.TipDialog;

import java.time.LocalDate;
import java.util.ArrayList;

public class SituationActivity extends AppCompatActivity {

    String sitText;
    LocalDate date;
    ArrayList<String> feelings = new ArrayList<>();
    ArrayList<Integer> feelingIntensities = new ArrayList<>();
    ArrayList<String> thoughts = new ArrayList<>();
    ArrayList<Integer> thoughtIntensities = new ArrayList<>();
    int currentStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);
        FragmentManager fragmentManager = getSupportFragmentManager();

        currentStep = 1;

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(view -> {

            Class switchTo = null;

            switch (currentStep){
                case 1:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        date = LocalDate.now();
                    }

                    switchTo = Situation2.class;
                    currentStep++;
                    break;
                case 2:
                    switchTo = Situation3.class;
                    currentStep++;
                    break;
                case 3:
                    switchTo = Situation4.class;
                    currentStep++;
                    break;
                case 4:
                    switchTo = Situation5.class;
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
                    switchTo = Situation1.class;
                    currentStep--;
                    break;
                case 3:
                    switchTo = Situation2.class;
                    currentStep--;
                    break;
                case 4:
                    switchTo = Situation3.class;
                    currentStep--;
                    break;
                case 5:
                    switchTo = Situation4.class;
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

    public String getSitText() {
        return sitText;
    }

    public LocalDate getDate() {
        return date;
    }

    public ArrayList<String> getFeelings() {
        return feelings;
    }

    public ArrayList<Integer> getFeelingIntensities() {
        return feelingIntensities;
    }

    public ArrayList<String> getThoughts() {
        return thoughts;
    }

    public ArrayList<Integer> getThoughtIntensities() {
        return thoughtIntensities;
    }

    public void setSitText(String sitText) {
        this.sitText = sitText;
    }

    public void setFeelings(ArrayList<String> fs){
        feelings = fs;
    }

    public void setFeelingIntensities(ArrayList<Integer> is){
        feelingIntensities = is;
    }

    public void setThoughts(ArrayList<String> thoughts) {
        this.thoughts = thoughts;
    }

    public void setThoughtIntensities(ArrayList<Integer> thoughtIntensities) {
        this.thoughtIntensities = thoughtIntensities;
    }
}