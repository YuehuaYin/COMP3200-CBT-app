package com.example.cbtapp.exercises.situationExercise;

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
import com.example.cbtapp.activityLog.DbCmd;
import com.example.cbtapp.collectables.CollectableRoll;
import com.example.cbtapp.exercises.ExercisesHome;
import com.example.cbtapp.exercises.TipDialog;
import com.example.cbtapp.exercises.thoughtrecordExercise.ThoughtRecordActivity;
import com.example.cbtapp.stats.Stats;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class SituationActivity extends AppCompatActivity {
    String sitText;
    LocalDate date;
    String content;
    ArrayList<Feel> feelings = new ArrayList<>();
    ArrayList<Feel> thoughts = new ArrayList<>();
    int currentStep;
    Button nextButton;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);
        fragmentManager = getSupportFragmentManager();

        currentStep = 1;

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(view -> {

            Class switchTo = null;

            switch (currentStep) {
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

                    Stats.addExercisesDone();
                    if (Stats.addPoints(20)){
                        intent.putExtra("levelup", true);
                    }

                    try {
                        Stats.writeStats(this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    saveActivity();
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

            switch (currentStep) {
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
                case 9:
                    switchTo = Situation5.class;
                    nextButton.setVisibility(View.VISIBLE);
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

    public void switchChallengeFragment(){
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, SituationChallenge.class, null)
                .setReorderingAllowed(true)
                .addToBackStack(null) // Name can be null
                .commit();
        currentStep = 9;
        nextButton.setVisibility(View.GONE);
    }

    public void showTip(String tips) {
        TipDialog tipDialog = new TipDialog();
        tipDialog.setTitle("Tips:");
        tipDialog.setMessage(tips);
        tipDialog.show(getSupportFragmentManager(), "tip dialog");
    }

    public void saveActivity(){
        DbCmd.saveActivityLog(date, "Situation", content, this);
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getSitText() {
        return sitText;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setFeelings(ArrayList<Feel> feelings) {
        this.feelings = feelings;
    }

    public void setThoughts(ArrayList<Feel> thoughts) {
        this.thoughts = thoughts;
    }

    public ArrayList<Feel> getFeelings() {
        return feelings;
    }

    public ArrayList<Feel> getThoughts() {
        return thoughts;
    }

    public void setSitText(String sitText) {
        this.sitText = sitText;
    }
}