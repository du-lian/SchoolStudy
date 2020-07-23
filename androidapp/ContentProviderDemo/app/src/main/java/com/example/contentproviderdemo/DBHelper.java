package com.example.contentproviderdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    /*private static final String DATABASE_NAME ="stu.db";
    private static final String STUDENT_TABLE_NAME ="student";
    private static final String SCORE_TABLE_NAME ="score";
    private static final int DATABASE_VERSION = 1;*/
    public DBHelper(Context context1, String dataname, Context factory, int i) {
        super(context1, dataname, null, i);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建两个表格：学生表和成绩表
        String sql1 ="CREATE TABLE IF NOT EXISTS student (stuno varchar(20),name varchar(20) ,age int)";
        db.execSQL(sql1);
        String sql2 ="CREATE TABLE IF NOT EXISTS score (stuno varchar(20),english int ,android int )";

        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
