package com.example.cbtapp.activityLog;

import android.content.Context;

import java.time.LocalDate;

public class DbCmd {

    public static void saveActivityLog(LocalDate date, String type, String content, Context context){
        AppDatabase db = AppDatabase.getDbInstance(context.getApplicationContext());
        ActivityLog activityLog = new ActivityLog();
        activityLog.date = date.toString();
        activityLog.type = type;
        activityLog.content = content;
        db.userDao().insertLog(activityLog);
    }
}
