package com.hadeya.tabonhandapp.Models;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class LoginResponse
{
    int id;

    public int getRepCodId() {
        return RepCodId;
    }

    public void setRepCodId(int repCodId) {
        RepCodId = repCodId;
    }

    int RepCodId;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String UserName , Password ;
}
