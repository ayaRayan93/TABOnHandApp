package com.hadeya.tabonhandapp.models;

/**
 * Created by AyaAli on 25/03/2018.
 */

public class CustomerInvoice {
    String InvoceId, InvoiceNo,Date,Value;

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
}
