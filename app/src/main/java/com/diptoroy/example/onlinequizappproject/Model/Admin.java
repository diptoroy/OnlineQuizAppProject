package com.diptoroy.example.onlinequizappproject.Model;

public class Admin {

    private String UserName;
    private String password;

    public Admin() {

    }

    public Admin(String userName, String password) {
        this.UserName = userName;
        this.password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
