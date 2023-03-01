package com.example.mobile_labs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mobile_labs.my.MyNavigation;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        LinearLayout layout = findViewById(R.id.menu);
        MyNavigation navigation = new MyNavigation(this,layout, "search");
        layout.addView(navigation);
    }

    public void openMain(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openProfile(View v){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}