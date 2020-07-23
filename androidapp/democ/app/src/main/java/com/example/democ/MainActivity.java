package com.example.democ;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IOnDataChangeListener {
    private ListView lvGrade;
    private Button btnAdd;
    private ArrayList<Grade> graList = new ArrayList<Grade>();
    private GradeAdapter adapter;
    private GradeViewHolder graViewHold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();


        adapter = new GradeAdapter(MainActivity.this, graList, MainActivity.this);
        lvGrade = (ListView) findViewById(R.id.lv_grade);
        lvGrade.setAdapter(adapter);
        lvGrade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Grade grade = graList.get(position);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, EditActivity.class);
                intent.putExtra("STUNAME", grade.getStuname());
                intent.putExtra("ANDROID", grade.getAndroid());
                intent.putExtra("ENGLISH", grade.getEnglish());

                startActivityForResult(intent, 1002);
            }


        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 1001);
            }
        });

        readDateFromDB();

    }
    // 读取数据库内容
    private void readDateFromDB() {
        Uri uri = Uri.parse("content://cn.gdcp.myprovide/grade");
        graList.clear();
        Cursor cursor = getContentResolver().query(uri,null, null, null, null);

        if(cursor != null && cursor.getCount() > 0){
            StringBuffer buffer = new StringBuffer();
            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                String stuno = cursor.getString(0);

                int android = cursor.getInt(1);
                int english = cursor.getInt(2);
                Grade  grade = new Grade(stuno, android, english, R.drawable.del);
                graList.add(grade);
            }


        }else{
            Toast.makeText(MainActivity.this,"行数为0",Toast.LENGTH_SHORT).show();
        }

        adapter.notifyDataSetChanged();
        cursor.close();



    }



    // 初始化
    private void findView() {
        lvGrade= findViewById(R.id.lv_grade);
        btnAdd = findViewById(R.id.btn_add_grade);
    }

    //删除
    @Override
    public void del(Grade grade) {
        Uri uri = Uri.parse("content://cn.gdcp.myprovide/grade");
        String where = "stuname = '"+grade.getStuname()+"'";
        String[] argArray = {grade.getStuname()};
        getContentResolver().delete(uri,where,argArray);

    }

    //返回结束码
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == 2001){
            String stuno = data.getStringExtra("STUNO");

            int android = data.getIntExtra("ANDORID", 60);
            int english = data.getIntExtra("ENGLISH",60);
            Grade grade =  new Grade(stuno, android, english,R.drawable.del);
            addGradeToDB(grade);
        }else if(resultCode == 3001){
            String stuno = data.getStringExtra("STUNO");

            int android = data.getIntExtra("ANDORID", 60);
            int english = data.getIntExtra("ENGLISH",60);
            Grade grade =  new Grade(stuno, android, english,R.drawable.del);

            updateGradeToDB(grade);
        }
    }
    //更新学生表
    private void updateGradeToDB(Grade grade) {
        Uri uri = Uri.parse("content://cn.gdcp.myprovide/grade");
        ContentValues values = new ContentValues();

        values.put("android",grade.getAndroid());
        values.put("englis",grade.getEnglish());
        String where ="stuname = '"+ grade.getStuname() +"'";
        getContentResolver().update(uri,values, where, null);
    }

    private void addGradeToDB(Grade grade) {
        Uri uri = Uri.parse("content://cn.gdcp.myprovide/grade");
        ContentValues values = new ContentValues();
        values.put("stuname",grade.getStuname());
        values.put("andorid",grade.getAndroid());
        values.put("english",grade.getEnglish());

        values.put("stuimg",R.id.iv_del);
        getContentResolver().insert(uri,values);
    }


}
