
package org.apnplace.gcm;


import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.apnplace.R;



public class GcmMessageHandler extends IntentService {
    String mes;
    private Handler handler;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    public GcmMessageHandler() {
        super("GcmMessageHandler");
    }
    //GCM Handler for push Notifications
    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
    }
    public static final String TAG = "GCMNotificationIntentService";
    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);

        NotificationCompat.Builder mbuilder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.mod2)
                .setContentTitle(extras.getString("title"))
                .setContentText(extras.getString("message"));


        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        Notification notification=mbuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        mNotificationManager.notify(12325, mbuilder.build());

        mes = extras.getString("title");
        //showToast();
        System.out.println("GCM Received : (" + messageType + ")  " + extras.getString("title"));

        GcmBroadcastReceiver.completeWakefulIntent(intent);



    }

   /* public void showToast(){
        handler.post(new Runnable() {
            public void run() {
                System.out.println("Handler show Toast");

                Toast.makeText(getApplicationContext(), mes, Toast.LENGTH_LONG).show();
            }
        });

    }*/

}

