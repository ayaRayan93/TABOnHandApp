package com.hadeya.tabonhandapp.store;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AyaAli on 25/03/2018.
 */

public class CustomerInvoiceTable {

    // Contacts table name
    public static final String CustomerInvoiceTable ="CustomerInvoiceTable" ;
    // Customer Table Columns names
    public static final String InvoceId ="InvoceId" ;
    public static final String InvoiceNo ="InvoiceNo" ;
    public static final String Date ="Date" ;
    public static final String Value ="Value" ;
    public static final String CustomerId ="CustomerId" ;
    public static String CREATE_Database_TABLE = "CREATE TABLE " + CustomerInvoiceTable + "("
            + InvoceId + " TEXT PRIMARY KEY,"
            +InvoiceNo+" TEXT,"
            +Date+" TEXT,"
            + Value + " TEXT,"
            + CustomerId + " TEXT" +
            ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_Database_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + CustomerInvoiceTable);
        onCreate(database);
    }
}
