package com.example.miguelhorreohevianotificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnDefinirP;
    Button btnRecuperarP;
    Button btnCrearN;
    private NotificationManager notificador;
    String CHANNEL_ID = "23";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        btnDefinirP = findViewById(R.id.button2);
        btnDefinirP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, OpcionesPreferencias.class);
                startActivity(i);
            }
        });

        btnRecuperarP = findViewById(R.id.button3);
        btnRecuperarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                Log.i("", "Opción 1: " + pref.getBoolean("clave1", false));
                Log.i("", "Opción 2: " + pref.getString("clave2", "No asignada"));
                Log.i("", "Opción 3: " + pref.getString("clave3", "No asignada"));
            }
        });

        btnCrearN = (Button) findViewById(R.id.button4);
        Intent intent = new Intent(this, SegundaActivity.class);
        notificador = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentTitle("Mensaje de alerta")
                .setContentText("Notificacion")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setTicker("AVISO DE NOTIFICACION");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);

        builder.setAutoCancel(true);



        btnCrearN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification n = builder.build();
                builder.setFullScreenIntent(resultPendingIntent, true);
                notificador.notify(1,n);
            }
        });


    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}