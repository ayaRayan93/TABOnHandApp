package com.hadeya.tabonhandapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AyaAli on 16/03/2018.
 */

public class InvoiceItem implements Parcelable{


    String Id,InvoiceId,ItemCode,ItemName,Quantity,Tax,ExpityDate,Price,DiscountPercent,DiscountAmount,Net;



    protected InvoiceItem(Parcel in) {
        Id = in.readString();
        InvoiceId = in.readString();
        ItemCode = in.readString();
        ItemName = in.readString();
        Quantity = in.readString();
        Tax = in.readString();
        ExpityDate = in.readString();
        Price = in.readString();
        DiscountPercent = in.readString();
        DiscountAmount = in.readString();
        Net=in.readString();
    }

    public InvoiceItem() {
    }

    public static final Creator<InvoiceItem> CREATOR = new Creator<InvoiceItem>() {
        @Override
        public InvoiceItem createFromParcel(Parcel in) {
            return new InvoiceItem(in);
        }

        @Override
        public InvoiceItem[] newArray(int size) {
            return new InvoiceItem[size];
        }
    };

    public String getNet() {
        return Net;
    }

    public void setNet(String net) {
        Net = net;
    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getInvoiceId() {
        return InvoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        InvoiceId = invoiceId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getTax() {
        return Tax;
    }

    public void setTax(String tax) {
        Tax = tax;
    }

    public String getExpityDate() {
        return ExpityDate;
    }

    public void setExpityDate(String expityDate) {
        ExpityDate = expityDate;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        DiscountPercent = discountPercent;
    }

    public String getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        DiscountAmount = discountAmount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(InvoiceId);
        dest.writeString(ItemCode);
        dest.writeString(ItemName);
        dest.writeString(Quantity);
        dest.writeString(Tax);
        dest.writeString(ExpityDate);
        dest.writeString(Price);
        dest.writeString(DiscountPercent);
        dest.writeString(DiscountAmount);
        dest.writeString(Net);
    }
}
