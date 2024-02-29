package com.example.cbtapp.exercises;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class TipDialog extends AppCompatDialogFragment {

    String message;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Tips:").setMessage(message).setPositiveButton("OK", (dialogInterface, i) -> {

        });
        return builder.create();
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
