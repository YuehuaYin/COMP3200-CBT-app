package com.example.cbtapp.goals;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import androidx.core.app.NotificationCompat;

import com.example.cbtapp.R;
import com.example.cbtapp.exercises.problemsolvingExercise.Solution;
import com.example.cbtapp.exercises.problemsolvingExercise.SolutionAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotificationHelper {

    Context context;
    private static final String NOTIFICATION_CHANNEL_ID = "10001";

    NotificationHelper(Context context) {
        this.context = context;
    }

    void createNotification()
    {

        Intent intent = new Intent(context , GoalsActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context,
                0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Title")
                .setContentText("Content")
                .setAutoCancel(false)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(0 /* Request Code */, mBuilder.build());
    }

    public static void NotificationSetterPopup(Context context, View parent, Solution solution, SolutionAdapter adapter) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.notificationpopup, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, 1000, 1500, false);

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
        List<ToggleButton> buttonList = Arrays.asList(monButton, tuesButton, wedButton, thursButton, friButton, satButton, sunButton);

        // set popup vars to existing notification if one exists
        if (solution.hasNotif){
            timePicker.setHour(solution.notification.hour);
            timePicker.setMinute(solution.notification.minute);
            repeatText.setText(String.valueOf(solution.notification.weeksRepeating));
            for (int i = 0; i < 7; i++) {
                buttonList.get(i).setChecked(solution.notification.daysSelected.get(i));
            }
        }

        // button listeners
        Button cancelButton = popupView.findViewById(R.id.button10);
        cancelButton.setOnClickListener(v -> popupWindow.dismiss());

        Button okButton = popupView.findViewById(R.id.button27);
        okButton.setOnClickListener(v -> {

            Integer repeating = Integer.parseInt(repeatText.getText().toString());
            Integer hour = timePicker.getHour();
            Integer min = timePicker.getMinute();

            List<Boolean> daysSelected = new ArrayList<>();
            daysSelected.add(monButton.isChecked());
            daysSelected.add(tuesButton.isChecked());
            daysSelected.add(wedButton.isChecked());
            daysSelected.add(thursButton.isChecked());
            daysSelected.add(friButton.isChecked());
            daysSelected.add(satButton.isChecked());
            daysSelected.add(sunButton.isChecked());

            // create notification
            solution.notification = new Notification(hour, min, repeating, daysSelected);
            solution.hasNotif = true;

            adapter.notifyDataSetChanged();
            popupWindow.dismiss();
        });
    }
}
