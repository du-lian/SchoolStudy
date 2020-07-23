package com.example.demoa;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCreate;
    private Button btnStudent;
    private Button btnGrade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();


        initOnclickView();
    }
    //点击事件
    private void initOnclickView() {
            btnCreate.setOnClickListener(this);
            btnStudent.setOnClickListener(this);
            btnGrade.setOnClickListener(this);

    }

    //初始化
    private void findView() {
        btnCreate = findViewById(R.id.btn_create_database);
        btnStudent = findViewById(R.id.btn_table_stu);
        btnGrade  = findViewById(R.id.btn_table_grade);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create_database:
                MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(MainActivity.this,"stu.db",null,1);
                SQLiteDatabase sqLiteOpenHelper = mySQLiteOpenHelper.getWritableDatabase();
                break;
            case R.id.btn_table_stu:

                break;
            case R.id.btn_table_grade:
                break;

                default:
                    break;
        }
    }
}
