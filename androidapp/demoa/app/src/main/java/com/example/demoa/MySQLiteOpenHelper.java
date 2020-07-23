package com.example.demoa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLOutput;

public class MySQLiteOpenHelper extends SQLiteOpenHelper{
    //数据库版本号
    private static Integer Version = 1;
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("创建学生和分数表");
        String studentsql = "CREATE TABLE IF NOT EXISTS student(stuid varchar(20), stuname varchar(20), stuage int, stusex int, stuimg int)";
        String gradesql   = "CREATE TABLE IF NOT EXISTS grade(stuid varchar(20), android int, english int)";

        db.execSQL(studentsql);
        db.execSQL(gradesql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    System.out.println("更新数据库版本为："+newVersion);
    }
}
