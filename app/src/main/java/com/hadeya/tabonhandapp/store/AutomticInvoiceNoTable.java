package com.hadeya.tabonhandapp.store;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Shaimaa Derbaz on 5/2/2018.
 */

public class AutomticInvoiceNoTable {


    public static final String AutomticInvoiceNoTable ="AutomticInvoiceNo" ;
    // Customer Table Columns names
    public static final String LoginRepCode ="LoginRepCode" ;
    public static final String SerialInvoice ="SerialInvoice" ;

    public static String CREATE_Database_TABLE = "CREATE TABLE " + AutomticInvoiceNoTable + "("
            + LoginRepCode + " TEXT,"
            + SerialInvoice + " INTEGER DEFAULT 0" + ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_Database_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + AutomticInvoiceNoTable);
        onCreate(database);
    }

}
