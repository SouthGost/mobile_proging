package com.example.mobile_labs.request;

import com.example.mobile_labs.Entity.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UsersRes {
    @SerializedName("users")
    @Expose
    public ArrayList<User> users;
}
