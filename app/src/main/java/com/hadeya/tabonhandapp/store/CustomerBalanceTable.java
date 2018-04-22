package com.hadeya.tabonhandapp.store;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AyaAli on 2018-04-23.
 */

public class CustomerBalanceTable {
    // Contacts table name
    public static final String CustomerBalanceTable ="CustomerBalanceTable" ;
    // Customer Table Columns names
    public static final String CustomerCode ="CustomerCode" ;
    public static final String araName ="araName" ;
    public static final String balance ="balance" ;
    public static final String SalesRepCode ="SalesRepCode" ;

    public static String CREATE_Database_TABLE = "CREATE TABLE " + CustomerBalanceTable + "("
            + CustomerCode + " TEXT PRIMARY KEY,"
            + araName + " TEXT ,"
            + balance + " TEXT ,"
            + SalesRepCode + " TEXT" + ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_Database_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + CustomerBalanceTable);
        onCreate(database);
    }
}
