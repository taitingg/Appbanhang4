package com.example.appbanhang.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.appbanhang.Activity.MainActivity;
import com.example.appbanhang.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagerReceiver extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        if(message.getNotification()!= null){
            showNotification(message.getNotification().getTitle(),message.getNotification().getBody());
        }
    }

    private void showNotification(String title, String body) {
        Intent i = new Intent(this, MainActivity.class);
        String channelID = "noti";
        i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),channelID)
                .setSmallIcon(R.drawable.eyes)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000,1000,1000,1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);
        builder = builder.setContent(customView(title,body));
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(channelID,"webApp",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(0,builder.build());
    }

    private RemoteViews customView(String titile,String body){
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(),R.layout.notification);
        remoteViews.setTextViewText(R.id.titile_noti,titile);
        remoteViews.setTextViewText(R.id.body_noti,body);
        remoteViews.setImageViewResource(R.id.imgnoti,R.drawable.eyes);
        return remoteViews;
    }

}
