package com.example.onlinemarketplace.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.onlinemarketplace.AppRepository;
import com.example.onlinemarketplace.Encryption;
import com.example.onlinemarketplace.Entities.User;
import com.example.onlinemarketplace.R;
import com.example.onlinemarketplace.UserInfo;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private AppRepository repository;
    EditText loginText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        repository = new AppRepository(getApplication());

        loginText = findViewById(R.id.loginText);
        passwordText = findViewById(R.id.passwordText);
    }

    public void loginClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        try {
            User user = repository.getUserByLogin(String.valueOf(loginText.getText()));
            if (Arrays.equals(user.getPassword(), Encryption.encrypt(String.valueOf(passwordText.getText())))) {
                UserInfo.id = user.getUid();
                UserInfo.role = user.getRole();
                UserInfo.username = user.getLogin();
                startActivity(intent);
            } else {
                Log.d("UserSignInCheck", "Wrong password");
            }
        } catch (Exception e) {
            Log.d("Login", e.getMessage());
        }


    }

    public void registrationClick(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}