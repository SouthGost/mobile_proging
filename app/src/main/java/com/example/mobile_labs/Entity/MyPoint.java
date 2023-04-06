package com.example.mobile_labs.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyPoint {
    @SerializedName("training_id")
    @Expose
    public int trainingId;

    @SerializedName("part")
    @Expose
    public int part;

    @SerializedName("latitude")
    @Expose
    public double latitude;

    @SerializedName("longitude")
    @Expose
    public double longitude;

    @SerializedName("time")
    @Expose
    public long time;

}
