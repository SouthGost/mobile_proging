package com.example.mobile_labs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.content.Context;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mobile_labs.my.MyNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        LinearLayout layout = findViewById(R.id.menu);
        MyNavigation navigation = new MyNavigation(this,layout, "main");
        layout.addView(navigation);

        toast("Нет подключения к серверу");


//        binding = ActivityMainBinding.inflate((getLayoutInflater()));
//
//        Context context = getApplicationContext();
//
//        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
//            Log.d("CREATION","qwe " + item.getItemId());
//            switch (item.getItemId()){
//                case R.id.home:
//                    Log.d("CREATION","home");
//                    break;
//                case R.id.search:
//                    Log.d("CREATION","search");
//                    openSearch(item.getActionView());
//
//                    break;
//                case R.id.profile:
//                    Log.d("CREATION","profile");
//                    openProfile(item.getActionView());
//
//                    break;
//
//            }
//            return true;
//        });

    }


    public void toast(String str){
        Toast toast = Toast.makeText(context, str,
                Toast.LENGTH_LONG);
        toast.show();
    }


    public void openProfile(View v) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void openSearch(View v) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}