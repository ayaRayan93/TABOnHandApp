package com.hadeya.tabonhandapp.Models;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class Item
{

    private int UnitCode;
    private String ItemName;
    private String ItemNameLat;
    private String ItemCode;
    private String TaxSet;
    private String SelPrice1Default;
    private boolean NotActive;

    public boolean isNotActive() {
        return NotActive;
    }
    public void setNotActive(boolean notActive) {
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
    public int getUnitCode() {
        return UnitCode;
    }
    public void setUnitCode(int unitCode) {
        UnitCode = unitCode;
    }

    public Item(){}

    public Item(String itemName , String itemNameLat , String itemCode , String taxSet ,
                String selPrice1Default , boolean notActive , int unitCode)
    {
        ItemCode = itemCode ;
        ItemName = itemName ;
        ItemNameLat = itemNameLat;
        UnitCode = unitCode;
        TaxSet = taxSet;
        SelPrice1Default = selPrice1Default;
        NotActive = notActive ;
    }
}
