package com.example.mobile_labs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register(View v) {
        String name = ((EditText)findViewById(R.id.nameEditText)).getText().toString();
        String surname = ((EditText)findViewById(R.id.surnameEditText)).getText().toString();
        String login = ((EditText)findViewById(R.id.loginEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.passwordCheckEditText)).getText().toString();
        try{
            if(!password.equals(passwordCheck)){
                toast("Пароли не совпали");
                throw new Exception("");
            }
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }catch (Exception error){
            toast("Не удалось зарегистрироваться");
        }
    }

    public void logScreen(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
    }

    public void toast(String str){
        Toast toast = Toast.makeText(this, str,
                Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
    }

}