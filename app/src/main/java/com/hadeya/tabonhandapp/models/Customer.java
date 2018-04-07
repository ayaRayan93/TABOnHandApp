package com.hadeya.tabonhandapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AyaAli on 05/03/2018.
 */

public class Customer implements Parcelable{

    String id, Flag,CustomerCode,CustName,CustomerNameLat,StreetAra,Classification,PersonToConnect,Tel,TAXID,SaleAreaCode,Notes,SalesRepCode,CodeList,NotActive;


    protected Customer(Parcel in) {
        id = in.readString();
        Flag = in.readString();
        CustomerCode = in.readString();
        CustName = in.readString();
        CustomerNameLat = in.readString();
        StreetAra = in.readString();
        Classification = in.readString();
        PersonToConnect = in.readString();
        Tel = in.readString();
        TAXID = in.readString();
        SaleAreaCode = in.readString();
        Notes = in.readString();
        SalesRepCode = in.readString();
        CodeList = in.readString();
        NotActive = in.readString();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public String getCustomerCode() {
        return CustomerCode;
    }

    public Customer() {
    }

    public Customer(String custName, String streetAra, String classification, String personToConnect, String tel, String TAXID, String saleAreaCode) {
       // CustomerCode = customerCode;
        CustName = custName;
        StreetAra = streetAra;
        Classification = classification;
        PersonToConnect = personToConnect;
        Tel = tel;
        this.TAXID = TAXID;
        SaleAreaCode = saleAreaCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;

    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getCustomerNameLat() {
        return CustomerNameLat;
    }

    public void setCustomerNameLat(String customerNameLat) {
        CustomerNameLat = customerNameLat;
    }

    public String getStreetAra() {
        return StreetAra;
    }

    public void setStreetAra(String streetAra) {
        StreetAra = streetAra;
    }

    public String getClassification() {
        return Classification;
    }

    public void setClassification(String classification) {
        Classification = classification;
    }

    public String getPersonToConnect() {
        return PersonToConnect;
    }

    public void setPersonToConnect(String personToConnect) {
        PersonToConnect = personToConnect;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getTAXID() {
        return TAXID;
    }

    public void setTAXID(String TAXID) {
        this.TAXID = TAXID;
    }

    public String getSaleAreaCode() {
        return SaleAreaCode;
    }

    public void setSaleAreaCode(String saleAreaCode) {
        SaleAreaCode = saleAreaCode;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getSalesRepCode() {
        return SalesRepCode;
    }

    public void setSalesRepCode(String salesRepCode) {
        SalesRepCode = salesRepCode;
    }

    public String getCodeList() {
        return CodeList;
    }

    public void setCodeList(String codeList) {
        CodeList = codeList;
    }

    public String getNotActive() {
        return NotActive;
    }

    public void setNotActive(String notActive) {
        NotActive = notActive;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(Flag);
        dest.writeString(CustomerCode);
        dest.writeString(CustName);
        dest.writeString(CustomerNameLat);
        dest.writeString(StreetAra);
        dest.writeString(Classification);
        dest.writeString(PersonToConnect);
        dest.writeString(Tel);
        dest.writeString(TAXID);
        dest.writeString(SaleAreaCode);
        dest.writeString(Notes);
        dest.writeString(SalesRepCode);
        dest.writeString(CodeList);
        dest.writeString(NotActive);
    }
}
