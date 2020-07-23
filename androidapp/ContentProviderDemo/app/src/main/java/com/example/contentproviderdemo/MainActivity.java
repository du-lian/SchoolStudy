package com.example.contentproviderdemo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnInsert;
    private Button btnQuery;
    private Button btnDel;
    private Button btnUpdate;
    private TextView txtResultStu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置URI
                Uri uri = Uri.parse("content://cn.gdcp.student/student");
                //插入表中数据
                ContentValues values = new ContentValues();
                values.put("stuno","17701");
                values.put("name","赵子贤");
                values.put("age",20);
                //获取ContentResolver
                ContentResolver resolver = getContentResolver();
                //通过ContentResolver 根据uri 向ContentProvider中插入数据
                resolver.insert(uri,values);

            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置URI
                Uri uri = Uri.parse("content://cn.gdcp.student/student");
                //查询数据
                ContentResolver resolver = getContentResolver();
                Cursor cursor = resolver.query(uri,null,null,null,null);
                if(cursor!=null && cursor.getCount() >0){
                    StringBuffer buffer = new StringBuffer();
                    for(cursor.moveToFirst() ; !cursor.isAfterLast() ; cursor.moveToNext()){
                        String stuno = cursor.getString(0);
                        String stuname = cursor.getString(1);
                        int    stuage  = cursor.getInt(2);
                        buffer.append(stuno).append("  ").append(stuname).append("  ").append(stuage).append("\n");
                    }
                    txtResultStu.setText(buffer);
                }else{
                    txtResultStu.setText("行数为0");
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置URI
                Uri uri = Uri.parse("content://cn.gdcp.student/student");
                //删除数据
               String where = "name = '赵子贤'";
               int rows = getContentResolver().delete(uri,where,null);
                Toast.makeText(MainActivity.this,"删除了"+rows+"行",Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置URI
                Uri uri = Uri.parse("content://cn.gdcp.student/student");
                //更新数据
                ContentValues values = new ContentValues();
                values.put("name","陈智秋");
                String where = "name = '赵子贤'";
                int row = getContentResolver().update(uri,values,where,null);
                Toast.makeText(MainActivity.this,"更新了"+row+"行",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        btnInsert = findViewById(R.id.btn_insert_stu);
        btnQuery  = findViewById(R.id.btn_query_stu);
        txtResultStu = findViewById(R.id.txt_result_stu);
        btnDel     = findViewById(R.id.btn_delete_stu);
        btnUpdate  = findViewById(R.id.btn_update_stu);
    }
}
