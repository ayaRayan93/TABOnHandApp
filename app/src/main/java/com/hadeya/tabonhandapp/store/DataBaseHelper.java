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
        ReceiptTable.onCreate(db);
        //
        UserTable.onCreate(db);
        InvoiceTypeTable.onCreate(db);

    }

public static  void resetDataBase(SQLiteDatabase db)
{
    String clearDBQuery = "DELETE FROM "+AreaTable.AreaTable;
    db.execSQL(clearDBQuery);
    clearDBQuery = "DELETE FROM "+ClassificationTable.ClassificationTable;
    db.execSQL(clearDBQuery);
    clearDBQuery = "DELETE FROM "+CustomerTable.CustomerTable;
    db.execSQL(clearDBQuery);
    clearDBQuery = "DELETE FROM "+CustomerInvoiceTable.CustomerInvoiceTable;
    db.execSQL(clearDBQuery);
    clearDBQuery = "DELETE FROM "+InvoiceTable.InvoiceTable;
    db.execSQL(clearDBQuery);
    clearDBQuery = "DELETE FROM "+InvoiceItemTable.InvoiceItemTable;
    db.execSQL(clearDBQuery);
    clearDBQuery = "DELETE FROM "+ItemTable.ItemTable;
    db.execSQL(clearDBQuery);
    clearDBQuery = "DELETE FROM "+ItemInvoiceTable.ItemInvoiceTable;
    db.execSQL(clearDBQuery);
    //clearDBQuery = "DELETE FROM "+UserTable.UserTable;
    //db.execSQL(clearDBQuery);
    clearDBQuery = "DELETE FROM "+InvoiceTypeTable.InvoiceTypeTable;
    db.execSQL(clearDBQuery);
}
    public static  void resetInvoice(SQLiteDatabase db)
    {

        String clearDBQuery = "DELETE FROM "+InvoiceTable.InvoiceTable;
        db.execSQL(clearDBQuery);
        clearDBQuery = "DELETE FROM "+InvoiceItemTable.InvoiceItemTable;
        db.execSQL(clearDBQuery);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        CustomerTable.onUpgrade(db,oldVersion,newVersion);
        AreaTable.onUpgrade(db,oldVersion,newVersion);
        ClassificationTable.onUpgrade(db,oldVersion,newVersion);
        ItemTable.onUpgrade(db,oldVersion,newVersion);
        CustomerInvoiceTable.onUpgrade(db,oldVersion,newVersion);
        ItemInvoiceTable.onUpgrade(db,oldVersion,newVersion);
        UserTable.onUpgrade(db,oldVersion,newVersion);

        InvoiceTable.onUpgrade(db,oldVersion,newVersion);
        InvoiceItemTable.onUpgrade(db,oldVersion,newVersion);
        InvoiceTypeTable.onUpgrade(db,oldVersion,newVersion);
    }
}
