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
 * Created by AyaAli on 07/03/2018.
 */

public class CustomerContentProvider extends ContentProvider {
    // database
    DataBaseHelper database;
    private Context context;
    // used for the UriMacher
    public CustomerContentProvider(DataBaseHelper database) {
          this.database=database;
    }

    public CustomerContentProvider() {

    }
    public CustomerContentProvider(Context context) {
        this.context = context;
        database = new DataBaseHelper(context);
    }

    private static final int TODOS = 10;
    private static final int TODO_ID = 20;

    private static final String AUTHORITY = "com.example.ayaali.customers.store";

    private static final String BASE_PATH = "customers";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);
    public static final Uri CONTENT_URI_add = Uri.parse("content://" + AUTHORITY
            + "/add");
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/customers";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/customer";


    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static
    {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, TODOS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", TODO_ID);
    }

    @Override
    public boolean onCreate() {
        database = new DataBaseHelper(context);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Uisng SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check if the caller has requested a column which does not exists
        // checkColumns(projection);

        // Set the table
        queryBuilder.setTables(CustomerTable.CustomerTable);
       // SQLiteDatabase sqlDB=database.getReadableDatabase();
       // Cursor cursor;
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case TODOS:
             /*   if (TextUtils.isEmpty(sortOrder))
                    sortOrder = "_ID ASC";
                cursor=sqlDB.query(CustomerTable.CustomerTable,projection,selection,selectionArgs,null,null,sortOrder);*/
                break;
            case TODO_ID:
                /*selection = CustomerTable.ID + "=?";
                selectionArgs=new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor=sqlDB.query(CustomerTable.CustomerTable,projection,selection,selectionArgs,null,null,sortOrder);*/
                break;
            default:
               // cursor=null;
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

          SQLiteDatabase db = database.getWritableDatabase();
        // Cursor cursor = queryBuilder.query(db, projection, selection,
        //       selectionArgs, null, null, sortOrder);
         Cursor cursor = db.rawQuery(selection, null);
        // make sure that potential listeners are getting notified
//        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        try {
            id = sqlDB.insert(CustomerTable.CustomerTable, null, values);
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
        SQLiteDatabase db = database.getWritableDatabase();

        int updateCount = db.update(CustomerTable.CustomerTable, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return updateCount;
    }
}
