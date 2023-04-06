package com.example.mobile_labs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_labs.Adapter.TrainingAdapter;
import com.example.mobile_labs.Entity.Training;
import com.example.mobile_labs.Entity.User;
import com.example.mobile_labs.my.MyNavigation;
import com.example.mobile_labs.request.Authorization;
import com.example.mobile_labs.request.NetworkService;
import com.example.mobile_labs.request.TrainingsRes;
import com.yandex.mapkit.MapKitFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private ArrayList<Training> trainings = new ArrayList<Training>();
    private Context context;
    private TrainingAdapter adapter;
    private int userId;
    private boolean isEnd = true;
    private boolean haveReqWithoutAnswer = false;
    private int page = 1;
    private int limit = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_profile);

        Intent i = getIntent();
        userId = i.getIntExtra("id", -1);

        LinearLayout layout = findViewById(R.id.menu);
        MyNavigation navigation = new MyNavigation(this, layout);
        layout.addView(navigation);

        ListView postContainer = findViewById(R.id.post_container);
        adapter = new TrainingAdapter(this, trainings);
        postContainer.setAdapter(adapter);

        postContainer.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (!haveReqWithoutAnswer && !isEnd && !absListView.canScrollVertically(1)) {
                    toast("Загрузка...");
                    loadNextTraining();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

        if (userId != -1) {
            loadUser();
            loadTrainings();
        } else {
            toast("Ошибка");
        }
    }

    public void loadTrainings() {
        trainings.clear();
        adapter.notifyDataSetChanged();
        isEnd = false;
        page = 1;
        req(true);
    }

    public void loadNextTraining() {
        req(false);
    }

    private void addTrainings(ArrayList<Training> newTrainings) {
        for (Training training_ : newTrainings) {
            trainings.add(training_);
        }
    }

    public void loadUser() {
        NetworkService.getInstance()
                .getJSONApi()
                .getUser(userId)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                        if (response.isSuccessful()) {
                            User user = response.body();

                            TextView userNameTextView = findViewById(R.id.Name);
                            userNameTextView.setText(user.surname + " " + user.name);
                            if (user.id != Authorization.userID) {
                                Button subButton = findViewById(R.id.subButton);
                                subButton.setVisibility(View.VISIBLE);
                                changeButton(Math.random() > 0.5);
                            } else {
                                ImageButton exitButton = findViewById(R.id.exitButton);
                                exitButton.setVisibility(View.VISIBLE);
                            }
                        } else {
                            toast("ОШИБКА");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

                        toast("ОШИБКА");
                    }
                });
    }

    public void req(boolean isFirst) {
        haveReqWithoutAnswer = true;
        NetworkService.getInstance()
                .getJSONApi()
                .getUserTrainings(userId, page, limit)
                .enqueue(new Callback<TrainingsRes>() {
                    @Override
                    public void onResponse(@NonNull Call<TrainingsRes> call, @NonNull Response<TrainingsRes> response) {
                        if (response.isSuccessful()) {
                            TrainingsRes trainingsRes = response.body();
                            int size = trainingsRes.trainings.size();

                            if (size == 0) {
                                if (isFirst) {
                                    toast("Тренировки не найдены");
                                } else {
                                    toast("Больше нет");
                                }
                                isEnd = true;
                            } else {
                                page += 1;
                                if (size < limit) {
                                    isEnd = true;
                                }
                                addTrainings(trainingsRes.trainings);
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            toast("ОШИБКА");
                        }
                        haveReqWithoutAnswer = false;
                    }

                    @Override
                    public void onFailure(@NonNull Call<TrainingsRes> call, @NonNull Throwable t) {
                        toast("ОШИБКА");
                        haveReqWithoutAnswer = false;
                    }
                });
    }

    public void changeButton(boolean isSubscribe){
        Button subButton = findViewById(R.id.subButton);
        if(isSubscribe){
            subButton.setText("ПОДПИСАТЬСЯ");
            subButton.setBackgroundTintList(context.getResources().getColorStateList(R.color.purple_700));
            subButton.setOnClickListener(item -> {
                subscribe();
            });
        } else {
            subButton.setText("ОТПИСАТЬСЯ");
            subButton.setBackgroundTintList(context.getResources().getColorStateList(R.color.gray));
            subButton.setOnClickListener(item -> {
                unsubscribe();
            });
        }
    }

    public void subscribe() {
        toast("Подписка");
        changeButton(false);
    }

    public void unsubscribe() {
        toast("Отписка");
        changeButton(true);
    }

    public void exit(View v) {
        Intent intent = new Intent(context, LoginActivity.class);
        Authorization.userID = null;
        Authorization.accessToken = null;
        Authorization.refreshToken = null;
        this.startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    public void toast(String str) {
        Toast toast = Toast.makeText(this, str,
                Toast.LENGTH_LONG);
        toast.show();
    }
}