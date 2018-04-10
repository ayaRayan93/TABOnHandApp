package com.hadeya.tabonhandapp.store;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hadeya.tabonhandapp.activities.start.LoginActivity;
import com.hadeya.tabonhandapp.json.Parser;
import com.hadeya.tabonhandapp.models.Area;
import com.hadeya.tabonhandapp.models.Classification;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.app.AppController;
import com.hadeya.tabonhandapp.models.CustomerInvoice;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;
import com.hadeya.tabonhandapp.models.Item;
import com.hadeya.tabonhandapp.models.ItemInvoice;
import com.hadeya.tabonhandapp.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getCustomer;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getInvoices;
import static com.hadeya.tabonhandapp.store.ReadDataFromDB.getItemInvoice;

/**
 * Created by AyaAli on 09/03/2018.
 */

public class WriteDataToDB {

    public static DataBaseHelper mdatabase;

    public static void downloadData()
    {
        //basic data
       // mdatabase.resetDataBase();
        storeCustomer("13007");
        storeClassification();
        storeArea();
        storeCustomerInvoice();
        StoreItems();
        storeItemInvoice();

        StoreInvoiceItems();
        StoreInvoices();
    }

    public static void storeCustomer(String repCode)
    {
      // final List<Customer> dataSet=new ArrayList<>();
        String Url="http://toh.hadeya.net/api/TOHCustomers/repCodeTOHCustomers/"+repCode;

        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
              /*  if (dataSet != null){
                    dataSet.clear();

                }*/
                Iterator iterator = Parser.parseStringToJson(response).iterator();
                while (iterator.hasNext()){
                    Customer customer = (Customer) iterator.next();
                    //dataSet.add(customer);
                    addCustomer(customer);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Stop the refreshing indicator
                Log.d("response", error.toString());
            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(strReq);

        //return1 dataSet;
    }
    public static void storeClassification()
    {
        //final List<Classification> dataClass=new ArrayList<>();
        String Url="http://toh.hadeya.net/api/getalldata/TOHCustomerClasses/13007";

        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
               /* if (dataClass != null){
                    dataClass.clear();
                }*/
                Iterator iterator = Parser.parseClassification(response).iterator();
                while (iterator.hasNext()){
                    Classification classification = (Classification) iterator.next();
                    //dataClass.add(movie);
                    addClassfication(classification);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(strReq);

       // return1 dataClass;
    }
    public static void storeArea()
    {
        //final List<Area> dataArea=new ArrayList<>();
        String Url="http://toh.hadeya.net/api/getalldata/TOHSalesZones/13007";

        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
               /* if (dataArea != null){
                    dataArea.clear();
                }*/
                Iterator iterator = Parser.parseArea(response).iterator();
                while (iterator.hasNext()){
                    Area area = (Area) iterator.next();
                   // dataArea.add(movie);
                    addArea(area);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(strReq);

       // return1 dataArea;
    }
    public static void storeCustomerInvoice()
    {
        //final List<Area> dataArea=new ArrayList<>();
        String Url="http://toh.hadeya.net/api/TOHInvoices/CustomerTOHInvoices/13007?CustomerId=11";

        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
               /* if (dataArea != null){
                    dataArea.clear();
                }*/
                Iterator iterator = Parser.parseCustomerInvoice(response).iterator();
                while (iterator.hasNext()){
                    CustomerInvoice customerInvoice = (CustomerInvoice) iterator.next();
                    // dataArea.add(movie);
                    addCustomerInvoice(customerInvoice);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(strReq);

        // return1 dataArea;
    }

    public static void StoreItems()
    {
        // final List<Customer> dataSet=new ArrayList<>();
        String Url="http://toh.hadeya.net/api/getalldata/TOHItems/13007";

        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
              /*  if (dataSet != null){
                    dataSet.clear();

                }*/
                Iterator iterator = Parser.parseItem(response).iterator();
                while (iterator.hasNext()){
                    Item item = (Item) iterator.next();
                    //dataSet.add(customer);
                    addItem(item);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Stop the refreshing indicator
                Log.d("response", error.toString());
            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(strReq);

    }
    public static void storeItemInvoice()
    {
        //final List<Area> dataArea=new ArrayList<>();
        String Url="http://toh.hadeya.net/api/TOHInvoices/ItemTOHInvoices/13007?itemCode=1";

        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
               /* if (dataArea != null){
                    dataArea.clear();
                }*/
                Iterator iterator = Parser.parseItemInvoice(response).iterator();
                while (iterator.hasNext()){
                    ItemInvoice itemInvoice = (ItemInvoice) iterator.next();
                    // dataArea.add(movie);
                    addItemInvoice(itemInvoice);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(strReq);

        // return1 dataArea;
    }

    public static void addCustomer(Customer customer)
    {

        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(CustomerTable.CustomerCode, customer.getCustomerCode());
        values.put(CustomerTable.CustName, customer.getCustName());
        values.put(CustomerTable.StreetAra,customer.getStreetAra());
        values.put(CustomerTable.Classification,customer.getClassification());
        values.put(CustomerTable.PersonToConnect,customer.getPersonToConnect());
        values.put(CustomerTable.Tel,customer.getTel());
        values.put(CustomerTable.TAXID,customer.getTAXID());
        values.put(CustomerTable.Flag,"1");//
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        CustomerContentProvider moviesContentProvider=new CustomerContentProvider(mdatabase);
        moviesContentProvider.insert(CustomerContentProvider.CONTENT_URI_add,values);

    }
    public static void addClassfication(Classification classification)
    {

        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ClassificationTable.ClassificationId, classification.getId());
        values.put(ClassificationTable.ClassificationName, classification.getName());
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        ClassificationContentProvider classificationContentProvider=new ClassificationContentProvider(mdatabase);
        classificationContentProvider.insert(ClassificationContentProvider.CONTENT_URI_add,values);

    }
    public static void addArea(Area area)
    {

        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AreaTable.AreaId, area.getId());
        values.put(AreaTable.AreaName, area.getName());
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        AreaContentProvider areaContentProvider=new AreaContentProvider(mdatabase);
        areaContentProvider.insert(AreaContentProvider.CONTENT_URI_add,values);

    }
    public static void addCustomerInvoice(CustomerInvoice customerInvoice)
    {

        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CustomerInvoiceTable.InvoceId, customerInvoice.getInvoceId());
        values.put(CustomerInvoiceTable.InvoiceNo, customerInvoice.getInvoiceNo());
        values.put(CustomerInvoiceTable.Date, customerInvoice.getDate());
        values.put(CustomerInvoiceTable.Value, customerInvoice.getValue());
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        CustomerInvoiceContentProvider customerInvoiceContentProvider=new CustomerInvoiceContentProvider(mdatabase);
        customerInvoiceContentProvider.insert(CustomerInvoiceContentProvider.CONTENT_URI_add,values);

    }
    public static void addItem(Item item)
    {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ItemTable.UnitCode, item.getUnitCode());
        values.put(ItemTable.ItemName, item.getItemName());
        values.put(ItemTable.ItemNameLat, item.getItemNameLat());
        values.put(ItemTable.ItemCode, item.getItemCode());
        values.put(ItemTable.TaxSet, item.getTaxSet());
        values.put(ItemTable.SelPrice1Default, item.getSelPrice1Default());
        values.put(ItemTable.NotActive, item.getNotActive());
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        ItemContentProvider itemContentProvider=new ItemContentProvider(mdatabase);
        itemContentProvider.insert(ItemContentProvider.CONTENT_URI_add,values);
    }
    public static void addItemInvoice(ItemInvoice itemInvoice)
    {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ItemInvoiceTable.InvoiceNo, itemInvoice.getInvoiceNo());
        values.put(ItemInvoiceTable.CustomerName, itemInvoice.getCustomerName());
        values.put(ItemInvoiceTable.Price, itemInvoice.getPrice());
        values.put(ItemInvoiceTable.Quantity, itemInvoice.getQuantity());
        values.put(ItemInvoiceTable.Value, itemInvoice.getValue());
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        ItemInvoiceContentProvider itemInvoiceContentProvider=new ItemInvoiceContentProvider(mdatabase);
        itemInvoiceContentProvider.insert(ItemInvoiceContentProvider.CONTENT_URI_add,values);
    }
    //invoices
    public static void StoreInvoices()
    {
        // final List<Customer> dataSet=new ArrayList<>();
        String Url="http://toh.hadeya.net/api/TOHInvoices/repCodeTOHInvoices/13007";

        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
              /*  if (dataSet != null){
                    dataSet.clear();

                }*/
                Iterator iterator = Parser.parseInvoice(response).iterator();
                while (iterator.hasNext()){
                    Invoice invoice = (Invoice) iterator.next();
                    //dataSet.add(customer);
                    addInvoice(invoice);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Stop the refreshing indicator
                Log.d("response", error.toString());
            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(strReq);

    }
    public static void StoreInvoiceItems()
    {
        // final List<Customer> dataSet=new ArrayList<>();
        String Url="http://toh.hadeya.net/api/TOHInvoices/repCodeTOHInvoices/13007";

        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("response", response);
              /*  if (dataSet != null){
                    dataSet.clear();

                }*/
                Iterator iterator = Parser.parseInvoiceItem(response).iterator();
                while (iterator.hasNext()){
                    InvoiceItem invoiceItem = (InvoiceItem) iterator.next();
                    //dataSet.add(customer);
                    addInvoiceItem(invoiceItem);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Stop the refreshing indicator
                Log.d("response", error.toString());
            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(strReq);

    }
    public static void StoreInvoiceLocal(Invoice invoice)
    {
        long id=addInvoiceWithID(invoice);
        invoice.setId(id+"");
        for (int i=0;i<invoice.getInvoiceItems().size();i++)
            addInvoiceItemWithInvoice(invoice.getInvoiceItems().get(i),invoice);

    }
    public static void addInvoice(Invoice invoice)
    {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
       // values.put(InvoiceTable.Id, invoice.getId());
        values.put(InvoiceTable.InvoiceTypeId, invoice.getInvoiceTypeId());
        values.put(InvoiceTable.InvoiceNo, invoice.getInvoiceNo());
        values.put(InvoiceTable.CustmerId, invoice.getCustmerId());
        values.put(InvoiceTable.PayementTypeId, invoice.getPayementTypeId());
        values.put(InvoiceTable.Notes, invoice.getNotes());
        values.put(InvoiceTable.RefNO, invoice.getRefNO());
        values.put(InvoiceTable.RepCodeId, invoice.getRepCodeId());
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        InvoiceContentProvider invoiceContentProvider=new InvoiceContentProvider(mdatabase);
        Uri UriId=invoiceContentProvider.insert(InvoiceContentProvider.CONTENT_URI_add,values);
    }

    public static long addInvoiceWithID(Invoice invoice)
    {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // values.put(InvoiceTable.Id, invoice.getId());
        values.put(InvoiceTable.InvoiceTypeId, invoice.getInvoiceTypeId());
        values.put(InvoiceTable.InvoiceNo, invoice.getInvoiceNo());
        values.put(InvoiceTable.CustmerId, invoice.getCustmerId());
        values.put(InvoiceTable.PayementTypeId, invoice.getPayementTypeId());
        values.put(InvoiceTable.Notes, invoice.getNotes());
        values.put(InvoiceTable.RefNO, invoice.getRefNO());
        values.put(InvoiceTable.RepCodeId, invoice.getRepCodeId());
        InvoiceContentProvider invoiceContentProvider=new InvoiceContentProvider(mdatabase);
        Uri UriId=invoiceContentProvider.insert(InvoiceContentProvider.CONTENT_URI_add,values);
        UriMatcher sURIMatcher = new UriMatcher(
                UriMatcher.NO_MATCH);
        long uriType = Long.valueOf(UriId.getLastPathSegment());
       // int uriType = ContentUris.parseId(UriId);
        return uriType;
    }
    public static void addInvoiceItem(InvoiceItem invoiceItem)
    {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InvoiceItemTable.Id, invoiceItem.getId());
        values.put(InvoiceItemTable.InvoiceId, invoiceItem.getInvoiceId());
        values.put(InvoiceItemTable.ItemCode, invoiceItem.getItemCode());
        values.put(InvoiceItemTable.ItemName, invoiceItem.getItemName());
        values.put(InvoiceItemTable.Quantity, invoiceItem.getQuantity());
        values.put(InvoiceItemTable.Tax, invoiceItem.getTax());
        values.put(InvoiceItemTable.ExpityDate, invoiceItem.getExpityDate());
        values.put(InvoiceItemTable.Price, invoiceItem.getPrice());
        values.put(InvoiceItemTable.DiscountPercent, invoiceItem.getDiscountPercent());
        values.put(InvoiceItemTable.DiscountAmount, invoiceItem.getDiscountAmount());
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        InvoiceItemContentProvider invoiceItemContentProvider=new InvoiceItemContentProvider(mdatabase);
        invoiceItemContentProvider.insert(InvoiceItemContentProvider.CONTENT_URI_add,values);
    }
    //Shaimaa
    public static void addInvoiceItemWithInvoice(InvoiceItem invoiceItem, Invoice invoice)
    {
        ContentValues values = new ContentValues();
        values.put(InvoiceItemTable.Id, invoiceItem.getId());
        values.put(InvoiceItemTable.InvoiceId, invoice.getId());
        values.put(InvoiceItemTable.ItemCode, invoiceItem.getItemCode());
        values.put(InvoiceItemTable.ItemName, invoiceItem.getItemName());
        values.put(InvoiceItemTable.Quantity, invoiceItem.getQuantity());
        values.put(InvoiceItemTable.Tax, invoiceItem.getTax());
        values.put(InvoiceItemTable.ExpityDate, invoiceItem.getExpityDate());
        values.put(InvoiceItemTable.Price, invoiceItem.getPrice());
        values.put(InvoiceItemTable.DiscountPercent, invoiceItem.getDiscountPercent());
        values.put(InvoiceItemTable.DiscountAmount, invoiceItem.getDiscountAmount());
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        InvoiceItemContentProvider invoiceItemContentProvider=new InvoiceItemContentProvider(mdatabase);
        invoiceItemContentProvider.insert(InvoiceItemContentProvider.CONTENT_URI_add,values);
    }
    public static void  uploadInvoice(final Context context, String RepCodeId) {
        Customer customer =new Customer();
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        List<Invoice> invoiceList = getInvoices(context, RepCodeId);
        for (int i = 0; i < invoiceList.size(); i++) {
            customer = getCustomer(context, invoiceList.get(i).getCustmerId());
            invoiceItems = getItemInvoice(context, invoiceList.get(i).getId());
            invoiceList.get(i).setCustomer(customer);
            invoiceList.get(i).setInvoiceItems(invoiceItems);


        }
        JSONObject totalData =new JSONObject();
        JSONArray jsonArrayInvoices = new JSONArray();
        JSONObject myJsonObjectInvoice = new JSONObject();
        for (int j = 0; j < invoiceList.size(); j++){
            try {
                int inId=0;
                try
                {
                    inId=Integer.parseInt(invoiceList.get(j).getId());
                }catch (Exception e)
                {
                    inId=0;
                }
                int inTypeId=0;
                try
                {
                    inTypeId=Integer.parseInt( invoiceList.get(j).getInvoiceTypeId());
                }catch (Exception e)
                {
                    inTypeId=0;
                }
                int custId=0;
                try
                {
                    custId=Integer.parseInt(invoiceList.get(j).getCustmerId());
                }catch (Exception e)
                {
                    custId=0;
                }
                int payType=0;
                try
                {
                    payType=Integer.parseInt(invoiceList.get(j).getPayementTypeId());
                }catch (Exception e)
                {
                    payType=0;
                }
                int repCodeId=0;
                try
                {
                    repCodeId=Integer.parseInt(invoiceList.get(j).getRepCodeId());
                }catch (Exception e)
                {
                    repCodeId=0;
                }

                int custCode=0;
                try
                {
                    custCode= Integer.parseInt(invoiceList.get(j).getCustomer().getCustomerCode());
                }catch (Exception e)
                {
                    custCode=0;
                }

                int classef=0;
                try
                {
                    classef=Integer.parseInt(invoiceList.get(j).getCustomer().getClassification());
                }catch (Exception e)
                {
                    classef=0;
                }
                int salesArea=0;
                try
                {
                    salesArea=Integer.parseInt(invoiceList.get(j).getCustomer().getSaleAreaCode());
                }catch (Exception e)
                {
                    salesArea=0;
                }
                int salesRep=0;
                try
                {
                    salesRep=Integer.parseInt(invoiceList.get(j).getCustomer().getSalesRepCode());
                }catch (Exception e)
                {
                    salesRep=0;
                }
                Boolean notAct=false;
                try
                {
                    notAct= Boolean.parseBoolean(invoiceList.get(j).getCustomer().getNotActive());
                }catch (Exception e)
                {
                    notAct=false;
                }

                String custNameLt=invoiceList.get(j).getCustomer().getCustomerNameLat();
                if(custNameLt==null||custNameLt.isEmpty())
                    custNameLt="";
                String strAr=invoiceList.get(j).getCustomer().getStreetAra();
                if(strAr==null||strAr.isEmpty())
                    strAr="";
                String perToCo=invoiceList.get(j).getCustomer().getPersonToConnect();
                if(perToCo==null||perToCo.isEmpty())
                    perToCo="";
                String tel=invoiceList.get(j).getCustomer().getTel();
                if(tel==null||tel.isEmpty())
                    tel="";
                String taxid=invoiceList.get(j).getCustomer().getTAXID();
                if(taxid==null||taxid.isEmpty())
                    taxid="";
                String notes=invoiceList.get(j).getCustomer().getNotes();
                if(notes==null||notes.isEmpty())
                    notes="";
                String codelist=invoiceList.get(j).getCustomer().getCodeList();
                if(codelist==null||codelist.isEmpty())
                    codelist="";
                String invdate=invoiceList.get(j).getInvoiceDate();
                SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                String strDateInv = sm.format(new Date().getTime());
                if(invdate==null||invdate.isEmpty()||invdate.length()<9)
                    invdate=strDateInv;


                myJsonObjectInvoice.put("Id", inId);
                myJsonObjectInvoice.put("InvoiceTypeId",inTypeId);
                myJsonObjectInvoice.put("InvoiceNo", invoiceList.get(j).getInvoiceNo());
                myJsonObjectInvoice.put("CustmerId",custId);
                myJsonObjectInvoice.put("PayementTypeId", payType);
                myJsonObjectInvoice.put("Notes", invoiceList.get(j).getNotes());
                myJsonObjectInvoice.put("RefNO", invoiceList.get(j).getRefNO());
                myJsonObjectInvoice.put("InvoiceDate",invdate );
                myJsonObjectInvoice.put("RepCodeId",repCodeId);
                JSONObject customerObj=new JSONObject();
                customerObj.put("CustomerCode",custCode);
                customerObj.put("CustName", invoiceList.get(j).getCustomer().getCustName());
                customerObj.put("CustomerNameLat", custNameLt);
                customerObj.put("StreetAra", strAr);
                customerObj.put("Classification",classef );
                customerObj.put("PersonToConnect",perToCo );
                customerObj.put("Tel",tel );
                customerObj.put("TAXID",taxid);
                customerObj.put("SaleAreaCode",salesArea);
                customerObj.put("Notes",notes );
                customerObj.put("SalesRepCode",salesRep );
                customerObj.put("CodeList", codelist);
                customerObj.put("NotActive",notAct);
                JSONArray custInov=new JSONArray();
                customerObj.put("TOHInvoices",custInov );
              //  customerObj.put("TOHInvoices", invoiceList.get(j).getCustomer().getCustName());

                myJsonObjectInvoice.put("TOHCustomer",customerObj);
                JSONArray jsonArrayItems = new JSONArray();
                for (int i = 0; i < invoiceItems.size(); i++) {
                    int itId=0;
                    try
                    {
                        itId=Integer.parseInt(invoiceItems.get(i).getId());
                    }catch (Exception e)
                    {
                        itId=0;
                    }
                    int invId=0;
                    try
                    {
                        invId=Integer.parseInt(invoiceItems.get(i).getInvoiceId());
                    }catch (Exception e)
                    {
                        invId=0;
                    }
                    int qt=1;
                    try
                    {
                        qt=Integer.parseInt(invoiceItems.get(i).getQuantity());
                    }catch (Exception e)
                    {
                        qt=1;
                    }
                    int tx=0;
                    try
                    {
                        tx=Integer.parseInt( invoiceItems.get(i).getTax());
                    }catch (Exception e)
                    {
                        tx=0;
                    }

                    double prc=1;
                    try
                    {
                        prc=Double.parseDouble(invoiceItems.get(i).getPrice());
                    }catch (Exception e)
                    {
                        prc=1;
                    }
                    double dicPer=0;
                    try
                    {
                        dicPer=Double.parseDouble(invoiceItems.get(i).getDiscountPercent());
                    }catch (Exception e)
                    {
                        dicPer=0;
                    }
                    double dicAmt=0;
                    try
                    {
                        dicAmt=Double.parseDouble(invoiceItems.get(i).getDiscountAmount());
                    }catch (Exception e)
                    {
                        dicAmt=0;
                    }
                    String itemcode=invoiceItems.get(i).getItemCode();
                    if(itemcode==null||itemcode.isEmpty())
                        itemcode="";
                    String itemname=invoiceItems.get(i).getItemName();
                    if(itemname==null||itemname.isEmpty())
                        itemname="";

                    String expdate= invoiceItems.get(i).getExpityDate();
                    SimpleDateFormat sm2 = new SimpleDateFormat("yyyy-MM-dd");
                    String strDateExp = sm2.format(new Date().getTime());
                  //  Date dt = sm.parse(strDate);
                    if(expdate==null||expdate.isEmpty()||expdate.length()<9)
                        expdate=strDateExp;

                    JSONObject myJsonObjectItem = new JSONObject();
                    myJsonObjectItem.put("Id",itId);
                    myJsonObjectItem.put("InvoiceId",invId);
                    myJsonObjectItem.put("ItemCode", itemcode);
                    myJsonObjectItem.put("ItemName", itemname);
                    myJsonObjectItem.put("Quantity",qt);
                    myJsonObjectItem.put("Tax",tx);
                    myJsonObjectItem.put("ExpityDate",expdate );
                    myJsonObjectItem.put("Price",prc);
                    myJsonObjectItem.put("DiscountPercent",dicPer);
                    myJsonObjectItem.put("DiscountAmount",dicAmt);
                    jsonArrayItems.put(myJsonObjectItem);
                    myJsonObjectInvoice.put("TOHInvoiceDetails",jsonArrayItems);
                    //myJsonObjectInvoice.put(jsonObjItems);
                }
            } catch (Exception e) {

            }
            jsonArrayInvoices.put(myJsonObjectInvoice);
    }

      //  totalData.put(jsonArrayInvoices);


                String url="http://toh.hadeya.net/api/TOHInvoices/addTOHInvoices/"+RepCodeId;
        RequestQueue queue = Volley.newRequestQueue(context);
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST,url, jsonArrayInvoices,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                VolleyLog.v("Response:%n %s", response.toString(4));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.e("Error: ", error.getMessage());
                }
            });
        queue.add(req);


    }

    //store new user
    public static void StoreUser(final String name, String pass, final Context context, final LoginActivity login) {
        try {
        // final List<Customer> dataSet=new ArrayList<>();
        JSONObject myJsonObject = new JSONObject();

            myJsonObject.put("UserName",name);
            myJsonObject.put("UserPassword",pass);

        String Url="http://toh.hadeya.net/api/Account/Login";


        /////////////connection//////////
        JsonObjectRequest strReq = new JsonObjectRequest(Request.Method.POST, Url,myJsonObject ,new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject jsonObject)
            {
              String response=jsonObject.toString();
                User user  = Parser.parseUser(response);
                   //dataSet.add(customer);
                    addUser(user);

                Intent main = new Intent("MainTopicsActivity");
                context.startActivity(main);
                login.finish();
                //finish();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Stop the refreshing indicator
                Log.d("response", error.toString());
                Toast.makeText(context, "UserName or Password incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(strReq);
        } catch (JSONException e) {
            e.printStackTrace();

        }

    }

    //

    //

    public static void addUser(User user)
    {

        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable.UserName, user.getUserName());
        values.put(UserTable.UserPassword, user.getPassword());
        values.put(UserTable.RepCode, user.getRepCodId());
        values.put(UserTable.LoginStatus, "1");
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        UserContentProvider userContentProvider=new UserContentProvider(mdatabase);
        userContentProvider.insert(UserContentProvider.CONTENT_URI_Add,values);

    }
    //upload

    public static void uploade(Context context, String repCode)
    {
        mdatabase=new DataBaseHelper(context);
        List<Customer> customerList=new ArrayList<>();
        String[] projection={CustomerTable.ID,
                CustomerTable.CustName,
                CustomerTable.StreetAra,
                CustomerTable.Classification,
                CustomerTable.PersonToConnect,
                CustomerTable.Tel,
                CustomerTable.TAXID,
                CustomerTable.SaleAreaCode,
                CustomerTable.Flag,
        };

// Select All Query
        String selectQuery = "SELECT * FROM " + CustomerTable.CustomerTable+" where Flag=0 ";//and SalesRepCode="+repCode;
        CustomerContentProvider  movieContentProvider=new CustomerContentProvider( WriteDataToDB.mdatabase);
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = movieContentProvider.query(CustomerContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                //customer.setCustomerCode(cursor.getString(0));
                customer.setCustName(cursor.getString(1));
                customer.setStreetAra(cursor.getString(2));
                customer.setClassification(cursor.getString(3));
                customer.setPersonToConnect(cursor.getString(4));
                customer.setTel(cursor.getString(5));
                customer.setTAXID(cursor.getString(6));
                customer.setSaleAreaCode(cursor.getString(7));
                customer.setFlag(cursor.getString(8));
// Adding contact to list
                customerList.add(customer);
               // uploadCustomer(customer,context,repCode);

            } while (cursor.moveToNext());
        }
        uploadCustomers(customerList,context,repCode);



    }

    public static void uploadCustomers(List<Customer> customerList,final Context context,String repCode)
    {
        try {
        JSONArray jsonArray = new JSONArray();
        for (int i=0; i < customerList.size(); i++) {

            JSONObject myJsonObject = new JSONObject();
            myJsonObject.put("CustName",customerList.get(i).getCustName());
            myJsonObject.put("CustomerNameLat",customerList.get(i).getCustomerNameLat());
            myJsonObject.put("StreetAra",customerList.get(i).getStreetAra());
            myJsonObject.put("Classification",customerList.get(i).getClassification());
            myJsonObject.put("PersonToConnect",customerList.get(i).getPersonToConnect());
            myJsonObject.put("Tel",customerList.get(i).getTel());
            myJsonObject.put("TAXID",customerList.get(i).getTAXID());
            myJsonObject.put("SaleAreaCode",customerList.get(i).getSaleAreaCode());
            myJsonObject.put("Notes",customerList.get(i).getNotes());
            myJsonObject.put("SalesRepCode","13007");//ccustomerList.get(i).getSalesRepCode()+"*");
            myJsonObject.put("CodeList",customerList.get(i).getCodeList());
            myJsonObject.put("NotActive","false");//ccustomerList.get(i).getNotActive()+"*");
            jsonArray.put(myJsonObject);

            }

            String url="http://toh.hadeya.net/api/TOHCustomers/AddTOHCustomers/"+repCode;

            RequestQueue queue = Volley.newRequestQueue(context);
            JsonArrayRequest jobReq = new JsonArrayRequest(Request.Method.POST, url, jsonArray,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray jsonObject) {
                            Toast.makeText(context, "Uploaded Done"+jsonObject.toString(), Toast.LENGTH_SHORT).show();
                            //update local
                            updateDB(context);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(context, "Uploaded fail", Toast.LENGTH_SHORT).show();
                        }
                    });

            queue.add(jobReq);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void  updateDB(Context context )
    {
        DataBaseHelper dataBaseHelper=new DataBaseHelper(context);
        SQLiteDatabase db=dataBaseHelper.getWritableDatabase();
        String query="update customer set Flag=1 where Flag=0";
        db.execSQL(query);
    }

}
