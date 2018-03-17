package com.hadeya.tabonhandapp.models;

/**
 * Created by AyaAli on 16/03/2018.
 */

public class Invoice {

    String Id,InvoiceTypeId,InvoiceNo,CustmerId,PayementTypeId,Notes,RefNO,RepCodeId;

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
}
