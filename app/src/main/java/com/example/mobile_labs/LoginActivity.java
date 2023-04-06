package com.example.mobile_labs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobile_labs.request.Authorization;
import com.example.mobile_labs.request.LoginReq;
import com.example.mobile_labs.request.TrainingsRes;
import com.example.mobile_labs.request.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v) {
        String login = ((EditText) findViewById(R.id.loginEditText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();

        LoginReq loginReq = new LoginReq();
        loginReq.login = login;
        loginReq.password = password;

        NetworkService.getInstance()
                .getJSONApi()
                .login(loginReq)
                .enqueue(new Callback<Authorization>() {
                    @Override
                    public void onResponse(@NonNull Call<Authorization> call, @NonNull Response<Authorization> response) {
                        if (response.isSuccessful()) {
                            Authorization auth = response.body();
                            Authorization.accessToken = auth.accessToken_;
                            Authorization.refreshToken = auth.refreshTokens_;
                            Authorization.userID = auth.id;

                            Intent intent = new Intent(v.getContext(), MainActivity.class);
                            v.getContext().startActivity(intent);
                        } else {
                            toast("Не верный логин или пароль");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Authorization> call, @NonNull Throwable t) {

                        toast("Не удалось подключиться к серверу");
                    }
                });
    }

    public void regScreen(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        this.startActivity(intent);
    }

    public void toast(String str) {
        Toast toast = Toast.makeText(this, str,
                Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}