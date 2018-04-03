package com.hadeya.tabonhandapp.store;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AyaAli on 03/04/2018.
 */

public class ReceiptTable {

    // Contacts table name
    public static final String ReceiptTable ="receipt" ;
    // Customer Table Columns names
    public static final String ID ="ID" ;
    public static final String RecNo ="RecNo" ;
    public static final String RecDate ="RecDate" ;
    public static final String RecieptTypeId ="RecieptTypeId" ;
    public static final String CustmerId ="CustmerId" ;
    public static final String Value ="Value" ;
    public static final String Notes ="Notes" ;
    public static final String RefNO ="RefNO" ;

    public static String CREATE_Database_TABLE = "CREATE TABLE " + ReceiptTable + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + RecNo + " TEXT,"
            + RecDate + " TEXT,"
            + RecieptTypeId + " TEXT,"
            + CustmerId + " TEXT,"
            + Value + " TEXT,"
            + Notes + " TEXT,"
            + RefNO + " TEXT" + ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_Database_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + ReceiptTable);
        onCreate(database);
    }
}
