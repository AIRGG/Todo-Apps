package com.airgg.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airgg.myfirstapplication.utils.DialogFactory;

import java.util.Timer;
import java.util.TimerTask;

import static com.airgg.myfirstapplication.utils.DialogFactory.createGenericErrorDialog;
import static com.airgg.myfirstapplication.utils.DialogFactory.createProgressDialog;
import static com.airgg.myfirstapplication.utils.DialogFactory.createSimpleOkErrorDialog;

public class MainActivity extends AppCompatActivity {

    public final static int NOTIF_ID = 30;
    private final static String CHANNEL_ID = "myChn01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btnTampil);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText txtInputNama = (EditText) findViewById(R.id.txtInpNama);
                TextView txtNama = (TextView) findViewById(R.id.txtNama);
                txtNama.setText(txtInputNama.getText());
            }
        });

        createNotifChannel();
        Intent notificationIntent = new Intent(this, AplikasikuActivity.class); // ganti ke chanel lain
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationCompat.Builder bldNotif = new NotificationCompat.Builder(this, "myTodoApps")
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle("Cobain1")
                .setContentText("ini text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);
//        bldNotif.addAction(R.drawable.ic_baseline_notifications_active_24, "Bukabaru", )

        final NotificationManagerCompat mng = NotificationManagerCompat.from(this);

        Button btn_notif = (Button)findViewById(R.id.btn_notif);
        Button btn_openact = (Button)findViewById(R.id.btn_openact);
        TextView lbl_time = findViewById(R.id.lbl_cobatime);
        btn_notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mng.notify(100, bldNotif.build());
//                addNotification();
//                for(int i = 0; i < 5; i++){
//                    cobaNotif();
//                    Log.d("Main", "aaa "+i);
//                }
                cobaNotif();
                cobaNotif();
                cobaNotif();
//                cobaNotif();
//                bukaBaru();
            }
        });
        btn_openact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bukaBaru();
//                alertCoba();
            }
        });

//        final Handler handler = new Handler();
//        final Timer timer = new Timer(false);
//        final TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        // Do whatever you want
//                        lbl_time.setText();
//
//                    }
//                });
//            }
//        };
//        timer.schedule(timerTask, 1000); // 1000 = 1 second.
    }


    private void alertCoba() {
        createSimpleOkErrorDialog(this, "apa", "iniii").show();
        createProgressDialog(this, "tunggu coy...").show();
        createGenericErrorDialog(this, "aaaa").show();
    }
    private void bukaBaru() {
        Intent intent = new Intent(this, CobaListView.class);
        startActivity(intent);
    }
    private void cobaNotif() {
        createNotifChannel();

        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent mainPI = PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_ONE_SHOT);

        Intent apku = new Intent(this, AplikasikuActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent akuPI = PendingIntent.getActivity(this, 0, apku, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder bldNotif = new NotificationCompat.Builder(this, "myTodoApps");
        bldNotif.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
        bldNotif.setContentTitle("AAA");
        bldNotif.setContentText("ini adalah content");
        bldNotif.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        bldNotif.setAutoCancel(true);
        bldNotif.setContentIntent(akuPI);
        bldNotif.addAction(R.drawable.ic_baseline_notifications_active_24, "BukaCoba", akuPI);

        NotificationManagerCompat notifMng = NotificationManagerCompat.from(this);
        notifMng.notify(NOTIF_ID, bldNotif.build());


//        Intent notificationIntent = new Intent(MainActivity.this, AplikasikuActivity.class);
//        // set intent so it does not start a new activity
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        PendingIntent intent = PendingIntent.getActivity(MainActivity.this, 0, notificationIntent, 0);
//
//        NotificationManager notificationManager = (NotificationManager) MainActivity.this
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification.Builder notification = new Notification.Builder(MainActivity.this)
//                .setContentTitle("Message Received")
//                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
//                .setAutoCancel(true)
//                .setContentIntent(intent);
//
//        Notification notificationn = notification.getNotification();
//        notificationManager.notify(1, notificationn);
//        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        Intent intent = new Intent(this, AplikasikuActivity.class);
//        intent.putExtra("payload", "aaa");
//        intent.setAction(Long.toString(System.currentTimeMillis()));
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//                intent, PendingIntent.FLAG_CANCEL_CURRENT);
//
//        Notification.Builder notification = new Notification.Builder(this)
//                .setContentTitle("Message Received")
//                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
//                .setContentText("apapun")
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true);
//
//        Notification notificationn = notification.build();
//        notificationManager.notify(112, notificationn);
    }
    private void addNotification() {
        NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(MainActivity.this);
        mBuilder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
        mBuilder.setContentTitle("Notification Alert, Click Me!");
        mBuilder.setContentText("Hi,This notification for you let me check");
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent conPendingIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(conPendingIntent);

        NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,mBuilder.build());
        Toast.makeText(MainActivity.this, "Notification", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, (String) "apapun",
//                Toast.LENGTH_LONG).show();
//        NotificationCompat.Builder builder =
//                new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
//                        .setContentTitle("Notifications Example")
//                        .setContentText("This is a test notification");
//
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(contentIntent);
//
//        // Add as notification
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(5, builder.build());
//        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//        Notification notification = new Notification(R.drawable.ic_baseline_notifications_active_24, "HAI", 3);
//
//        Intent notificationIntent = new Intent(this, AplikasikuActivity.class);
//
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//        PendingIntent intent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//
//        notification.setLatestEventInfo(context, title, message, intent);
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;
//        notificationManager.notify(0, notification);
    }

    private void createNotifChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Apapun1";
            String desc = "ini adalah deskripsi yang panjang";
            NotificationChannel chn = new NotificationChannel("myTodoApps", name, NotificationManager.IMPORTANCE_DEFAULT);
            chn.setDescription(desc);

            NotificationManager mng = getSystemService(NotificationManager.class);
            mng.createNotificationChannel(chn);

        }
    }
}