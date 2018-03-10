package com.hadeya.tabonhandapp.Models;

/**
 * Created by mohamed shaban on 09/03/2018.
 */

public class InvoiceDetails
{
    int Id , InvoiceId , Quantity , Tax , Price , DiscountPercent , DiscountAmount;
    String ItemCode;
    String ItemName;
    String ExpityDate ;

    public String getExpityDate() {
        return ExpityDate;
    }

    public void setExpityDate(String expityDate) {
        ExpityDate = expityDate;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public int getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        DiscountAmount = discountAmount;
    }

    public int getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        DiscountPercent = discountPercent;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getTax() {
        return Tax;
    }

    public void setTax(int tax) {
        Tax = tax;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getInvoiceId() {
        return InvoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        InvoiceId = invoiceId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


}
