package com.hadeya.tabonhandapp.models;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class User
{


    String RepCodId;

    String UserName , Password ;

    public String getRepCodId() {
        return RepCodId;
    }

    public void setRepCodId(String repCodId) {
        RepCodId = repCodId;
    }

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
}
