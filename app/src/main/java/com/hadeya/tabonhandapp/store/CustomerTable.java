package com.hadeya.tabonhandapp.store;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AyaAli on 07/03/2018.
 */

public class CustomerTable {
    // Contacts table name
    public static final String CustomerTable ="customer" ;
    // Customer Table Columns names
    public static final String ID ="ID" ;
    public static final String CustName ="CustName" ;
    public static final String StreetAra="StreetAra" ;
    public static final String Classification="classification" ;
    public static final String PersonToConnect="PersonToConnect" ;
    public static final String Tel="Tel" ;
    public static final String TAXID="TAXID" ;
    public static final String SaleAreaCode="SaleAreaCode" ;
    public static final String SalesRepCode="SalesRepCode" ;
    public static final String Balance="Balance" ;
    public static final String Flag="Flag" ;

    public static String CREATE_Database_TABLE = "CREATE TABLE " + CustomerTable + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            +CustName+" TEXT,"
            +StreetAra+" TEXT,"
            +Classification+" TEXT,"
            +PersonToConnect+" TEXT,"
            + Tel + " TEXT ,"
            + TAXID + " TEXT,"
            + SalesRepCode + " TEXT,"
            + Flag + " TEXT,"
            + SaleAreaCode + " TEXT,"
            + Balance + " TEXT" + ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_Database_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + CustomerTable);
        onCreate(database);
    }
}
