package com.example.mobile_labs;

import android.content.Intent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mobile_labs.Adapter.TrainingAdapter;
import com.example.mobile_labs.request.TrainingsRes;
import com.example.mobile_labs.my.MyNavigation;
import com.example.mobile_labs.Entity.Training;
import com.example.mobile_labs.request.NetworkService;
import com.yandex.mapkit.MapKitFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private ArrayList<Training> trainings = new ArrayList<Training>();
    private TrainingAdapter adapter;
    private static boolean check = false;
    private boolean isEnd = true;
    private boolean haveReqWithoutAnswer = false;
    private int limit = 3;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!check) {
            MapKitFactory.setApiKey("8dc53918-3175-4d66-bb26-76d1977bd63c");
            check = true;
        }
        context = this;

        MapKitFactory.initialize(context);
        setContentView(R.layout.activity_main);


        LinearLayout layout = findViewById(R.id.menu);
        MyNavigation navigation = new MyNavigation(this, layout);
        layout.addView(navigation);

        ListView postContainer = findViewById(R.id.post_container);
        adapter = new TrainingAdapter(context, trainings);
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

        loadTrainings();
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

    public void req(boolean isFirst) {
        haveReqWithoutAnswer = true;
        NetworkService.getInstance()
                .getJSONApi()
                .getTrainingsList(page, limit)
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
                        toast("ERRORRRRR");
                        haveReqWithoutAnswer = false;
                    }
                });
    }

    public void toast(String str) {
        Toast toast = Toast.makeText(context, str,
                Toast.LENGTH_LONG);
        toast.show();
    }


    @Override
    protected void onStop() {
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }


}