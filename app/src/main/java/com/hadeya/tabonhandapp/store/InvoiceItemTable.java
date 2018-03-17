package com.hadeya.tabonhandapp.store;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AyaAli on 16/03/2018.
 */

public class InvoiceItemTable {
    // Contacts table name
    public static final String InvoiceItemTable ="invoice" ;
    // Customer Table Columns names
    public static final String Id ="Id" ;
    public static final String InvoiceId ="InvoiceId" ;
    public static final String ItemCode ="ItemCode" ;
    public static final String ItemName ="ItemName" ;
    public static final String Quantity ="Quantity" ;
    public static final String Tax ="Tax" ;
    public static final String ExpityDate ="ExpityDate" ;
    public static final String Price ="Price" ;
    public static final String DiscountPercent ="DiscountPercent" ;
    public static final String DiscountAmount ="DiscountAmount" ;

    public static String CREATE_Database_TABLE = "CREATE TABLE " + InvoiceItemTable + "("
            + Id + " TEXT PRIMARY KEY,"
            + InvoiceId + " TEXT,"
            + ItemCode + " TEXT,"
            + ItemName + " TEXT,"
            + Quantity + " TEXT,"
            + Tax + " TEXT,"
            + ExpityDate + " TEXT,"
            + Price + " TEXT,"
            + DiscountPercent + " TEXT,"
            + DiscountAmount + " TEXT" + ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_Database_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + InvoiceItemTable);
        onCreate(database);
    }
}
