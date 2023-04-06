package com.example.mobile_labs.request;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//192.168.1.252
public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "http://25.58.32.4:8000/api/";
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public JSONPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JSONPlaceHolderApi.class);
    }
}
