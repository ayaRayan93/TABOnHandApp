package com.hadeya.tabonhandapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by AyaAli on 16/03/2018.
 */

public class Invoice implements Parcelable {


    String  Id;
    String InvoiceTypeId,InvoiceNo,InvoiceDate,CustmerId,PayementTypeId,Notes,RefNO,RepCodeId;
    Customer customer;
    List<InvoiceItem> invoiceItems;

    protected Invoice(Parcel in) {
        Id = in.readString();
        InvoiceTypeId = in.readString();
        InvoiceNo = in.readString();
        InvoiceDate = in.readString();
        CustmerId = in.readString();
        PayementTypeId = in.readString();
        Notes = in.readString();
        RefNO = in.readString();
        RepCodeId = in.readString();
        customer = in.readParcelable(Customer.class.getClassLoader());
        invoiceItems = in.createTypedArrayList(InvoiceItem.CREATOR);
    }

    public static final Creator<Invoice> CREATOR = new Creator<Invoice>() {
        @Override
        public Invoice createFromParcel(Parcel in) {
            return new Invoice(in);
        }

        @Override
        public Invoice[] newArray(int size) {
            return new Invoice[size];
        }
    };

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public Invoice() {
        invoiceItems=new ArrayList<>();
    }

    public Invoice(String invoiceTypeId, String invoiceNo, String InvoiceDate, String custmerId, String payementTypeId, String notes, String refNO, String repCodeId) {
        InvoiceTypeId = invoiceTypeId;
        InvoiceNo = invoiceNo;
        InvoiceDate=InvoiceDate;
        CustmerId = custmerId;
        PayementTypeId = payementTypeId;
        Notes = notes;
        RefNO = refNO;
        RepCodeId = repCodeId;

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getInvoiceTypeId() {
        return InvoiceTypeId;
    }

    public void setInvoiceTypeId(String invoiceTypeId) {
        InvoiceTypeId = invoiceTypeId;
    }

    public String getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        InvoiceNo = invoiceNo;
    }

    public String getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        InvoiceDate = invoiceDate;
    }

    public String getCustmerId() {
        return CustmerId;
    }

    public void setCustmerId(String custmerId) {
        CustmerId = custmerId;
    }

    public String getPayementTypeId() {
        return PayementTypeId;
    }

    public void setPayementTypeId(String payementTypeId) {
        PayementTypeId = payementTypeId;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getRefNO() {
        return RefNO;
    }

    public void setRefNO(String refNO) {
        RefNO = refNO;
    }

    public String getRepCodeId() {
        return RepCodeId;
    }

    public void setRepCodeId(String repCodeId) {
        RepCodeId = repCodeId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(Id);
        dest.writeString(InvoiceTypeId);
        dest.writeString(InvoiceNo);
        dest.writeString(InvoiceDate);
        dest.writeString(CustmerId);
        dest.writeString(PayementTypeId);
        dest.writeString(Notes);
        dest.writeString(RefNO);
        dest.writeString(RepCodeId);
        dest.writeParcelable(customer,flags);
        dest.writeList(invoiceItems);

    }
}
