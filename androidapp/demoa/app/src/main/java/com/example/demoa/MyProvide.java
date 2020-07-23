package com.example.demoa;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MyProvide extends ContentProvider {
    private Context mContext;
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    public static final String AUTOHORITY = "cn.gdcp.myprovide";
    public static final int Student_Code = 1;
    public static final int Grade_Code = 2;
    private static UriMatcher mMatcher;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(AUTOHORITY,"student",Student_Code);
        mMatcher.addURI(AUTOHORITY,"grade",Grade_Code);
    }
    @Override
    public boolean onCreate() {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(getContext(),"stu.db",null, 1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();
        switch (mMatcher.match(uri)){
            case 1:
                Cursor cursor1 = sqLiteDatabase.query("student", projection, selection,selectionArgs,null, null, sortOrder);
                return cursor1;

            case 2:
                Cursor cursor2 = sqLiteDatabase.query("grade", projection, selection,selectionArgs,null, null, sortOrder);
                return cursor2;

        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        switch (mMatcher.match(uri)){
            case 1:
                sqLiteDatabase.insert("student",null,values);
                break;
            case 2:
                sqLiteDatabase.insert("grade",null,values);
                break;
        }

        sqLiteDatabase.close();
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        switch (mMatcher.match(uri)){
            case 1:

                int rows = sqLiteDatabase.delete("student",selection,selectionArgs);
                sqLiteDatabase.close();
                return rows;
            case 2:

                int row = sqLiteDatabase.delete("grade",selection,selectionArgs);
                sqLiteDatabase.close();
                return row;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        switch (mMatcher.match(uri)){
            case 1:

                int rows = sqLiteDatabase.update("student",values,selection,selectionArgs);
                sqLiteDatabase.close();
                return rows;
            case 2:

                int row = sqLiteDatabase.update("grade",values,selection,selectionArgs);
                sqLiteDatabase.close();
                return row;
        }
        return 0;
    }
}
