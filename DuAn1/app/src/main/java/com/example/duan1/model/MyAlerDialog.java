package com.example.duan1.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;

import com.example.duan1.KhoanChiActivity;
import com.example.duan1.MoneyLimitActivity;
import com.example.duan1.R;

public class MyAlerDialog {
    Context context;
    AlertDialog.Builder alert;
    LayoutInflater inflater;
    public MyAlerDialog(Context context) {
        this.context = context;
        alert = new AlertDialog.Builder(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public AlertDialog.Builder getAlert() {
        View v = inflater.inflate(R.layout.alert_notification, null, false);
        alert.setView(v);
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(context, MoneyLimitActivity.class));
            }
        });
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
        return alert;
    }
}
