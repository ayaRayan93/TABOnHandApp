package com.hadeya.tabonhandapp.store;

import android.content.ContentValues;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hadeya.tabonhandapp.json.Parser;
import com.hadeya.tabonhandapp.models.Area;
import com.hadeya.tabonhandapp.models.Classification;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.app.AppController;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;
import com.hadeya.tabonhandapp.models.Item;
import com.hadeya.tabonhandapp.models.User;

import java.util.Iterator;

/**
 * Created by AyaAli on 09/03/2018.
 */

public class WriteDataToDB {

    public static void downloadData()
    {
        storeCustomer("13007");
        storeClassification();
        storeArea();
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

        //return dataSet;
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

       // return dataClass;
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

       // return dataArea;
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
    public static void StoreInvoices()
    {
        // final List<Customer> dataSet=new ArrayList<>();
        String Url="http://toh.hadeya.net/api/TOHCustomers/repCodeTOHCustomers/";

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
        String Url="http://toh.hadeya.net/api/TOHCustomers/repCodeTOHCustomers/";

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
    public static void StoreUser()
    {
        // final List<Customer> dataSet=new ArrayList<>();
        String Url="http://toh.hadeya.net/api/TOHCustomers/repCodeTOHCustomers/";

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
                    User user = (User) iterator.next();
                    //dataSet.add(customer);
                    addUser(user);
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

    public static void addCustomer(Customer customer)
    {

        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CustomerTable.CustomerCode, customer.getCustomerCode());
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
        CustomerContentProvider moviesContentProvider=new CustomerContentProvider();
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
        ClassificationContentProvider classificationContentProvider=new ClassificationContentProvider();
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
        AreaContentProvider areaContentProvider=new AreaContentProvider();
        areaContentProvider.insert(AreaContentProvider.CONTENT_URI_add,values);

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
        ItemContentProvider itemContentProvider=new ItemContentProvider();
        itemContentProvider.insert(ItemContentProvider.CONTENT_URI_add,values);
    }
    public static void addInvoice(Invoice invoice)
    {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InvoiceTable.Id, invoice.getId());
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
        InvoiceContentProvider invoiceContentProvider=new InvoiceContentProvider();
        invoiceContentProvider.insert(InvoiceContentProvider.CONTENT_URI_add,values);
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
        InvoiceContentProvider invoiceContentProvider=new InvoiceContentProvider();
        invoiceContentProvider.insert(InvoiceContentProvider.CONTENT_URI_add,values);
    }

    public static void addUser(User user)
    {

        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable.RepCode, user.getRepCodId());
        values.put(UserTable.UserName, user.getUserName());
        values.put(UserTable.UserPassword, user.getPassword());
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        AreaContentProvider areaContentProvider=new AreaContentProvider();
        areaContentProvider.insert(AreaContentProvider.CONTENT_URI_add,values);

    }
}
