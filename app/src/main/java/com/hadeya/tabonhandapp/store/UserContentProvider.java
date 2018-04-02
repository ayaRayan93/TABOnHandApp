package com.hadeya.tabonhandapp.store;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by AyaAli on 15/03/2018.
 */

public class UserContentProvider extends ContentProvider {
    // database
    private DataBaseHelper database;//=CustomerContentProvider.database;
    private Context context;
    // used for the UriMacher
    public UserContentProvider() {

    }
    public UserContentProvider(DataBaseHelper database) {
        this.database=database;
    }
    public UserContentProvider(Context context) {
        this.context = context;
        database = new DataBaseHelper(context);
    }

    private static final int TODOS = 12;
    private static final int TODO_ID = 22;

    private static final String AUTHORITY = "com.example.ayaali.customers.store";

    private static final String BASE_PATH = "user";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);
    public static final Uri CONTENT_URI_Add = Uri.parse("content://" + AUTHORITY
            + "/add");
    public static final Uri CONTENT_URI_logout = Uri.parse("content://" + AUTHORITY
            + "/logout");

    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, TODOS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", TODO_ID);
    }

    @Override
    public boolean onCreate() {
        database = new DataBaseHelper(context);
        //database = CustomerContentProvider.database;
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Uisng SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check if the caller has requested a column which does not exists
        // checkColumns(projection);

        // Set the table
        queryBuilder.setTables(UserTable.UserTable);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case TODOS:

                break;
            case TODO_ID:
                break;
            default:
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
            id = sqlDB.insert(UserTable.UserTable, null, values);
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
