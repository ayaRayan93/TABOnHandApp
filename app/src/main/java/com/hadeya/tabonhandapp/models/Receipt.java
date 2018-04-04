package com.hadeya.tabonhandapp.models;

/**
 * Created by AyaAli on 03/04/2018.
 */

public class Receipt {

    String RecNo,RecDate,RecieptTypeId,CustmerId,Value,Notes,RefNO;

    public String getRecNo() {
        return RecNo;
    }

    public void setRecNo(String recNo) {
        RecNo = recNo;
    }

    public String getRecDate() {
        return RecDate;
    }

    public void setRecDate(String recDate) {
        RecDate = recDate;
    }

    public String getRecieptTypeId() {
        return RecieptTypeId;
    }

    public void setRecieptTypeId(String recieptTypeId) {
        RecieptTypeId = recieptTypeId;
    }

    public String getCustmerId() {
        return CustmerId;
    }

    public void setCustmerId(String custmerId) {
        CustmerId = custmerId;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
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
}
