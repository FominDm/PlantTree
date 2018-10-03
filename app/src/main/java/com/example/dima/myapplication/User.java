package com.example.dima.myapplication;

public class User
{
    private String userName, userPassword, userMail, userType;

    public User(String userName, String userMail, String userPassword, String userType)
    {
        setUserName(userName);
        setUserPassword(userPassword);
        setUserMail(userMail);
        setUserType(userType);
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

    public String getUsersType()
    {
        return this.userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }
}
