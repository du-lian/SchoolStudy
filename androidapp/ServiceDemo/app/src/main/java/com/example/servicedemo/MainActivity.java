package com.example.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import Service.MyService;

public class MainActivity extends AppCompatActivity {
    private Button btnStart;
    private Button btnDrop;
    private Button btnBinder;
    private Button btnUnBinder;
    private ServiceConnection conn;
    private Binder.myBinder myBinder;
    private Button btnGetTime;
    private Button btnReverceWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btnStartOnclickList();//开启Service服务

        btnDropOnclickList();//关闭Service服务

        btnBinderOnclickList(); //绑定binder服务

        btnUnBinderOnclickList();  //停止绑定binder服务

        btnGetTimeOnclickList(); //获取当前时间

        btnReverceWordsOnclickList();  //反转单词


    }
    //反转单词
    private void btnReverceWordsOnclickList() {
        StringBuffer sb = myBinder.reverseWords();
        Toast.makeText(MainActivity.this,"反转单词："+sb,Toast.LENGTH_SHORT).show();
    }

    //获取当前时间
    private void btnGetTimeOnclickList() {
        String time = myBinder.getTime();
        Toast.makeText(MainActivity.this,"时间："+time,Toast.LENGTH_SHORT).show();
    }

    //停止绑定binder服务
    private void btnUnBinderOnclickList() {
        btnUnBinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });
    }

    //绑定binder服务
    private void btnBinderOnclickList() {
        btnBinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyService.class);
                bindService(intent,conn,BIND_AUTO_CREATE);
            }
        });
    }

    //关闭Service服务
    private void btnDropOnclickList() {
        btnDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });
    }

    //开启Service服务
    private void btnStartOnclickList() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyService.class);
                startService(intent);
            }
        });
    }

    private void initView() {
        btnStart = findViewById(R.id.btn_start_service);
        btnDrop  = findViewById(R.id.btn_drop_service);
        btnBinder = findViewById(R.id.btn_binder);
        btnUnBinder = findViewById(R.id.btn_unbinder);
        btnGetTime  = findViewById(R.id.btn_gettime);
        btnReverceWords = findViewById(R.id.btn_reverse_word);

        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("ServiceConnection","onServiceConnected");

                myBinder = (Binder.myBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e("ServiceConnection","onServiceDisconnected");
            }
        };
    }
}
