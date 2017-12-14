package com.example.yanina.mysong.View;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.yanina.mysong.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;



public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG = "noticias";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String from = remoteMessage.getFrom();
        Log.d(TAG , "Mensaje recibido de "+ from);
        if(remoteMessage.getNotification() != null){

            Log.d(TAG,"Notificacion; " + remoteMessage.getNotification().getBody());
            mostrarNotificacion(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }

        if(remoteMessage.getData().size()> 0){
            Log.d(TAG,"Data; " + remoteMessage.getData());
        }
    }

    private void mostrarNotificacion(String title, String body) {

        Intent internet = new Intent(this,MainActivity.class);
        internet.setFlags(internet.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, internet,PendingIntent.FLAG_ONE_SHOT);
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificacionBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_create_black_24dp)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntent);

        NotificationManager notificationMnager =( NotificationManager ) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationMnager.notify(0,notificacionBuilder.build());
    }
}
