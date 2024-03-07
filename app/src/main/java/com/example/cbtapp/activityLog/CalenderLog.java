package com.example.cbtapp.activityLog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cbtapp.HomeActivity;
import com.example.cbtapp.R;
import com.example.cbtapp.SwipeListener;
import com.example.cbtapp.exercises.ExercisesHome;
import com.example.cbtapp.stats.StatsPage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class CalenderLog extends AppCompatActivity {
    SwipeListener swipeListener;
    LinearLayout navBar;
    TextView logView;
    Document doc;
    Element rootElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_log);

        logView = findViewById(R.id.textView17);

        /**
         * navBar = findViewById(R.id.navBar);
         *         NavBar.setUpNavbar(this, navBar);
         *         swipeListener = new SwipeListener(findViewById(R.id.layout), navBar);
         */

        try {
            doc = XMLParser.importXmlFile(new File(getFilesDir(), "ActivityLog.xml"));
            rootElement = doc.getDocumentElement();
            logView.setText(XMLParser.getTagAttributeElement(rootElement, "Date", "date"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}