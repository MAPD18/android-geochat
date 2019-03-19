package com.zv.geochat.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.zv.geochat.ChatActivity;
import static com.zv.geochat.Constants.CHANNEL_DESCRIPTION;
import static com.zv.geochat.Constants.CHANNEL_ID;
import static com.zv.geochat.Constants.CHANNEL_NAME;

public class NotificationDecorator {

    private static final String TAG = "NotificationDecorator";
    private static final int NOTIFICATION_ID = 149;
    private final Context context;
    private final NotificationManager notificationMgr;
    private final MessageNotifierConfig messageNotifierConfig;

    public NotificationDecorator(Context context, NotificationManager notificationManager) {
        this.context = context;
        this.notificationMgr = notificationManager;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(CHANNEL_DESCRIPTION);
            notificationMgr.createNotificationChannel(channel);
        }
        this.messageNotifierConfig = new MessageNotifierConfig(context);
    }

    public void displaySimpleNotification(String title, String contentText) {
        if (messageNotifierConfig.isPlaySound()) {
            Intent intent = new Intent(context, ChatActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            // notification message
            try {


                Notification noti = new Notification.Builder(context)
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle(title)
                        .setContentText(contentText)
                        .setContentIntent(contentIntent)
                        .setAutoCancel(true)
                        .setSound(messageNotifierConfig.getSoundUri())
                        .setVibrate(messageNotifierConfig.getVibratePattern())
                        .setLights(Color.BLUE, 1000, 1000)
                        .build();

                noti.flags |= Notification.FLAG_AUTO_CANCEL;
                notificationMgr.notify(0, noti);
            } catch (IllegalArgumentException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    public void displayExpandableNotification(String title, String contentText) {
        if (messageNotifierConfig.isPlaySound()) {
            Intent intent = new Intent(context, ChatActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);

            // notification message
            try {


                Notification noti = new Notification.Builder(context)
                        .setSmallIcon(R.drawable.ic_message)
                        .setContentTitle(title)
                        .setContentText(contentText)
                        .setContentIntent(contentIntent)
                        .setAutoCancel(true)
                        .setSound(messageNotifierConfig.getSoundUri())
                        .setVibrate(messageNotifierConfig.getVibratePattern())
                        .setLights(Color.BLUE, 1000, 1000)
                        .setStyle(new Notification.BigTextStyle().bigText(contentText))
                        .build();

                noti.flags |= Notification.FLAG_AUTO_CANCEL;
                notificationMgr.notify(0, noti);
            } catch (IllegalArgumentException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }
