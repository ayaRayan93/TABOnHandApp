package com.hadeya.tabonhandapp.models;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class Item
{

    private String UnitCode;
    private String ItemName;
    private String ItemNameLat;
    private String ItemCode;
    private String TaxSet;
    private String SelPrice1Default;
    private String NotActive;

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
}
