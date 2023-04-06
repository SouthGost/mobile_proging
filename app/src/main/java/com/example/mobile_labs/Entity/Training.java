package com.example.mobile_labs.Entity;

import com.yandex.mapkit.geometry.Point;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Training {
    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("user_id")
    @Expose
    public int userId;

    @SerializedName("distance")
    @Expose
    public int distance;

    @SerializedName("time")
    @Expose
    public int time;

    @SerializedName("temp")
    @Expose
    public int temp;

    @SerializedName("start_time")
    @Expose
    public long startTime;

    @SerializedName("user")
    @Expose
    public User user;

    @SerializedName("points")
    @Expose
    public ArrayList<MyPoint> points;


    public List<Point> getTrack(){
        ArrayList<Point> track = new ArrayList<Point>();

        for (MyPoint p: points) {
            track.add(new Point(p.latitude, p.longitude));
        }

        return track;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", userId=" + userId +
                ", distance=" + distance +
                ", time=" + time +
                ", temp=" + temp +
                ", startTime=" + startTime +
                '}';
    }
}
