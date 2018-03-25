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
import com.hadeya.tabonhandapp.models.CustomerInvoice;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;
import com.hadeya.tabonhandapp.models.Item;
import com.hadeya.tabonhandapp.models.ItemInvoice;
import com.hadeya.tabonhandapp.models.User;

import java.util.Iterator;

/**
 * Created by AyaAli on 09/03/2018.
 */

public class WriteDataToDB {

    public static DataBaseHelper mdatabase;
    public static void downloadData()
    {
        //basic data
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

        // return dataArea;
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
    //
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
    public static void StoreUser()
    {
        // final List<Customer> dataSet=new ArrayList<>();
        String Url="http://toh.hadeya.net/api/Account";


        /////////////connection//////////
        StringRequest strReq = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>()
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


    //



    //
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
        InvoiceContentProvider invoiceContentProvider=new InvoiceContentProvider(mdatabase);
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
        InvoiceItemContentProvider invoiceItemContentProvider=new InvoiceItemContentProvider(mdatabase);
        invoiceItemContentProvider.insert(InvoiceItemContentProvider.CONTENT_URI_add,values);
    }

    public static void addUser(User user)
    {

        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable.RepCode, user.getRepCodId());
        values.put(UserTable.UserName, user.getUserName());
        values.put(UserTable.UserPassword, user.getPassword());
        values.put(UserTable.LoginStatus, "1");
        // Inserting Row
        //db.insert(TABLE_MOVIES, null, values);
        //db.close(); // Closing database connection
        AreaContentProvider areaContentProvider=new AreaContentProvider(mdatabase);
        areaContentProvider.insert(AreaContentProvider.CONTENT_URI_add,values);

    }
}
