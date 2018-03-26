package com.hadeya.tabonhandapp.store;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hadeya.tabonhandapp.app.AppController;
import com.hadeya.tabonhandapp.json.Parser;
import com.hadeya.tabonhandapp.models.Area;
import com.hadeya.tabonhandapp.models.Classification;
import com.hadeya.tabonhandapp.models.Customer;
import com.hadeya.tabonhandapp.models.CustomerInvoice;
import com.hadeya.tabonhandapp.models.Invoice;
import com.hadeya.tabonhandapp.models.InvoiceItem;
import com.hadeya.tabonhandapp.models.Item;
import com.hadeya.tabonhandapp.models.ItemInvoice;
import com.hadeya.tabonhandapp.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by AyaAli on 09/03/2018.
 */

public class ReadDataFromDB {


    //basic data
    public static List<Customer> getAllCustomerForSalesPerson(Context context)
    {
        String[] projection={CustomerTable.CustomerCode,
                CustomerTable.CustName,
                CustomerTable.StreetAra,
                CustomerTable.Classification,
                CustomerTable.PersonToConnect,
                CustomerTable.Tel,
                CustomerTable.TAXID,
                CustomerTable.SaleAreaCode,
                CustomerTable.Flag,
        };

        List<Customer> customerList = new ArrayList<>();
// Select All Query
        String selectQuery = "SELECT * FROM " + CustomerTable.CustomerTable;
        CustomerContentProvider  movieContentProvider=new CustomerContentProvider( WriteDataToDB.mdatabase);
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = movieContentProvider.query(CustomerContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                customer.setCustomerCode(cursor.getString(0));
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
            } while (cursor.moveToNext());
        }
// return contact list
        return customerList;

    }

    public static List<Classification> getAllCustomerClassification()
    {
        String[] projection={ClassificationTable.ClassificationId,
                ClassificationTable.ClassificationName,
        };

        List<Classification> ClassificationList = new ArrayList<>();
// Select All Query
        String selectQuery = "SELECT * FROM " + ClassificationTable.ClassificationTable;
        ClassificationContentProvider  ClassificationContentProvider=new ClassificationContentProvider( WriteDataToDB.mdatabase);
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = ClassificationContentProvider.query(ClassificationContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Classification classification = new Classification();
                classification.setId(cursor.getString(0));
                classification.setName(cursor.getString(1));
// Adding contact to list
                ClassificationList.add(classification);
            } while (cursor.moveToNext());
        }
// return contact list
        return ClassificationList;

    }

    public static List<Area> getAllCustomerArea(Context context)
    {
        String[] projection={AreaTable.AreaId,
                AreaTable.AreaName,
        };

        List<Area> AreaList = new ArrayList<>();
// Select All Query
        String selectQuery = "SELECT * FROM " + AreaTable.AreaTable;
        AreaContentProvider  areaContentProvider=new AreaContentProvider( WriteDataToDB.mdatabase);
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = areaContentProvider.query(AreaContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Area area = new Area();
                area.setId(cursor.getString(0));
                area.setName(cursor.getString(1));
// Adding contact to list
                AreaList.add(area);
            } while (cursor.moveToNext());
        }
// return contact list
        return AreaList;

    }

    public static List<CustomerInvoice> getAllCustomerInvoice(Context context)
    {
        String[] projection={CustomerInvoiceTable.InvoceId,
                CustomerInvoiceTable.InvoiceNo,
                CustomerInvoiceTable.Date,
                CustomerInvoiceTable.Value,
        };

        List<CustomerInvoice> CustomerInvoiceList = new ArrayList<>();
// Select All Query
        String selectQuery = "SELECT * FROM " + CustomerInvoiceTable.CustomerInvoiceTable;
        CustomerInvoiceContentProvider  customerInvoiceContentProvider=new CustomerInvoiceContentProvider( WriteDataToDB.mdatabase);
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = customerInvoiceContentProvider.query(CustomerInvoiceContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CustomerInvoice customerInvoice = new CustomerInvoice();
                customerInvoice.setInvoceId(cursor.getString(0));
                customerInvoice.setInvoiceNo(cursor.getString(1));
                customerInvoice.setDate(cursor.getString(2));
                customerInvoice.setValue(cursor.getString(3));
// Adding contact to list
                CustomerInvoiceList.add(customerInvoice);
            } while (cursor.moveToNext());
        }
// return contact list
        return CustomerInvoiceList;

    }

    public static List<Item> getAllItems(Context context)
    {
        String[] projection={ItemTable.UnitCode,
                ItemTable.ItemName,
                ItemTable.ItemNameLat,
                ItemTable.ItemCode,
                ItemTable.TaxSet,
                ItemTable.SelPrice1Default,
                ItemTable.NotActive,
        };

        List<Item> itemList = new ArrayList<>();
// Select All Query
        String selectQuery = "SELECT * FROM " + ItemTable.ItemTable;
        ItemContentProvider  itemContentProvider=new ItemContentProvider( WriteDataToDB.mdatabase);
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = itemContentProvider.query(ItemContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setUnitCode(cursor.getString(0));
                item.setItemName(cursor.getString(1));
                item.setItemNameLat(cursor.getString(2));
                item.setItemCode(cursor.getString(3));
                item.setTaxSet(cursor.getString(4));
                item.setSelPrice1Default(cursor.getString(5));
                item.setNotActive(cursor.getString(6));
// Adding contact to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }
// return contact list
        return itemList;

    }
    public static List<ItemInvoice> getAllItemInvoice(Context context)
    {
        String[] projection={ItemInvoiceTable.InvoiceNo,
                ItemInvoiceTable.CustomerName,
                ItemInvoiceTable.Price,
                ItemInvoiceTable.Quantity,
                ItemInvoiceTable.Value,
        };

        List<ItemInvoice> ItemInvoiceList = new ArrayList<>();
// Select All Query
        String selectQuery = "SELECT * FROM " + ItemInvoiceTable.ItemInvoiceTable;
        ItemInvoiceContentProvider  itemInvoiceContentProvider=new ItemInvoiceContentProvider( WriteDataToDB.mdatabase);
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = itemInvoiceContentProvider.query(ItemInvoiceContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ItemInvoice itemInvoice = new ItemInvoice();
                itemInvoice.setInvoiceNo(cursor.getString(0));
                itemInvoice.setCustomerName(cursor.getString(1));
                itemInvoice.setPrice(cursor.getString(2));
                itemInvoice.setQuantity(cursor.getString(3));
                itemInvoice.setValue(cursor.getString(4));
// Adding contact to list
                ItemInvoiceList.add(itemInvoice);
            } while (cursor.moveToNext());
        }
// return contact list
        return ItemInvoiceList;

    }

    //
    public static List<InvoiceItem> getItemInvoices(Context context)
    {
        String[] projection={InvoiceItemTable.Id,
                InvoiceItemTable.InvoiceId,
                InvoiceItemTable.ItemCode,
                InvoiceItemTable.ItemName,
                InvoiceItemTable.Quantity,
                InvoiceItemTable.Tax,
                InvoiceItemTable.ExpityDate,
                InvoiceItemTable.Price,
                InvoiceItemTable.DiscountPercent,
                InvoiceItemTable.DiscountAmount,
        };

        List<InvoiceItem> invoiceItemList = new ArrayList<>();
// Select All Query
        String selectQuery = "SELECT * FROM " + InvoiceItemTable.InvoiceItemTable;
        InvoiceItemContentProvider  itemInvoiceContentProvider=new InvoiceItemContentProvider( WriteDataToDB.mdatabase);
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = itemInvoiceContentProvider.query(InvoiceItemContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                InvoiceItem invoiceItem = new InvoiceItem();
                invoiceItem.setId(cursor.getString(0));
                invoiceItem.setInvoiceId(cursor.getString(1));
                invoiceItem.setItemCode(cursor.getString(2));
                invoiceItem.setItemName(cursor.getString(3));
                invoiceItem.setQuantity(cursor.getString(4));
                invoiceItem.setTax(cursor.getString(5));
                invoiceItem.setExpityDate(cursor.getString(6));
                invoiceItem.setPrice(cursor.getString(7));
                invoiceItem.setDiscountPercent(cursor.getString(8));
                invoiceItem.setDiscountAmount(cursor.getString(9));
// Adding contact to list
                invoiceItemList.add(invoiceItem);
            } while (cursor.moveToNext());
        }
// return contact list
        return invoiceItemList;

    }
    public static List<Invoice> getCustomerInvoices(Context context)
    {
        String[] projection={InvoiceTable.Id,
                InvoiceTable.InvoiceTypeId,
                InvoiceTable.InvoiceNo,
                InvoiceTable.CustmerId,
                InvoiceTable.PayementTypeId,
                InvoiceTable.Notes,
                InvoiceTable.RefNO,
                InvoiceTable.RepCodeId,
        };

        List<Invoice> invoiceList = new ArrayList<>();
// Select All Query
        String selectQuery = "SELECT * FROM " + InvoiceTable.InvoiceTable;
        InvoiceContentProvider  invoiceContentProvider=new InvoiceContentProvider( WriteDataToDB.mdatabase);
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = invoiceContentProvider.query(InvoiceContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Invoice invoice = new Invoice();
                invoice.setId(cursor.getString(0));
                invoice.setInvoiceTypeId(cursor.getString(1));
                invoice.setInvoiceNo(cursor.getString(2));
                invoice.setCustmerId(cursor.getString(3));
                invoice.setPayementTypeId(cursor.getString(4));
                invoice.setNotes(cursor.getString(5));
                invoice.setRefNO(cursor.getString(6));
                invoice.setRepCodeId(cursor.getString(7));
// Adding contact to list
                invoiceList.add(invoice);
            } while (cursor.moveToNext());
        }
// return contact list
        return invoiceList;

    }
    public static List<User> getUser(String name,String pass)
    {
        try {

        String[] projection={UserTable.RepCode,
                UserTable.UserName,
                UserTable.UserPassword,
        };

        List<User> userList = new ArrayList<>();
// Select All Query
        String selectQuery = "SELECT * FROM " + UserTable.UserTable+" where UserName="+name+"and UserPassword="+pass;
        UserContentProvider  userContentProvider=new UserContentProvider( WriteDataToDB.mdatabase);
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = userContentProvider.query(UserContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setRepCodId(cursor.getString(0));
                user.setUserName(cursor.getString(1));
                user.setPassword(cursor.getString(1));
// Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
// return contact list
        return userList;

        }
        catch(Exception e)
        {
         return null;
        }

    }
    public static List<User> getUser()
    {
        try {

            String[] projection={UserTable.RepCode,
                    UserTable.UserName,
                    UserTable.UserPassword,
            };

            List<User> userList = new ArrayList<>();
// Select All Query
            String selectQuery = "SELECT * FROM " + UserTable.UserTable;
            UserContentProvider  userContentProvider=new UserContentProvider( WriteDataToDB.mdatabase);
            //SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = userContentProvider.query(UserContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setRepCodId(cursor.getString(0));
                    user.setUserName(cursor.getString(1));
                    user.setPassword(cursor.getString(1));
// Adding contact to list
                    userList.add(user);
                } while (cursor.moveToNext());
            }
// return contact list
            return userList;

        }
        catch(Exception e)
        {
            return null;
        }

    }


    public static void uploade(Context context,String repCode)
    {
        String[] projection={CustomerTable.CustomerCode,
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
        String selectQuery = "SELECT * FROM " + CustomerTable.CustomerTable+"where Flag=0 and SalesRepCode="+repCode;
        CustomerContentProvider  movieContentProvider=new CustomerContentProvider( WriteDataToDB.mdatabase);
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = movieContentProvider.query(CustomerContentProvider.CONTENT_URI,projection,selectQuery,null,null); //db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                customer.setCustomerCode(cursor.getString(0));
                customer.setCustName(cursor.getString(1));
                customer.setStreetAra(cursor.getString(2));
                customer.setClassification(cursor.getString(3));
                customer.setPersonToConnect(cursor.getString(4));
                customer.setTel(cursor.getString(5));
                customer.setTAXID(cursor.getString(6));
                customer.setSaleAreaCode(cursor.getString(7));
                customer.setFlag(cursor.getString(8));
// Adding contact to list
                uploadCustomer(customer,context,repCode);
            } while (cursor.moveToNext());
        }


    }

    public static void uploadCustomer(final Customer customer, final Context context, String repCode)
    {

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST,"http://toh.hadeya.net/api/TOHCustomers/addTOHCustomer/"+repCode, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Uploaded Done", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Uploaded Fail", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("CustomerCode",customer.getCustomerCode());
                params.put("CustName",customer.getCustName());
                params.put("CustomerNameLat",customer.getCustomerNameLat());
                params.put("StreetAra",customer.getStreetAra());
                params.put("Classification",customer.getClassification());
                params.put("PersonToConnect",customer.getPersonToConnect());
                params.put("Tel",customer.getTel());
                params.put("TAXID",customer.getTAXID());
                params.put("SaleAreaCode",customer.getSaleAreaCode());
                params.put("Notes",customer.getNotes());
                params.put("SalesRepCode",customer.getSalesRepCode());
                params.put("CodeList",customer.getCodeList());
                params.put("NotActive",customer.getNotes());


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }


}
