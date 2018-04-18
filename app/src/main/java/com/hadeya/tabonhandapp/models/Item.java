package com.hadeya.tabonhandapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class Item implements Parcelable
{

    private String UnitCode;
    private String UnitName;
    private String ItemName;
    private String ItemNameLat;
    private String ItemCode;
    private String TaxSet;
    private String SelPrice1Default;
    private String NotActive;

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public Item() {
    }

    protected Item(Parcel in) {
        UnitCode = in.readString();
        ItemName = in.readString();
        ItemNameLat = in.readString();
        ItemCode = in.readString();
        TaxSet = in.readString();
        SelPrice1Default = in.readString();
        NotActive = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getNotActive() {
        return NotActive;
    }

    public void setNotActive(String notActive) {
        NotActive = notActive;
    }

    public String getSelPrice1Default() {
        return SelPrice1Default;
    }
    public void setSelPrice1Default(String selPrice1Default) {SelPrice1Default = selPrice1Default;}
    public String getTaxSet() {
        return TaxSet;
    }
    public void setTaxSet(String taxSet) {
        TaxSet = taxSet;
    }
    public String getItemCode() {
        return ItemCode;
    }
    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }
    public String getItemNameLat() {
        return ItemNameLat;
    }
    public void setItemNameLat(String itemNameLat) {
        ItemNameLat = itemNameLat;
    }
    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {ItemName = itemName;}

    public String getUnitCode() {
        return UnitCode;
    }

    public void setUnitCode(String unitCode) {
        UnitCode = unitCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(UnitCode);
        dest.writeString(ItemName);
        dest.writeString(ItemNameLat);
        dest.writeString(ItemCode);
        dest.writeString(TaxSet);
        dest.writeString(SelPrice1Default);
        dest.writeString(NotActive);
    }
}
