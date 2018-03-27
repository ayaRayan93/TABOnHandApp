package com.hadeya.tabonhandapp.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AyaAli on 07/03/2018.
 */


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "appDB";
    private static final int DATABASE_VERSION = 1;

    public  SQLiteDatabase db;
    public DataBaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        //basic data
        CustomerTable.onCreate(db);
        AreaTable.onCreate(db);
        ClassificationTable.onCreate(db);
        CustomerInvoiceTable.onCreate(db);
        ItemTable.onCreate(db);
        ItemInvoiceTable.onCreate(db);

        //
        InvoiceTable.onCreate(db);
        InvoiceItemTable.onCreate(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        CustomerTable.onUpgrade(db,oldVersion,newVersion);
        AreaTable.onUpgrade(db,oldVersion,newVersion);
        ClassificationTable.onUpgrade(db,oldVersion,newVersion);
        ItemTable.onUpgrade(db,oldVersion,newVersion);
        CustomerInvoiceTable.onUpgrade(db,oldVersion,newVersion);
        ItemInvoiceTable.onUpgrade(db,oldVersion,newVersion);

        InvoiceTable.onUpgrade(db,oldVersion,newVersion);
        InvoiceItemTable.onUpgrade(db,oldVersion,newVersion);
    }
}
