package com.example.mobile_labs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Context context = getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginButton(View v) {
        alert();
    }

    public void toast(String str){
        Toast toast = Toast.makeText(context, str,
                Toast.LENGTH_LONG);
        toast.show();
    }

    public static String createNotificationChannel(Context context) {

        // NotificationChannels are required for Notifications on O (API 26) and above.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // The id of the channel.
            String channelId = "Channel_id";

            // The user-visible name of the channel.
            CharSequence channelName = "Канал1";
            // The user-visible description of the channel.
            String channelDescription = "Канал1 Alert";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
            boolean channelEnableVibrate = true;
            //            int channelLockscreenVisibility = Notification.;

            // Initializes NotificationChannel.
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, channelImportance);
            notificationChannel.setDescription(channelDescription);
            notificationChannel.enableVibration(channelEnableVibrate);
            //            notificationChannel.setLockscreenVisibility(channelLockscreenVisibility);

            // Adds NotificationChannel to system. Attempting to create an existing notification
            // channel with its original values performs no operation, so it's safe to perform the
            // below sequence.
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);

            return channelId;
        } else {
            // Returns null for pre-O (26) devices.
            return null;
        }
    }

    public void notification(){
        Intent notificationintent = new Intent(context, MainActivity.class);
        PendingIntent contentintent = PendingIntent.getActivity(
                context,
                0,
                notificationintent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Resources res = context.getResources();

        String channel_id = createNotificationChannel(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel_id);
        builder.setContentIntent(contentintent)
// маленькое изображение
                .setSmallIcon(R.drawable.small)
// большое изображение
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.big))
// Только дnя Android 4.х, в 5.х и 6.х не сработает
                .setTicker("Фaйл был зашифрован!")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
// заголовок уведомления
                .setContentTitle("My prograrn")
// Текст уведомления дnя Android 5.х, 6.х
                .setContentText("Фaйл был зашифрован!");
        Notification notification = builder.build();
        NotificationManager notificationМanager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationМanager.notify(101, notification);
    }

    public void alert(){
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
// Сообщение диалога
        alt_bld.setMessage("Подключиться к серверу?")
                .setCancelable(false)
                .setPositiveButton("Дa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = Toast.makeText(context, "Это 2 уведомление",
                                Toast.LENGTH_LONG);
                        toast.show();
                    }
                })
// Действие для кнопки Yes
                .setNegativeButton("Heт", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
// Действие для кнопки No
        AlertDialog alert = alt_bld.create();
// Title for AlertDialog
        alert.setTitle("Зaпpoc");
// Icon for AlertDialog
        alert.show();
    }
}