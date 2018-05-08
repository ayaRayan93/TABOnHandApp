package com.hadeya.tabonhandapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AyaAli on 25/03/2018.
 */

public class CustomerInvoice implements Parcelable {
    String InvoceId, InvoiceNo,Date,Value;

    protected CustomerInvoice(Parcel in) {
        InvoceId = in.readString();
        InvoiceNo = in.readString();
        Date = in.readString();
        Value = in.readString();
    }

    public CustomerInvoice() {
    }

    public static final Creator<CustomerInvoice> CREATOR = new Creator<CustomerInvoice>() {
        @Override
        public CustomerInvoice createFromParcel(Parcel in) {
            return new CustomerInvoice(in);
        }

        @Override
        public CustomerInvoice[] newArray(int size) {
            return new CustomerInvoice[size];
        }
    };

    public String getInvoceId() {
        return InvoceId;
    }

    public void setInvoceId(String invoceId) {
        InvoceId = invoceId;
    }

    public String getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        InvoiceNo = invoiceNo;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(InvoceId);
        dest.writeString(InvoiceNo);
        dest.writeString(Date);
        dest.writeString(Value);
    }
}
