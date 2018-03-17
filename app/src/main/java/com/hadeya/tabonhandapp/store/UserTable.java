package com.hadeya.tabonhandapp.store;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AyaAli on 15/03/2018.
 */

public class UserTable {
    // Contacts table name
    public static final String UserTable ="user" ;
    // Customer Table Columns names
    public static final String UserName ="UserName" ;
    public static final String UserPassword ="UserPassword" ;
    public static final String RepCode ="RepCode" ;
    public static final String LoginStatus ="LoginStatus" ;

    public static String CREATE_Database_TABLE = "CREATE TABLE " + UserTable + "("
            + RepCode + " TEXT PRIMARY KEY,"
            + UserName + " TEXT,"
            + UserPassword + " TEXT,"
            + LoginStatus + " TEXT" + ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_Database_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + UserTable);
        onCreate(database);
    }
}
