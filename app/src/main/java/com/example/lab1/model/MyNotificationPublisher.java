
package com.example.lab1.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyNotificationPublisher extends BroadcastReceiver {

    @Override
    public void onReceive (Context context , Intent intent) {
        // Logica para generar la notificación al recibier el broadcast...
        String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        Toast.makeText(context, phoneNumber, Toast.LENGTH_LONG).show();
    }
}