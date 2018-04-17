package com.hadeya.tabonhandapp.models;

/**
 * Created by Shaimaa Derbaz on 4/16/2018.
 */

public class InvoiceType {
    private int BranchCode,trxtypecode,TrxKind,TrxTypeID,TrxType;
    private String TrxArbName,TrxEngName;

    public InvoiceType()
    {

    }

    public InvoiceType(int branchCode, int trxtypecode, int trxKind, int trxTypeID, int trxType, String trxArbName, String trxEngName) {
        BranchCode = branchCode;
        this.trxtypecode = trxtypecode;
        TrxKind = trxKind;
        TrxTypeID = trxTypeID;
        TrxType = trxType;
        TrxArbName = trxArbName;
        TrxEngName = trxEngName;
    }

    public int getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(int branchCode) {
        BranchCode = branchCode;
    }

    public int getTrxtypecode() {
        return trxtypecode;
    }

    public void setTrxtypecode(int trxtypecode) {
        this.trxtypecode = trxtypecode;
    }

    public int getTrxKind() {
        return TrxKind;
    }

    public void setTrxKind(int trxKind) {
        TrxKind = trxKind;
    }

    public int getTrxTypeID() {
        return TrxTypeID;
    }

    public void setTrxTypeID(int trxTypeID) {
        TrxTypeID = trxTypeID;
    }

    public int getTrxType() {
        return TrxType;
    }

    public void setTrxType(int trxType) {
        TrxType = trxType;
    }

    public String getTrxArbName() {
        return TrxArbName;
    }

    public void setTrxArbName(String trxArbName) {
        TrxArbName = trxArbName;
    }

    public String getTrxEngName() {
        return TrxEngName;
    }

    public void setTrxEngName(String trxEngName) {
        TrxEngName = trxEngName;
    }
}
