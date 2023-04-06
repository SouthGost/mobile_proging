package com.example.mobile_labs.request;

import com.example.mobile_labs.Entity.Training;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TrainingsRes {
    @SerializedName("trainings")
    @Expose
    public ArrayList<Training> trainings;
}
