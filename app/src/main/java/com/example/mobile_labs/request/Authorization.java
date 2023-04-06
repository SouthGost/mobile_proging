package com.example.mobile_labs.request;

import com.example.mobile_labs.Entity.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authorization {

    public static String accessToken;
    public static String refreshToken;
    public static Integer userID = 15;

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("accessToken")
    @Expose
    public String accessToken_;

    @SerializedName("refreshTokens")
    @Expose
    public String refreshTokens_;

}
