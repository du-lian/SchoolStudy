package com.example.demob;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import java.util.List;

import static com.example.demob.R.id.iv_del;

public class MainActivity extends AppCompatActivity implements IOnDataChangeListener {
    private ListView lvStu;
    private Button   btnAdd;
    private ArrayList<Student> stuList = new ArrayList<Student>();
    private StudentAdapter adapter;
    private StuViewHold stuViewHold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findView();


        adapter = new StudentAdapter(MainActivity.this, stuList, MainActivity.this);
        lvStu = (ListView) findViewById(R.id.lv_stu);
        lvStu.setAdapter(adapter);
        lvStu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = stuList.get(position);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, EditActivity.class);
                intent.putExtra("STUNO", student.getStuid());
                intent.putExtra("NAME", student.getStuname());
                intent.putExtra("AGE", student.getStuage());
                intent.putExtra("SEX",student.getStusex());
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
        Uri uri = Uri.parse("content://cn.gdcp.myprovide/student");
        stuList.clear();
        Cursor cursor = getContentResolver().query(uri,null, null, null, null);

        if(cursor != null && cursor.getCount() > 0){
            StringBuffer buffer = new StringBuffer();
            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                String stuno = cursor.getString(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                int sex = cursor.getInt(3);
                Student  student = new Student(stuno, name, age, sex ,R.drawable.del);
                stuList.add(student);
            }


        }else{
            Toast.makeText(MainActivity.this,"行数为0",Toast.LENGTH_SHORT).show();
        }

        adapter.notifyDataSetChanged();
        cursor.close();



    }



    // 初始化
    private void findView() {
        lvStu= findViewById(R.id.lv_stu);
        btnAdd = findViewById(R.id.btn_add_stu);
    }

    //删除
    @Override
    public void del(Student student) {
        Uri uri = Uri.parse("content://cn.gdcp.myprovide/student");
        String where = "stuno = '"+student.getStuid()+"'";
        String[] argArray = {student.getStuid()};
        getContentResolver().delete(uri,where,argArray);

    }

    //返回结束码
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == 2001){
            String stuno = data.getStringExtra("STUNO");
            String name = data.getStringExtra("NAME");
            int age = data.getIntExtra("AGE", 0);
            int sex = data.getIntExtra("SEX",1);
            Student student =  new Student(stuno, name, age,sex,R.drawable.del);
            addStudentToDB(student);
        }else if(resultCode == 3001){
            String stuno = data.getStringExtra("STUNO");
            String name = data.getStringExtra("NAME");
            int age = data.getIntExtra("AGE", 0);
            int sex = data.getIntExtra("SEX",1);
            Student student =  new Student(stuno, name, age,sex,R.drawable.del);
            updateStudentToDB(student);
        }
    }
    //更新学生表
    private void updateStudentToDB(Student student) {
        Uri uri = Uri.parse("content://cn.gdcp.myprovide/student");
        ContentValues values = new ContentValues();

        values.put("stuname",student.getStuname());
        values.put("stuage",student.getStuage());
        values.put("stusex",student.getStusex());
        String where ="stuno = '"+ student.getStuid() +"'";
        getContentResolver().update(uri,values, where, null);
    }

    private void addStudentToDB(Student student) {
        Uri uri = Uri.parse("content://cn.gdcp.myprovide/student");
        ContentValues values = new ContentValues();
        values.put("stuid",student.getStuid());
        values.put("stuname",student.getStuname());
        values.put("stuage",student.getStuage());
        values.put("stusex",student.getStusex());
        values.put("stuimg",R.id.iv_del);
        getContentResolver().insert(uri,values);
    }
}
