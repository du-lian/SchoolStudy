package com.example.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MyProvider extends ContentProvider {

    DBHelper mDbHelper = null;

    public static final String AUTOHORITY ="cn.gdcp.student";
    //设置ContentProvider的唯一标识

    public static final int Student_Code =1;
    public static final int Score_Code = 2;

    // UriMatchar类使用：在ContentProvider 中注册URI
    private static final UriMatcher mMatcher;
    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //初始化
        mMatcher.addURI(AUTOHORITY,"student",Student_Code);
        mMatcher.addURI(AUTOHORITY,"score",Score_Code);
    }
    @Override
    public boolean onCreate() {
        mDbHelper = new DBHelper(getContext(),"stu.db",null,1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        switch (mMatcher.match(uri)){
            case 1:
                Cursor cursor = database.query("student",projection,selection,selectionArgs,null,null,sortOrder);
                return cursor;
            case  2:
                break;
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
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        switch (mMatcher.match(uri)){
            case 1:
                database.insert("student",null,values);
                break;
            case  2:
                break;
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        switch (mMatcher.match(uri)){
            case 1:
                int rows = database.delete("student",selection,selectionArgs);
                database.close();
                return rows;

            case  2:
                break;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        switch (mMatcher.match(uri)){
            case 1:
                int rows = database.update("student",values,selection,selectionArgs);
                database.close();
                return rows;
            case  2:
                break;
        }
        return 0;
    }
}
