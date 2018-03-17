package com.hadeya.tabonhandapp.store;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AyaAli on 16/03/2018.
 */

public class InvoiceTable {

    // Contacts table name
    public static final String InvoiceTable ="invoice" ;
    // Customer Table Columns names
    public static final String Id ="Id" ;
    public static final String InvoiceTypeId ="InvoiceTypeId" ;
    public static final String InvoiceNo ="InvoiceNo" ;
    public static final String CustmerId ="CustmerId" ;
    public static final String PayementTypeId ="PayementTypeId" ;
    public static final String Notes ="Notes" ;
    public static final String RefNO ="RefNO" ;
    public static final String RepCodeId ="RepCodeId" ;

    public static String CREATE_Database_TABLE = "CREATE TABLE " + InvoiceTable + "("
            + Id + " TEXT PRIMARY KEY,"
            + InvoiceTypeId + " TEXT,"
            + InvoiceNo + " TEXT,"
            + CustmerId + " TEXT,"
            + PayementTypeId + " TEXT,"
            + RefNO + " TEXT,"
            + Notes + " TEXT,"
            + RepCodeId + " TEXT" + ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_Database_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + InvoiceTable);
        onCreate(database);
    }
}
