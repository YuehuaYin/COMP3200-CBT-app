package com.example.cbtapp.collectables;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.cbtapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CollectableRoll {

    public static void GACHATIME(Context context, View parent, String title) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.gacha, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, 800, 1200, false);

        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

        // get vars from sp
        SharedPreferences sp = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        Boolean collect1 = sp.getBoolean("collect1", false);
        Boolean collect2 = sp.getBoolean("collect2", false);
        Boolean collect3 = sp.getBoolean("collect3", false);
        Boolean collect4 = sp.getBoolean("collect4", false);
        Boolean collect5 = sp.getBoolean("collect5", false);
        Boolean collect6 = sp.getBoolean("collect6", false);
        Boolean collect7 = sp.getBoolean("collect7", false);
        Boolean collect8 = sp.getBoolean("collect8", false);
        Boolean collect9 = sp.getBoolean("collect9", false);

        List<Boolean> boolList = Arrays.asList(collect1, collect2, collect3, collect4, collect5, collect6, collect7, collect8, collect9);

        // time to gamble
        Integer rollOutcome;
        // 0-10 = 1, 10-25 = 2, 25-30 = 3, 30-50 = 4, 50-65 = 5, 65-70 = 6, 70-80 = 7, 80-90 = 8, 90-100 = 9
        Random random = new Random();
        Integer rollValue = random.nextInt(100);
        if (rollValue < 10){
            rollOutcome = 1;
        }
        else if (rollValue < 25){
            rollOutcome = 2;
        }
        else if (rollValue < 30){
            rollOutcome = 3;
        }
        else if (rollValue < 50){
            rollOutcome = 4;
        }
        else if (rollValue < 65){
            rollOutcome = 5;
        }
        else if (rollValue < 70){
            rollOutcome = 6;
        }
        else if (rollValue < 80){
            rollOutcome = 7;
        }
        else if (rollValue < 90){
            rollOutcome = 8;
        }
        else {
            rollOutcome = 9;
        }

        editor.putBoolean("collect" + rollOutcome, true);

        // calculate if roll is a repeat
        Boolean isRepeat = false;
        if (boolList.get(rollOutcome-1)){
            isRepeat = true;
            Integer repeats = sp.getInt("repeats", 0);
            editor.putInt("repeats", repeats+1);
        }

        editor.commit();

        // set UI elements
        List<Integer> drawableList = Arrays.asList(R.drawable.blobplaceholder, R.drawable.blobplaceholder, R.drawable.blobplaceholder, R.drawable.blobplaceholder, R.drawable.blobplaceholder, R.drawable.blobplaceholder, R.drawable.blobplaceholder, R.drawable.blobplaceholder, R.drawable.blobplaceholder);

        TextView titleText = popupView.findViewById(R.id.textView25);
        titleText.setText(title);

        ImageView gachaImage = popupView.findViewById(R.id.imageView4);
        gachaImage.setImageResource(drawableList.get(rollOutcome-1));

        TextView gachaText = popupView.findViewById(R.id.textView22);
        if (isRepeat){
            gachaText.setText("Collectable " + rollOutcome + " (repeat)");
        }
        else {
            gachaText.setText("Collectable " + rollOutcome + " (new)");
        }

        Button okButton = popupView.findViewById(R.id.button16);
        okButton.setOnClickListener(v -> popupWindow.dismiss());

        Button toCollectablesButton = popupView.findViewById(R.id.button15);
        toCollectablesButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, CollectablesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }
}
