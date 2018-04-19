package com.hadeya.tabonhandapp.store;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Shaimaa Derbaz on 4/19/2018.
 */

public class InvoiceSimpleTable {
    // Contacts table name
    public static final String InvoiceSimpleTable ="invoiceSimple" ;
    // Customer Table Columns names
    public static final String Id ="Id" ;
    public static final String InvoiceNo ="InvoiceNo" ;
    public static final String InvoiceDate ="InvoiceDate" ;
    public static final String CustmerName ="CustmerName" ;
    public static final String Net ="Net" ;


    public static String CREATE_Database_TABLE = "CREATE TABLE " + InvoiceSimpleTable + "("
            + Id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + InvoiceNo + " TEXT,"
            + InvoiceDate + " TEXT,"
            + CustmerName + " TEXT,"
            + Net + " TEXT" + ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_Database_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + InvoiceSimpleTable);
        onCreate(database);
    }
}
