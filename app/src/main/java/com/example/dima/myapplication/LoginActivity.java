package com.example.dima.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private ImageButton homeButton;
    private EditText emailField, passwordField;
    private ArrayList<String> emails, passwords;
    private String passBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emails = getIntent().getStringArrayListExtra("emails");
        passwords = getIntent().getStringArrayListExtra("passwords");
        emailField = findViewById(R.id.EmailField);
        passwordField = findViewById(R.id.PaswordField);
        ListnerOnButton();
    }

    private void ListnerOnButton()
    {
        homeButton = findViewById(R.id.HomeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        loginButton = findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if(checkEmail())
                {
                    if(TryLogIn())
                    {
                        Intent i = new Intent();
                        i.putExtra(Intent.EXTRA_TEXT, passBack);
                        setResult(RESULT_OK, i);
                        finish();
                    }else
                    {
                        Toast.makeText(LoginActivity.this,"Email or password are not correct!", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(LoginActivity.this,"Email you entered not valid!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean TryLogIn()
    {
        boolean logIn = false;


        for(String email: emails)
        {
            if(emailField.getText().toString().toLowerCase().equals(email))
            {
                for(String password:passwords)
                {
                    if(passwordField.getText().toString().equals(password))
                    {
                        passBack = email;
                        logIn = true;
                    }
                }
            }
        }

        return logIn;
    }

    private boolean checkEmail()
    {
        boolean res = false;

        if(emailField.getText().toString().contains("@"))
        {
            res = true;
        }
        return res;
    }
}
