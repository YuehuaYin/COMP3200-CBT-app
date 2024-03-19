package com.example.cbtapp.collectables;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cbtapp.NavBar;
import com.example.cbtapp.R;
import com.example.cbtapp.SwipeListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectablesActivity extends AppCompatActivity {

    Boolean collect1;
    Boolean collect2;
    Boolean collect3;
    Boolean collect4;
    Boolean collect5;
    Boolean collect6;
    Boolean collect7;
    Boolean collect8;
    Boolean collect9;
    Integer repeats;
    LinearLayout navBar;
    SwipeListener swipeListener;
    Button repeatRollButton;
    ImageView image1 = findViewById(R.id.imageView13);
    ImageView image2 = findViewById(R.id.imageView12);
    ImageView image3 = findViewById(R.id.imageView11);
    ImageView image4 = findViewById(R.id.imageView7);
    ImageView image5 = findViewById(R.id.imageView6);
    ImageView image6 = findViewById(R.id.imageView5);
    ImageView image7 = findViewById(R.id.imageView);
    ImageView image8 = findViewById(R.id.imageView2);
    ImageView image9 = findViewById(R.id.imageView3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collectables);

        navBar = findViewById(R.id.navBar);
        NavBar.setUpNavbar(this, navBar);
        swipeListener = new SwipeListener(findViewById(R.id.layout), navBar);

        SharedPreferences sp = getSharedPreferences("CollectableProgress", Context.MODE_PRIVATE);

        collect1 = sp.getBoolean("collect1", false);
        collect2 = sp.getBoolean("collect2", false);
        collect3 = sp.getBoolean("collect3", false);
        collect4 = sp.getBoolean("collect4", false);
        collect5 = sp.getBoolean("collect5", false);
        collect6 = sp.getBoolean("collect6", false);
        collect7 = sp.getBoolean("collect7", false);
        collect8 = sp.getBoolean("collect8", false);
        collect9 = sp.getBoolean("collect9", false);

        List<Boolean> boolList = Arrays.asList(collect1, collect2, collect3, collect4, collect5, collect6, collect7, collect8, collect9);
        List<ImageView> imageViewList = Arrays.asList(image1, image2, image3, image4, image5, image6, image7, image8, image9);
        List<Integer> drawableList = Arrays.asList(R.drawable.blobplaceholder, R.drawable.blobplaceholder, R.drawable.blobplaceholder, R.drawable.blobplaceholder, R.drawable.blobplaceholder, R.drawable.blobplaceholder, R.drawable.blobplaceholder, R.drawable.blobplaceholder, R.drawable.blobplaceholder);

        for (int i = 0; i < 9; i++) {
            if (boolList.get(i)){
                imageViewList.get(i).setImageResource(drawableList.get(i));
            }
        }

        repeats = sp.getInt("repeats", 0);
        repeatRollButton = findViewById(R.id.button13);
        updateButton();
        repeatRollButton.setOnClickListener(v -> repeatRoll());

    }

    void repeatRoll(){
        repeats -= 3;
        updateButton();
    }

    void updateButton(){
        repeatRollButton.setText("Exchange repeats for extra rolls \n Repeats: " + repeats + "/3");

        if (repeats < 3) {
            repeatRollButton.setEnabled(false);
            repeatRollButton.setTextColor(Color.GRAY);
        }
    }


}