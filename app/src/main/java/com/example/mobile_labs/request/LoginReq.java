package com.example.mobile_labs.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginReq {

    @SerializedName("login")
    @Expose
    public String login;

    @SerializedName("password")
    @Expose
    public String password;

}
