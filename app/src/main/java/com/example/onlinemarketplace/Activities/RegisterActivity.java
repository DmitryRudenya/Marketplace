package com.example.onlinemarketplace.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.onlinemarketplace.AppRepository;
import com.example.onlinemarketplace.DAO.UserDao;
import com.example.onlinemarketplace.Encryption;
import com.example.onlinemarketplace.Entities.User;
import com.example.onlinemarketplace.R;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class RegisterActivity extends AppCompatActivity {

    private AppRepository appRepository;
    UserDao userDao;
    EditText nameText;
    EditText emailText;
    EditText loginText;
    EditText passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appRepository = new AppRepository(getApplication());
        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        loginText = findViewById(R.id.loginText);
        passwordText = findViewById(R.id.passwordText);

    }

    public void registerClick(View view) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            User user = new User(String.valueOf(nameText.getText()), String.valueOf(loginText.getText()), Encryption.encrypt(String.valueOf(passwordText.getText())), String.valueOf(emailText.getText()), "USER");
            appRepository.insertUser(user);

            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("userName", user.getName());
            intent.putExtra("userLogin", user.getLogin());
            startActivity(intent);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }
}