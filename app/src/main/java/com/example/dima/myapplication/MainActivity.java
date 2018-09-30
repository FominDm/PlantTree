package com.example.dima.myapplication;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private ImageButton cartButton;
    private String userFilePath = "Useraccounts.txt";
    private ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        userList = new ArrayList<>();
        CreateUserList(userFilePath);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListnerOnButton();
    }

    public void ListnerOnButton()
    {
        cartButton = (ImageButton) findViewById(R.id.cartButton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".LoginActivity");
                startActivity(intent);
            }
        });

    }
   /* public void SaveUsersToFile(String filePath)
    {
        try
        {
            OutputStream fos = openFileOutput(filePath, MODE_PRIVATE);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            String data = "";
            for(User user:userList)
            {
                data += user.getUserName() + " ";
                data += user.getUserMail() + " ";
                data += user.getUserPassword() + " \n";
            }
            bw.write(data);
            bw.close();
            fos.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }*/

    public void CreateUserList(String filePath)
    {
        try {
            InputStream is = getAssets().open(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while(true)
            {
                String line = br.readLine();
                if(line == null)
                {
                    break;
                }else
                {
                    String userName, userMail, userPassword;
                    User user;

                    String tokenizedLine[] = line.split(" ");

                    userName = tokenizedLine[0];
                    userMail = tokenizedLine[1];
                    userPassword = tokenizedLine[2];

                    user = new User(userName, userMail, userPassword);

                    userList.add(user);
                }

            }
            br.close();
            is.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
