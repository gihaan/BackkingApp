package com.example.gihan.backkingapp.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Gihan on 9/29/2017.
 */

public class RecipsProvider extends ContentProvider {


    static final String PROVIDER_NAME = "com.example.gihan.backkingapp.ContentProvider.RecipsProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/cpcontacts";

    public static final Uri CONTENT_URI = Uri.parse(URL);

    static final String id = "id";
    static final String name = "name";
    static final int uriCode = 1;

    private static HashMap<String, String> values;

    static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "cpcontacts", uriCode);
    }

    private SQLiteDatabase sqlDB;

    static final String DATABASE_NAME = "recipapp";

    static final String TABLE_STEP = "gerdiant";

    public static final String RECIP_NAME = "recipname";
    public static final String Quality = "quality";
    public static final String MEAURE= "meaure";
    public static final String NAME = "name";


    static final int DATABASE_VERSION = 1;
    public static final String CREATE_STEP = "CREATE TABLE " + TABLE_STEP + "( id INTEGER PRIMARY KEY AUTOINCREMENT , "+RECIP_NAME+" TEXT , " + Quality + " TEXT," +MEAURE + " TEXT," + NAME + " TEXT );";


    @Override
    public boolean onCreate() {


        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        sqlDB = dbHelper.getWritableDatabase();

        if (sqlDB != null) {
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_STEP);

        switch (uriMatcher.match(uri)) {

            case uriCode:
                queryBuilder.setProjectionMap(values);
                break;
            default:
                throw new IllegalArgumentException("unknown uri " + uri);

        }
        Cursor cursor = queryBuilder.query(sqlDB, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {


        switch (uriMatcher.match(uri)) {

            case uriCode:
                return "vnd.android.cursor.dir/cpcontact";
            default:
                throw new IllegalArgumentException("un supporrt uri  " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        long rowID = sqlDB.insert(TABLE_STEP, null, values);

        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        } else {
            Toast.makeText(getContext(), "insert row failed", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqlDB) {

            sqlDB.execSQL(CREATE_STEP);


        }

        @Override
        public void onUpgrade(SQLiteDatabase sqlDB, int oldVersion, int newVersion) {

            sqlDB.execSQL("DROP TABLE IF EXISTS" + TABLE_STEP);

            onCreate(sqlDB);

        }
    }
}
