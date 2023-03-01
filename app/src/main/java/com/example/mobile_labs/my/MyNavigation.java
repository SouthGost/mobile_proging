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

@SuppressLint("ViewConstructor")
public class MyNavigation extends LinearLayout {
    private Context context;

    public MyNavigation(AppCompatActivity activity, LinearLayout parent, String pageName) {
        super(activity.getApplicationContext());
        context = activity.getApplicationContext();
        View view = LayoutInflater.from(context).inflate(R.layout.my_navigation,parent,true);
        if(!pageName.equals("main")){
            view.findViewById(R.id.main_item).setOnClickListener(item -> {
                Intent intent = new Intent(context, MainActivity.class);
                activity.startActivity(intent);
            });
        }
        if(!pageName.equals("search")) {
            view.findViewById(R.id.search_item).setOnClickListener(item -> {
                Intent intent = new Intent(context, SearchActivity.class);
                activity.startActivity(intent);
            });
        }
        if(!pageName.equals("profile")) {
            view.findViewById(R.id.profile_item).setOnClickListener(item -> {
                Intent intent = new Intent(context, ProfileActivity.class); // LoginActivity ProfileActivity
                activity.startActivity(intent);
            });
        }
    }

}
