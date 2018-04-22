package com.hadeya.tabonhandapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AyaAli on 2018-04-22.
 */

public class Customer_Balance implements Parcelable {

    String CustomerCode,araName,balance;

    public Customer_Balance(Parcel in) {
        CustomerCode = in.readString();
        araName = in.readString();
        balance = in.readString();
    }

    public static final Creator<Customer_Balance> CREATOR = new Creator<Customer_Balance>() {
        @Override
        public Customer_Balance createFromParcel(Parcel in) {
            return new Customer_Balance(in);
        }

        @Override
        public Customer_Balance[] newArray(int size) {
            return new Customer_Balance[size];
        }
    };

    public Customer_Balance() {

    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }

    public String getAraName() {
        return araName;
    }

    public void setAraName(String araName) {
        this.araName = araName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(CustomerCode);
        dest.writeString(araName);
        dest.writeString(balance);
    }
}
