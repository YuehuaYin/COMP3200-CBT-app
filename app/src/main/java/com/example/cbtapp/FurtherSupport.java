package com.example.cbtapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cbtapp.activityLog.CalenderLog;
import com.example.cbtapp.exercises.ExercisesHome;
import com.example.cbtapp.stats.StatsPage;

import org.w3c.dom.Text;

public class FurtherSupport extends AppCompatActivity {

    SwipeListener swipeListener;
    LinearLayout navBar;
    TextView emergencyText;
    TextView samaritansText;
    TextView ttText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_further_support);

        navBar = findViewById(R.id.navBar);
        NavBar.setUpNavbar(this, navBar);
        swipeListener = new SwipeListener(findViewById(R.id.layout), navBar);

        emergencyText = findViewById(R.id.textView28);
        SpannableString emergText = new SpannableString("If you are experiencing suicidal thoughts or need help urgently, call 999 now.");
        ClickableSpan clickableSpan1 = new ClickableSpan()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:999"));
                startActivity(intent);
            }
        };
        emergText.setSpan(clickableSpan1, 70, 73, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        emergencyText.setText(emergText);
        emergencyText.setMovementMethod(LinkMovementMethod.getInstance());

        samaritansText = findViewById(R.id.textView31);
        SpannableString samText = new SpannableString("Calling the Samaritans at 116123");
        ClickableSpan clickableSpan2 = new ClickableSpan()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:116123"));
                startActivity(intent);
            }
        };
        samText.setSpan(clickableSpan2, 26, samText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        samaritansText.setText(samText);
        samaritansText.setMovementMethod(LinkMovementMethod.getInstance());

        ttText = findViewById(R.id.textView32);
        SpannableString linkText = new SpannableString("NHS Talking Therapies");
        ClickableSpan clickableSpan3 = new ClickableSpan()
        {
            @Override
            public void onClick(View v) {
                System.out.println("clicked");
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nhs.uk/mental-health/talking-therapies-medicine-treatments/talking-therapies-and-counselling/nhs-talking-therapies/"));
                startActivity(browserIntent);
            }
        };
        linkText.setSpan(clickableSpan3, 0, linkText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ttText.setText(linkText);
        ttText.setMovementMethod(LinkMovementMethod.getInstance());


        /**
         * navBar = findViewById(R.id.navBar);
         *         NavBar.setUpNavbar(this, navBar);
         *         swipeListener = new SwipeListener(findViewById(R.id.layout), navBar);
         */
    }


    /**
     * Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
     * startActivity(browserIntent);
     */
}