package com.hadeya.tabonhandapp.store;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AyaAli on 25/03/2018.
 */

public class ItemInvoiceTable {

    // Contacts table name
    public static final String ItemInvoiceTable ="ItemInvoiceTable" ;
    // Customer Table Columns names
    public static final String InvoiceNo ="InvoiceNo" ;
    public static final String CustomerName ="CustomerName" ;
    public static final String Price ="Price" ;
    public static final String Quantity ="Quantity" ;
    public static final String Value ="Value" ;
    public static final String ItemCode ="ItemCode" ;

    public static String CREATE_Database_TABLE = "CREATE TABLE " + ItemInvoiceTable + "("
            + InvoiceNo + " TEXT PRIMARY KEY,"
            +CustomerName+" TEXT,"
            +Price+" TEXT,"
            +Quantity+" TEXT,"
            +Value+" TEXT,"
            + ItemCode + " TEXT" + ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_Database_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + ItemInvoiceTable);
        onCreate(database);
    }
}
