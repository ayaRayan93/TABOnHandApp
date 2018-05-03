package com.hadeya.tabonhandapp.models;

/**
 * Created by Shaimaa Derbaz on 5/2/2018.
 */

public class AutomticInvoiceNo {
    private String LoginRepCode  ;
    public  int SerialInvoice ;

    public AutomticInvoiceNo() {
    }

    public AutomticInvoiceNo(String loginRepCode, int serialInvoice) {
        LoginRepCode = loginRepCode;
        SerialInvoice = serialInvoice;
    }

    public String getLoginRepCode() {
        return LoginRepCode;
    }

    public void setLoginRepCode(String loginRepCode) {
        LoginRepCode = loginRepCode;
    }

    public int getSerialInvoice() {
        return SerialInvoice;
    }

    public void setSerialInvoice(int serialInvoice) {
        SerialInvoice = serialInvoice;
    }
}
