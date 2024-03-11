package com.example.cbtapp.activityLog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cbtapp.R;

import java.util.ArrayList;
public class LogListAdapter extends RecyclerView.Adapter<LogListAdapter.LogHolder> {

    // reference: https://www.youtube.com/watch?v=aUFdgLSEl0g
    Context context;
    ArrayList<ActivityLog> activityLogs;

    public LogListAdapter(Context context, ArrayList<ActivityLog> activityLogs) {
        this.context = context;
        this.activityLogs = activityLogs;
    }

    @Override
    public LogHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LogHolder(LayoutInflater.from(context).inflate(R.layout.activity_log, parent, false));
    }

    @Override
    public void onBindViewHolder(LogHolder holder, int position) {
        holder.dateText.setText(activityLogs.get(position).date.toString());
        holder.typeText.setText(activityLogs.get(position).type);
        holder.contentText.setText(activityLogs.get(position).content);
    }

    @Override
    public int getItemCount() {
        return activityLogs.size();
    }

    public static class LogHolder extends RecyclerView.ViewHolder {

        TextView dateText;
        TextView typeText;
        TextView contentText;

        public LogHolder(View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.textView18);
            typeText = itemView.findViewById(R.id.textView17);
            contentText = itemView.findViewById(R.id.textView19);
        }
    }
}
