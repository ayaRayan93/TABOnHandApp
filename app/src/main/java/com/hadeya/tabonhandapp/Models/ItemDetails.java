package com.hadeya.tabonhandapp.Models;

import java.util.ArrayList;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class ItemDetails
{
    int id , InvoiceTypeId , CustmerId , PayementTypeId , RepCodeId;
    String InvoiceNo;
    String Notes;
    String RefNO;
    String InvoiceDate ;

    public ArrayList<InvoiceDetails> getTOHInvoiceDetails() {
        return TOHInvoiceDetails;
    }

    public void setTOHInvoiceDetails(ArrayList<InvoiceDetails> TOHInvoiceDetails) {
        this.TOHInvoiceDetails = TOHInvoiceDetails;
    }

    ArrayList<InvoiceDetails> TOHInvoiceDetails ;




    public String getRefNO() {
        return RefNO;
    }

    public void setRefNO(String refNO) {
        RefNO = refNO;
    }

    public String getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        InvoiceDate = invoiceDate;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        InvoiceNo = invoiceNo;
    }

    public int getRepCodeId() {
        return RepCodeId;
    }

    public void setRepCodeId(int repCodeId) {
        RepCodeId = repCodeId;
    }

    public int getPayementTypeId() {
        return PayementTypeId;
    }

    public void setPayementTypeId(int payementTypeId) {
        PayementTypeId = payementTypeId;
    }

    public int getCustmerId() {
        return CustmerId;
    }

    public void setCustmerId(int custmerId) {
        CustmerId = custmerId;
    }

    public int getInvoiceTypeId() {
        return InvoiceTypeId;
    }

    public void setInvoiceTypeId(int invoiceTypeId) {
        InvoiceTypeId = invoiceTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
