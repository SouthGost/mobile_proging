package com.example.mobile_labs.request;

import com.example.mobile_labs.Entity.User;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("trainings/list")
    public Call<TrainingsRes> getTrainingsList(@Query("page") int page, @Query("limit") int limit);

    @POST("auth/login")
    public Call<Authorization> login(@Body LoginReq loginReq);

    @GET("users/list")
    public Call<UsersRes> getUsers(@Query("search") String search, @Query("page") int page);

    @GET("users/{id}/trainings")
    public Call<TrainingsRes> getUserTrainings(@Path("id") int userId, @Query("page") int page, @Query("limit") int limit);

    @GET("users/{id}")
    public Call<User> getUser(@Path("id") int userId);

}
