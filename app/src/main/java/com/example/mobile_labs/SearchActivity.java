package com.example.mobile_labs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_labs.Adapter.SearchAdapter;
import com.example.mobile_labs.Entity.User;
import com.example.mobile_labs.my.MyNavigation;
import com.example.mobile_labs.request.Authorization;
import com.example.mobile_labs.request.NetworkService;
import com.example.mobile_labs.request.UsersRes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private Activity activity;
    private ArrayList<User> users;
    private ListView searchContainer;
    private boolean isEnd = true;
    private int page = 1;
    private String search;
    private boolean haveReqWithoutAnswer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_search);

        LinearLayout layout = findViewById(R.id.menu);
        MyNavigation navigation = new MyNavigation(this, layout);
        layout.addView(navigation);

        searchContainer = findViewById(R.id.search_container);
        searchContainer.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (!haveReqWithoutAnswer && users != null && !isEnd && !absListView.canScrollVertically(1)) {
                    toast("Загрузка...");
                    addUsers();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

        users = new ArrayList<>();
        SearchAdapter adapter = new SearchAdapter(activity, users);
        searchContainer.setAdapter(adapter);

        EditText searchEditText = findViewById(R.id.searchEditText);
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchClick(null);
                    return true;
                }
                return false;
            }
        });

        getUsers("");
    }

    public void searchClick(View v) {
        EditText searchEditText = findViewById(R.id.searchEditText);
        getUsers(searchEditText.getText().toString());
    }

    public void getUsers(String search) {
        BaseAdapter baseAdapter = (BaseAdapter) searchContainer.getAdapter();
        users.clear();
        baseAdapter.notifyDataSetChanged();
        page = 1;
        this.search = search;
        makeRequest(true);
    }

    public void addUsers() {
        makeRequest(false);
    }

    public void updateAdapter() {
        BaseAdapter baseAdapter = (BaseAdapter) searchContainer.getAdapter();
        baseAdapter.notifyDataSetChanged();
    }

    public void makeRequest(boolean isFirst) {
        haveReqWithoutAnswer = true;
        NetworkService.getInstance()
                .getJSONApi()
                .getUsers(search, page)
                .enqueue(new Callback<UsersRes>() {
                    @Override
                    public void onResponse(@NonNull Call<UsersRes> call, @NonNull Response<UsersRes> response) {
                        if (response.isSuccessful()) {
                            UsersRes usersRes = response.body();
                            int size = usersRes.users.size();
                            if (size == 0) {
                                if (isFirst) {
                                    toast("Пользователи не найдены");

                                } else {
                                    toast("Больше нет");
                                }
                                isEnd = true;
                            } else {

                                page += 1;
                                if (size < 5) {
                                    isEnd = true;
                                } else {
                                    isEnd = false;
                                }
                                for (User u : usersRes.users) {
                                    users.add(u);
                                }
                                updateAdapter();
                            }
                        } else {
                            toast("Не верные параметры поиска");
                        }
                        haveReqWithoutAnswer = false;
                    }

                    @Override
                    public void onFailure(@NonNull Call<UsersRes> call, @NonNull Throwable t) {
                        toast("Не удалось подключиться к серверу");
                        haveReqWithoutAnswer = false;
                    }
                });
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