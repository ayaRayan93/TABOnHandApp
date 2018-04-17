package com.hadeya.tabonhandapp.store;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Shaimaa Derbaz on 4/16/2018.
 */

public class InvoiceTypeTable {

    // Contacts table name
    public static final String InvoiceTypeTable ="InvoiceType" ;
    // Customer Table Columns names
    public static final String Id ="Id" ;
    public static final String BranchCode ="BranchCode" ;
    public static final String trxtypecode ="trxtypecode" ;
    public static final String TrxKind ="TrxKind" ;
    public static final String TrxTypeID ="TrxTypeID" ;
    public static final String TrxType ="TrxType" ;
    public static final String TrxArbName ="TrxArbName" ;
    public static final String TrxEngName ="TrxEngName" ;


    public static String CREATE_Database_TABLE = "CREATE TABLE " + InvoiceTypeTable + "("
            + Id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + BranchCode + " TEXT,"
            + trxtypecode + " TEXT,"
            + TrxKind + " TEXT,"
            + TrxTypeID + " TEXT,"
            + TrxType + " TEXT,"
            + TrxArbName + " TEXT,"
            + TrxEngName + " TEXT"+ ")";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_Database_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + InvoiceTypeTable);
        onCreate(database);
    }
}
