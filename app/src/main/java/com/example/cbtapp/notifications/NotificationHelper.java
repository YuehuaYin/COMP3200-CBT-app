package com.example.cbtapp.notifications;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.example.cbtapp.R;
import com.example.cbtapp.activityLog.DbCmd;
import com.example.cbtapp.exercises.problemsolvingExercise.Solution;
import com.example.cbtapp.exercises.problemsolvingExercise.SolutionAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class NotificationHelper {

    public static void NotificationSetterPopup(Context context, View parent, Solution solution, SolutionAdapter adapter) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.notificationpopup, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, 1000, 1500, false);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);

        TimePicker timePicker = popupView.findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);

        EditText repeatText = popupView.findViewById(R.id.editTextText6);

        ToggleButton monButton = popupView.findViewById(R.id.toggleButton);
        ToggleButton tuesButton = popupView.findViewById(R.id.toggleButton2);
        ToggleButton wedButton = popupView.findViewById(R.id.toggleButton3);
        ToggleButton thursButton = popupView.findViewById(R.id.toggleButton4);
        ToggleButton friButton = popupView.findViewById(R.id.toggleButton5);
        ToggleButton satButton = popupView.findViewById(R.id.toggleButton6);
        ToggleButton sunButton = popupView.findViewById(R.id.toggleButton7);
        List<ToggleButton> buttonList = Arrays.asList(sunButton, monButton, tuesButton, wedButton, thursButton, friButton, satButton);

        // set vars to existing notification if one exists
        if (solution.hasNotif){
            timePicker.setHour(solution.notifhour);
            timePicker.setMinute(solution.notifmin);
            repeatText.setText(String.valueOf(solution.notifrepeating));
            for (int i = 0; i < 7; i++) {
                buttonList.get(i).setChecked(solution.getDaysSelected().get(i));
            }
        } else {
            Calendar calendar = Calendar.getInstance();
            int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
            buttonList.get(currentDay-1).setChecked(true);
        }

        // button listeners
        Button cancelButton = popupView.findViewById(R.id.button10);
        if (solution.hasNotif){
            cancelButton.setText("Delete");
        }
        cancelButton.setOnClickListener(v -> {
            if (solution.hasNotif){
                solution.resetNotif();
                AlarmScheduler alarmScheduler = new AlarmScheduler(context);
                alarmScheduler.cancel(solution);

                DbCmd.deleteSolution(solution.uid, context);
                adapter.notifyDataSetChanged();
            }
            popupWindow.dismiss();
        });

        Button okButton = popupView.findViewById(R.id.button27);
        okButton.setOnClickListener(v -> {

            Integer repeating = Integer.parseInt(repeatText.getText().toString());
            Integer hour = timePicker.getHour();
            Integer min = timePicker.getMinute();

            List<Boolean> daysSelected = new ArrayList<>();
            daysSelected.add(sunButton.isChecked());
            daysSelected.add(monButton.isChecked());
            daysSelected.add(tuesButton.isChecked());
            daysSelected.add(wedButton.isChecked());
            daysSelected.add(thursButton.isChecked());
            daysSelected.add(friButton.isChecked());
            daysSelected.add(satButton.isChecked());

            // create notification
            solution.setNotifVars(hour, min, repeating, daysSelected);
            solution.hasNotif = true;
            AlarmScheduler alarmScheduler = new AlarmScheduler(context);
            alarmScheduler.schedule(solution);
            if (solution.hasNotif){
                DbCmd.deleteSolution(solution.uid, context);
            }
            DbCmd.saveSolution(solution, context);

            adapter.notifyDataSetChanged();
            popupWindow.dismiss();
        });
    }
}
