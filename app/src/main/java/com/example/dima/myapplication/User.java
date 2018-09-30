package com.example.dima.myapplication;

public class User
{
    private String userName, userPassword, userMail;

    public User(String userName, String userMail, String userPassword)
    {
        setUserName(userName);
        setUserPassword(userPassword);
        setUserMail(userMail);
    }

    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPassword()
    {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public String getUserMail()
    {
        return this.userMail;
    }

    public void setUserMail(String userMail)
    {
        this.userMail = userMail;
    }


}
