package com.example.mobile_labs.my;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_labs.LoginActivity;
import com.example.mobile_labs.MainActivity;
import com.example.mobile_labs.ProfileActivity;
import com.example.mobile_labs.R;
import com.example.mobile_labs.SearchActivity;
import com.example.mobile_labs.request.Authorization;

@SuppressLint("ViewConstructor")
public class MyNavigation extends LinearLayout {
    private Context context;

    public MyNavigation(AppCompatActivity activity, LinearLayout parent) {
        super(activity.getApplicationContext());
        context = activity.getApplicationContext();
        View view = LayoutInflater.from(context).inflate(R.layout.my_navigation, parent, true);

        view.findViewById(R.id.main_item).setOnClickListener(item -> {
            Intent intent = new Intent(context, MainActivity.class);
            activity.startActivity(intent);
        });


        view.findViewById(R.id.search_item).setOnClickListener(item -> {
            Intent intent = new Intent(context, SearchActivity.class);
            activity.startActivity(intent);
        });


        view.findViewById(R.id.profile_item).setOnClickListener(item -> {
            Intent intent = new Intent(context, ProfileActivity.class); // LoginActivity ProfileActivity
            intent.putExtra("id", Authorization.userID);
            activity.startActivity(intent);
        });

//        view.findViewById(R.id.exit_item).setOnClickListener(item -> {
//            Intent intent = new Intent(context, LoginActivity.class); // LoginActivity ProfileActivity
//            Authorization.userID = null;
//            Authorization.accessToken = null;
//            Authorization.refreshToken = null;
//            activity.startActivity(intent);
//        });
    }

}
