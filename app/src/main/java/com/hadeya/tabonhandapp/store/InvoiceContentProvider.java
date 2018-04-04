package com.hadeya.tabonhandapp.store;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by AyaAli on 16/03/2018.
 */

public class InvoiceContentProvider extends ContentProvider {

    // database
    private DataBaseHelper database;//=CustomerContentProvider.database;
    private Context context;
    // used for the UriMacher
    public InvoiceContentProvider() {

    }
    public InvoiceContentProvider(DataBaseHelper database) {
        this.database=database;
    }
    public InvoiceContentProvider(Context context) {
        this.context = context;
        database = new DataBaseHelper(context);
    }

    public static final int TODOS = 12;
    public static final int TODO_ID = 22;

    private static final String AUTHORITY = "com.example.ayaali.customers.store";

    private static final String BASE_PATH = "invoice";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);
    public static final Uri CONTENT_URI_add = Uri.parse("content://" + AUTHORITY
            + "/add");
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/items";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/item";


    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, TODOS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", TODO_ID);
    }

    @Override
    public boolean onCreate() {
        database = new DataBaseHelper(context);
        return false;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Uisng SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        SQLiteDatabase sqlDB=database.getReadableDatabase();
        Cursor cursor;
        // check if the caller has requested a column which does not exists
        // checkColumns(projection);

        // Set the table
        queryBuilder.setTables(InvoiceTable.InvoiceTable);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case TODOS:
                if (TextUtils.isEmpty(sortOrder))
                    sortOrder = "_ID ASC";
                cursor=sqlDB.query(InvoiceTable.InvoiceTable,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case TODO_ID:
                selection = InvoiceTable.Id + "=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor=sqlDB.query(InvoiceTable.InvoiceTable,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                cursor=null;
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }


        // Cursor cursor = queryBuilder.query(db, projection, selection,
        //       selectionArgs, null, null, sortOrder);
       // Cursor cursor = sqlDB.rawQuery(selection, null);
        // make sure that potential listeners are getting notified
//        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        try {
            id = sqlDB.insert(InvoiceTable.InvoiceTable, null, values);
        }
        catch(Exception e){
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        //  getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
