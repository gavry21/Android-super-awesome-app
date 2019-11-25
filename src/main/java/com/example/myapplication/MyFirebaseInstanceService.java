package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;
import static android.content.ContentValues.TAG;

    public class MyFirebaseInstanceService extends FirebaseMessagingService {

        String title;
        String body;
        TextView message;

        @Override

        public void onMessageReceived(RemoteMessage remoteMessage) {
            super.onMessageReceived(remoteMessage);

            if(remoteMessage.getData().isEmpty())
            showNotificastion(remoteMessage.getNotification().getTitle(),
                    remoteMessage.getNotification().getBody());
            else
                showNotificastion(remoteMessage.getData());
        }

        private void showNotificastion(Map<String, String> data) {

            String title = data.get("title").toString();
            String body = data.get("body").toString();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            String NOTIFICATION_CHANNEL_ID = "example.myapplication.test";

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                        "Notification", NotificationManager.IMPORTANCE_DEFAULT);

                notificationChannel.setDescription("EDMT CHANNEL");
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.BLUE);
                notificationChannel.setVibrationPattern(new long[]{0, 100, 500, 1000});
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);

            }

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,
                    NOTIFICATION_CHANNEL_ID);
            notificationBuilder.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setContentInfo("Info");

            notificationManager.notify(new Random().nextInt(), notificationBuilder.build());

        }


        private void showNotificastion(String title, String body) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            String NOTIFICATION_CHANNEL_ID = "example.myapplication.test";

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                        "Notification", NotificationManager.IMPORTANCE_DEFAULT);

                notificationChannel.setDescription("EDMT CHANNEL");
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.BLUE);
                notificationChannel.setVibrationPattern(new long[]{0, 100, 500, 1000});
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);

            }

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,
                    NOTIFICATION_CHANNEL_ID);
            notificationBuilder.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setContentInfo("Info");

            notificationManager.notify(new Random().nextInt(), notificationBuilder.build());
        }

        public void onNewToken(String token){

            super.onNewToken(token);
            Log.d("TOKENFIREBASE",token);
            sendRegistrationToServer(token);

    }

        private void sendRegistrationToServer(String token) {
            // TODO: Implement this method to send token to your app server.
        }




}

