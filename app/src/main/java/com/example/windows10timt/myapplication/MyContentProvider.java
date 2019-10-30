package com.example.windows10timt.myapplication;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MyContentProvider extends ContentProvider {
    static final String AUTHORITY="com.example.windows10timt.myapplication";
    static  final String CONTENT_PATH="bookdata";
    static  final  String URL="content://"+ AUTHORITY+"/"+ CONTENT_PATH;
    static  final Uri CONTENT_URI=Uri.parse(URL);
    static  final  String TABLE_NAME="Books";
    private SQLiteDatabase db;
    private  static HashMap<String,String> BOOKG_PROJECTION_MAP;
    static final int ALLITIEMS=1;
    static  final int ONEITEM=2;
    static UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, CONTENT_PATH, ALLITIEMS);
        uriMatcher.addURI(AUTHORITY, CONTENT_PATH+"/#", ONEITEM);
    }
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri))
        {
            case ALLITIEMS:
                count = db.delete(TABLE_NAME, selection, selectionArgs);
                break;
            case ONEITEM:
                String id =  uri.getPathSegments().get(1);
                count = db.delete(TABLE_NAME, "id_book" + "=" + id +
                (!TextUtils.isEmpty(selection)?"AND ("+ selection+')':""),selectionArgs);break;
            default:
                try {
                    throw  new IllegalAccessException("Unknown URI"+ uri);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return  count;
        // Implement this to handle requests to delete one or more rows.

    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        long number_row = db.insert(TABLE_NAME, "", values);
        if(number_row>0)
        {
            Uri uri1 = ContentUris.withAppendedId(CONTENT_URI, number_row);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;

        }
        throw new SQLException("Failed to add a record in to" + uri);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        Context context= getContext();
        DBHelper dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
        if (db==null)
            return false;

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(TABLE_NAME);
        switch (uriMatcher.match(uri)){
            case ALLITIEMS:
                sqLiteQueryBuilder.setProjectionMap(BOOKG_PROJECTION_MAP);break;
            case  ONEITEM:
                sqLiteQueryBuilder.appendWhere("id_book"+""+uri.getPathSegments().get(1));break;
        }
        if (sortOrder == null || sortOrder == "")
        {
            sortOrder = "title";
        }
        Cursor cursor = sqLiteQueryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
        // TODO: Implement this to handle query requests from clients.

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri))
        {
            case ALLITIEMS:
                count = db.update(TABLE_NAME,values, selection, selectionArgs);
                break;
            case ONEITEM:
                count = db.update(TABLE_NAME,values, "id_book" + "=" + uri.getPathSegments().get(1) +
                        (!TextUtils.isEmpty(selection)?"AND ("+ selection+')':""),selectionArgs);break;
            default:
                try {
                    throw  new IllegalAccessException("Unknown URI"+ uri);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return  count;

        // TODO: Implement this to handle requests to update one or more rows.

    }
}
