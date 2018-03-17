package com.hadeya.tabonhandapp.store;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AyaAli on 15/03/2018.
 */

public class ItemTable {

    // Contacts table name
    public static final String ItemTable ="item" ;
    // Customer Table Columns names
    public static final String UnitCode ="UnitCode" ;
    public static final String ItemName ="ItemName" ;
    public static final String ItemNameLat ="ItemNameLat" ;
    public static final String ItemCode ="ItemCode" ;
    public static final String TaxSet ="TaxSet" ;
    public static final String SelPrice1Default ="SelPrice1Default" ;
    public static final String NotActive ="NotActive" ;

    public static String CREATE_Database_TABLE = "CREATE TABLE " + ItemTable + "("
            + UnitCode + " TEXT PRIMARY KEY,"
            + ItemName + " TEXT,"
            + ItemNameLat + " TEXT,"
            + ItemCode + " TEXT,"
            + TaxSet + " TEXT,"
            + SelPrice1Default + " TEXT,"
            + NotActive + " TEXT" + ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_Database_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + ItemTable);
        onCreate(database);
    }
}
