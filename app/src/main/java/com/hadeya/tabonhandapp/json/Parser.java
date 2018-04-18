package com.hadeya.tabonhandapp.json;


import android.support.annotation.Nullable;

import com.hadeya.tabonhandapp.models.Area;
import com.hadeya.tabonhandapp.models.Classification;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.models.CustomerInvoice;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;
import com.hadeya.tabonhandapp.models.InvoiceType;
import com.hadeya.tabonhandapp.models.Item;
import com.hadeya.tabonhandapp.models.ItemInvoice;
import com.hadeya.tabonhandapp.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.hadeya.tabonhandapp.store.UserTable.UserName;

/**
 * Created by AyaAli on 05/03/2018.
 */

public class Parser {

    //Basic DATA
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
    public static List<CustomerInvoice> parseCustomerInvoice(String data) {
        List<CustomerInvoice> modelCustomerInvoice;

        try {
            JSONArray jsonArray = new JSONArray(data);
            modelCustomerInvoice = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject employeeJsonObject = jsonArray.getJSONObject(i);
                String InvoiceNo = employeeJsonObject.optString("InvoiceNo");
                String InvoceId =employeeJsonObject.optString("InvoceId");
                String InvoiceDate = employeeJsonObject.optString("InvoiceDate");
                String value = employeeJsonObject.optString("value");


                CustomerInvoice customerInvoice = new CustomerInvoice();
                customerInvoice.setInvoiceNo(InvoiceNo);
                customerInvoice.setInvoceId(InvoceId);
                customerInvoice.setDate(InvoiceDate);
                customerInvoice.setValue(value);

                modelCustomerInvoice.add(customerInvoice);



            }

            return modelCustomerInvoice;

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
                JSONObject JsonObject = jsonArray.getJSONObject(i);
                String ItemCode = JsonObject.optString("ItemCode");
                String ItemName = JsonObject.optString("ItemName");
                String ItemNameLat = JsonObject.optString("ItemNameLat");
                String UnitCode = JsonObject.optString("UnitCode");
                String UnitName = JsonObject.optString("UnitAraName");
                String TaxSet = JsonObject.optString("TaxSet");
                String SelPrice1Default = JsonObject.optString("SelPrice1Default");
                String NotActive = JsonObject.optString("NotActive");

                Item item = new Item();

                item.setItemCode(ItemCode);
                item.setItemName(ItemName);
                item.setItemNameLat(ItemNameLat);
                item.setUnitCode(UnitCode);
                item.setUnitName(UnitName);
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
    public static List<ItemInvoice> parseItemInvoice(String data) {
        List<ItemInvoice> modelItemInvoice;

        try {
            JSONArray jsonArray = new JSONArray(data);
            modelItemInvoice = new ArrayList<>();


            for (int i = 0 ; i <jsonArray.length();i++)
            {
                JSONObject JsonObject = jsonArray.getJSONObject(i);
                String InvoiceNo = JsonObject.optString("InvoiceNo");
                String CustomerName =JsonObject.optString("CustomerName");
                String Quantity = JsonObject.optString("Quantity");
                String Price =JsonObject.optString("Price");
                String value="0";//String.valueOf(Integer.parseInt(Quantity)*Double.parseDouble(Price));

                ItemInvoice itemInvoice = new ItemInvoice();

                itemInvoice.setInvoiceNo(InvoiceNo);
                itemInvoice.setCustomerName(CustomerName);
                itemInvoice.setQuantity(Quantity);
                itemInvoice.setValue(value);
                itemInvoice.setPrice(Price);
                modelItemInvoice.add(itemInvoice);
            }
            return modelItemInvoice;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
   //
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
                JSONArray aa=employeeJsonObject.getJSONArray("TOHInvoiceDetails");
                for (int j=0;j<aa.length();j++) {
                    employeeJsonObject = aa.getJSONObject(j);
                    String Id = employeeJsonObject.optString("Id");
                    String InvoiceId = employeeJsonObject.optString("InvoiceId");
                    String ItemCode = employeeJsonObject.optString("ItemCode");
                    String ItemName = employeeJsonObject.optString("ItemName");
                    String Quantity = employeeJsonObject.optString("Quantity");
                    String Tax = employeeJsonObject.optString("Tax");
                    String ExpityDate = employeeJsonObject.optString("ExpityDate");
                    String Price = employeeJsonObject.optString("Price");
                    String DiscountPercent = employeeJsonObject.optString("DiscountPercent");
                    String DiscountAmount = employeeJsonObject.optString("DiscountAmount");

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
            }

            return modelInvoiceItem;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


    public static User parseUser(String data) {
        User modelUser;

        try {
            JSONObject jsonObject = new JSONObject(data);
            String RepCodId = jsonObject.optString("RepCodId");

            modelUser = new User();
            modelUser.setRepCodId(RepCodId);
            JSONObject employeeJsonObject = jsonObject.optJSONObject("TohUser");
            String UserName =employeeJsonObject.optString("UserName");
            String Password =employeeJsonObject.optString("UserPassword");
            modelUser.setUserName(UserName);
            modelUser.setPassword(Password);

            return modelUser;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static  List <InvoiceType>  parseInvoiceTypes(String data) {
        List <InvoiceType>  modelInvoiceType =null;

        try {
            JSONArray jsonArray = new JSONArray(data);
            modelInvoiceType = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++)
            {
                //modelInvoiceType.get(i) = new InvoiceType();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String BranchCode = jsonObject.optString("BranchCode");
                String trxtypecode = jsonObject.optString("trxtypecode");
                String TrxArbName = jsonObject.optString("TrxArbName");
                String TrxEngName = jsonObject.optString("TrxEngName");
                String TrxKind = jsonObject.optString("TrxKind");
                String TrxTypeID = jsonObject.optString("TrxTypeID");
                String TrxType = jsonObject.optString("TrxType");
                InvoiceType invoiceT = new InvoiceType();
                invoiceT.setBranchCode(Integer.parseInt(BranchCode));
                invoiceT.setTrxtypecode(Integer.parseInt(trxtypecode));
                invoiceT.setTrxArbName(TrxArbName);
                invoiceT.setTrxEngName(TrxEngName);
                invoiceT.setTrxKind(Integer.parseInt(TrxKind));
                invoiceT.setTrxTypeID(Integer.parseInt(TrxTypeID));
                invoiceT.setTrxType(Integer.parseInt(TrxType));
                modelInvoiceType.add(invoiceT);


        }
            return modelInvoiceType;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


}
