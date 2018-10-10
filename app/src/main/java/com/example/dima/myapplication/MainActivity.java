package com.example.dima.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageButton cartButton, loginButton;
    private Button shopOnline;
    private TextView loginTextView;
    private ListView specialsListView;
    private String userFilePath = "Useraccounts.txt";
    private String productFilePath = "Productlist.txt";
    private String specialsFilePath = "Specials.txt";

    private static ArrayList<User> userList;
    public static ArrayList<Product> productList;
    public static ArrayList<Product> specialsList;
    private static User currentUser;
    public static ShoppingCart shoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        currentUser = null;
        userList = new ArrayList<>();
        productList = new ArrayList<>();
        shoppingCart = new ShoppingCart();
        specialsList = new ArrayList<>();
        LoadData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListnerOnButton();
        SetTexts();

        specialsListView = findViewById(R.id.ListView);
        ProductSpecialsAdapter adapter = new ProductSpecialsAdapter(this, specialsList);
        specialsListView.setAdapter(adapter);
    }

    public void SetTexts()
    {
        loginTextView = findViewById(R.id.LoginViewField);
        if(currentUser == null)
        {
            loginTextView.setText("Please log in");
        }else
        {
            String res = "Welcome ";
            res += currentUser.getUserName() +"!";
            loginTextView.setText(res);
        }

    }

    public void ListnerOnButton()
    {
        cartButton = findViewById(R.id.cartButton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".ShoppingCartActivity");
                startActivity(intent);
            }
        });

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".LoginActivity");

                ArrayList<String> emails = new ArrayList<>();
                ArrayList<String> passwords = new ArrayList<>();
                for(User user:userList)
                {
                    emails.add(user.getUserMail());
                    passwords.add(user.getUserPassword());
                }
                intent.putStringArrayListExtra("emails", emails);
                intent.putStringArrayListExtra("passwords", passwords);
                startActivityForResult(intent, 0);
            }
        });

        shopOnline = findViewById(R.id.ShopCatalog);
        shopOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".ShopOnline");
                startActivity(intent);
            }
        });


    }

    public void LoadData()
    {
        try {
            InputStream is = getAssets().open(userFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while(true)
            {
                String line = br.readLine();
                if(line == null)
                {
                    break;
                }else
                {
                    String userName, userMail, userPassword, userType;
                    User user;

                    String tokenizedLine[] = line.split(" ");

                    userName = tokenizedLine[0];
                    userMail = tokenizedLine[1];
                    userPassword = tokenizedLine[2];
                    userType = tokenizedLine[3];

                    user = new User(userName, userMail, userPassword, userType);

                    userList.add(user);
                }

            }
            br.close();
            is.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }

        try{
            InputStream is = getAssets().open(productFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while(true)
            {
                String line = br.readLine();
                if(line == null)
                {
                    break;
                }else
                {
                    String name, description, category, price;
                    String tokenLine[] = line.split("/;");

                    name = tokenLine[0].trim();
                    description = tokenLine[1].trim();
                    category = tokenLine[2].trim();
                    price = tokenLine[3].trim();
                    Product product = new Product(name, description, category, price);
                    productList.add(product);
                }
            }
            br.close();
            is.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }

        try{
            InputStream is = getAssets().open(specialsFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while(true)
            {
                String line = br.readLine();
                if(line == null)
                {
                    break;
                }else
                {
                    String name, price;
                    String tokenLine[] = line.split("/;");

                    name = tokenLine[0].trim();
                    price = tokenLine[1].trim();

                    for(Product product: productList)
                    {
                        if(name.equals(product.getName()))
                        {
                            Product newProduct = new Product();
                            newProduct.setName(name);
                            newProduct.setCategory(product.getCategory());
                            newProduct.setDescription(product.getDescription());
                            newProduct.setPrice(price);
                            specialsList.add(newProduct);
                            break;
                        }
                    }
                }
            }
            br.close();
            is.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String returnString = data.getStringExtra(Intent.EXTRA_TEXT);

                for(User user:userList)
                {
                    if(user.getUserMail().toString().toLowerCase().equals(returnString))
                    {
                        currentUser = user;
                        SetTexts();
                        break;
                    }
                }
            }
        }
    }


}
