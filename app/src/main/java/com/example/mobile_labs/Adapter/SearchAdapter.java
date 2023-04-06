package com.example.mobile_labs.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mobile_labs.Entity.User;
import com.example.mobile_labs.ProfileActivity;
import com.example.mobile_labs.R;
import com.example.mobile_labs.request.Authorization;

import java.util.List;

public class SearchAdapter extends BaseAdapter {
    Activity activity;
    LayoutInflater LInflater;
    List<User> objects;

    public SearchAdapter(Activity context, List<User> users) {
        this.activity = context;
        objects = users;
        LInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int pos) {
        return objects.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return getUser(pos).login.hashCode();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        View view = convertView;
        User user = getUser(pos);
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.my_search_profile, parent, false);
        }

        view.setOnClickListener(item -> {
            Intent intent = new Intent(activity, ProfileActivity.class); // LoginActivity ProfileActivity
            intent.putExtra("id", user.id);
            activity.startActivity(intent);
        });

        TextView usernameText = (TextView) view.findViewById(R.id.userName);

        usernameText.setText(user.surname + " " + user.name);

        return view;
    }


    User getUser(int pos) {
        return (User) getItem(pos);
    }

    List<User> getBox() {
        //Поменять
        return objects;
    }
}
