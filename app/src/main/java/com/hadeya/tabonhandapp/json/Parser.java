package com.hadeya.tabonhandapp.json;


import com.hadeya.tabonhandapp.models.Area;
import com.hadeya.tabonhandapp.models.Classification;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;
import com.hadeya.tabonhandapp.models.Item;
import com.hadeya.tabonhandapp.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AyaAli on 05/03/2018.
 */

public class Parser {

    public static List<Customer> parseStringToJson(String data) {
        List<Customer> modelCustomer;

        try {
            JSONArray jsonArray = new JSONArray(data);
            modelCustomer = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject employeeJsonObject = jsonArray.getJSONObject(i);
                String CustomerCode = employeeJsonObject.optString("CustomerCode");
                String CustName =employeeJsonObject.optString("CustName");
                String CustomerNameLat = employeeJsonObject.optString("CustomerNameLat");
                String StreetAra = employeeJsonObject.optString("StreetAra");
                String Classification = employeeJsonObject.optString("ClassificationTable");
                String PersonToConnect = employeeJsonObject.optString("PersonToConnect");
                String Tel = employeeJsonObject.optString("Tel");
                String TAXID = employeeJsonObject.optString("TAXID");
                String SaleAreaCode = employeeJsonObject.optString("SaleAreaCode");
                String Notes = employeeJsonObject.optString("Notes");
                String SalesRepCode = employeeJsonObject.optString("SalesRepCode");
                String CodeList = employeeJsonObject.optString("CodeList");
                String NotActive = employeeJsonObject.optString("NotActive");


                Customer customer = new Customer();
                customer.setCustomerCode(CustomerCode);
                customer.setCustName(CustName);
                customer.setCustomerNameLat(CustomerNameLat);
                customer.setStreetAra(StreetAra);
                customer.setClassification(Classification);

                customer.setPersonToConnect(PersonToConnect);
                customer.setTel(Tel);
                customer.setTAXID(TAXID);

                customer.setSaleAreaCode(SaleAreaCode);
                customer.setNotes(Notes);
                customer.setSalesRepCode(SalesRepCode);
                customer.setCodeList(CodeList);
                customer.setNotActive(NotActive);

                modelCustomer.add(customer);



            }

            return modelCustomer;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
    public static List<Classification> parseClassification(String data) {
        List<Classification> modelClassification;

        try {
            JSONArray jsonArray = new JSONArray(data);
            modelClassification = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject employeeJsonObject = jsonArray.getJSONObject(i);
                String Code = employeeJsonObject.optString("CustomerClassCode");
                String arname =employeeJsonObject.optString("CustomerClassName");


                Classification classification = new Classification();
                classification.setId(Code);
                classification.setName(arname);

                modelClassification.add(classification);



            }

            return modelClassification;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
    public static List<Area> parseArea(String data) {
        List<Area> modelArea;

        try {
            JSONArray jsonArray = new JSONArray(data);
            modelArea = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject employeeJsonObject = jsonArray.getJSONObject(i);
                String Code = employeeJsonObject.optString("SalesZoneCode");
                String arname =employeeJsonObject.optString("SalesZoneName");


                Area area = new Area();
                area.setId(Code);
                area.setName(arname);

                modelArea.add(area);



            }

            return modelArea;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
    public static List<Item> parseItem(String data) {
        List<Item> modelItem;

        try {
            JSONArray jsonArray = new JSONArray(data);
            modelItem = new ArrayList<>();


            for (int i = 0 ; i <jsonArray.length();i++)
            {
                String ItemCode = jsonArray.getJSONObject(i).getString("ItemCode");
                String ItemName = jsonArray.getJSONObject(i).getString("ItemName");
                String ItemNameLat = jsonArray.getJSONObject(i).getString("ItemNameLat");
                String UnitCode = jsonArray.getJSONObject(i).getString("UnitCode");
                String TaxSet = jsonArray.getJSONObject(i).getString("TaxSet");
                String SelPrice1Default = jsonArray.getJSONObject(i).getString("SelPrice1Default");
                String NotActive = jsonArray.getJSONObject(i).getString("NotActive");

                Item item = new Item();

                item.setItemCode(ItemCode);
                item.setItemName(ItemName);
                item.setItemNameLat(ItemNameLat);
                item.setUnitCode(UnitCode);
                item.setTaxSet(TaxSet);
                item.setSelPrice1Default(SelPrice1Default);
                item.setNotActive(NotActive);
                modelItem.add(item);
            }
            return modelItem;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
    public static List<Invoice> parseInvoice(String data) {
        List<Invoice> modelInvoice;

        try {
            JSONArray jsonArray = new JSONArray(data);
            modelInvoice = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject employeeJsonObject = jsonArray.getJSONObject(i);
                String Id = employeeJsonObject.optString("Id");
                String InvoiceTypeId =employeeJsonObject.optString("InvoiceTypeId");
                String InvoiceNo = employeeJsonObject.optString("InvoiceNo");
                String CustmerId =employeeJsonObject.optString("CustmerId");
                String PayementTypeId = employeeJsonObject.optString("PayementTypeId");
                String Notes =employeeJsonObject.optString("Notes");
                String RefNO = employeeJsonObject.optString("RefNO");
                String RepCodeId =employeeJsonObject.optString("RepCodeId");

                Invoice invoice = new Invoice();
                invoice.setId(Id);
                invoice.setInvoiceTypeId(InvoiceTypeId);
                invoice.setInvoiceNo(InvoiceNo);
                invoice.setCustmerId(CustmerId);
                invoice.setPayementTypeId(PayementTypeId);
                invoice.setNotes(Notes);
                invoice.setRefNO(RefNO);
                invoice.setRepCodeId(RepCodeId);

                modelInvoice.add(invoice);



            }

            return modelInvoice;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
    public static List<InvoiceItem> parseInvoiceItem(String data) {
        List<InvoiceItem> modelInvoiceItem;

        try {
            JSONArray jsonArray = new JSONArray(data);
            modelInvoiceItem = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject employeeJsonObject = jsonArray.getJSONObject(i);
                String Id = employeeJsonObject.optString("Id");
                String InvoiceId =employeeJsonObject.optString("InvoiceId");
                String ItemCode = employeeJsonObject.optString("ItemCode");
                String ItemName =employeeJsonObject.optString("ItemName");
                String Quantity = employeeJsonObject.optString("Quantity");
                String Tax =employeeJsonObject.optString("Tax");
                String ExpityDate = employeeJsonObject.optString("ExpityDate");
                String Price =employeeJsonObject.optString("Price");
                String DiscountPercent = employeeJsonObject.optString("DiscountPercent");
                String DiscountAmount =employeeJsonObject.optString("DiscountAmount");

                InvoiceItem invoiceItem = new InvoiceItem();
                invoiceItem.setId(Id);
                invoiceItem.setInvoiceId(InvoiceId);
                invoiceItem.setItemCode(ItemCode);
                invoiceItem.setItemName(ItemName);
                invoiceItem.setQuantity(Quantity);
                invoiceItem.setTax(Tax);
                invoiceItem.setExpityDate(ExpityDate);
                invoiceItem.setPrice(Price);
                invoiceItem.setDiscountPercent(DiscountPercent);
                invoiceItem.setDiscountAmount(DiscountAmount);

                modelInvoiceItem.add(invoiceItem);



            }

            return modelInvoiceItem;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
    public static List<User> parseUser(String data) {
        List<User> modelUser;

        try {
            JSONArray jsonArray = new JSONArray(data);
            modelUser = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject employeeJsonObject = jsonArray.getJSONObject(i);
                String RepCodId = employeeJsonObject.optString("RepCodId");
                String UserName =employeeJsonObject.optString("UserName");
                String Password =employeeJsonObject.optString("Password");

                User user = new User();
                user.setRepCodId(RepCodId);
                user.setUserName(UserName);
                user.setPassword(Password);

                modelUser.add(user);



            }

            return modelUser;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
