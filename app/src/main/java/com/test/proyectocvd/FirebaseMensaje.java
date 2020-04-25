package com.test.proyectocvd;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMensaje extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        if(remoteMessage.getData().size() > 0){
            mostrarNotificacion(remoteMessage.getData().get("title"),remoteMessage.getData().get("message"));
        }
        if(remoteMessage.getNotification() != null){
            mostrarNotificacion(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }
    }

    private RemoteViews getNotificacionPersonalizada(String titulo, String mensaje){
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(),R.layout.notification);
        remoteViews.setTextViewText(R.id.txtTitulo,titulo);
        remoteViews.setTextViewText(R.id.txtMensaje,mensaje);
        remoteViews.setImageViewResource(R.id.icono,R.drawable.logo);
        return remoteViews;
    }

    public void mostrarNotificacion(String titulo, String mensaje){
        Log.d("=====>"," "+titulo+" "+mensaje);
        try {
            Intent intent=new Intent(this, MainActivity.class);
            String channel_id="web_app_channel1";
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
            Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),channel_id)
                    .setSmallIcon(R.drawable.logo)
                    .setSound(uri)
                    .setAutoCancel(true)
                    .setVibrate(new long[]{1000,1000,1000,1000,1000})
                    .setOnlyAlertOnce(true)
                    .setContentIntent(pendingIntent);

            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
                builder=builder.setContent(getNotificacionPersonalizada(titulo,mensaje));
            }
            else{
                builder=builder.setContentTitle(titulo)
                        .setContentText(mensaje)
                        .setSmallIcon(R.drawable.logo);
            }
            NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                NotificationChannel notificationChannel=new NotificationChannel(channel_id,"web_app",NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.setSound(uri,null);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            notificationManager.notify(1,builder.build());
        }catch (Exception e){
            Log.d("=====>",e.toString());
        }

    }
}
