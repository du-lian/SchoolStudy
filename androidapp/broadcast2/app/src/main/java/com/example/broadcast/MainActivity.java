package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements IOnUpDateListener {
    private IntentFilter intentFilter;
    private IntentFilter intentFilter1;

    private TimeChangeReceiver timeChangeReceiver;
    private TextView tv_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        tv_time=findViewById(R.id.tv_time);
        tv_time.setText(simpleDateFormat.format(date));

        timeChangeReceiver = new TimeChangeReceiver(MainActivity.this);
        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);  //每分钟的变化
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED); //设置了系统时区
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);//设置了系统时间

        registerReceiver(timeChangeReceiver,intentFilter);
        updateTime();


        intentFilter1 = new IntentFilter();
        intentFilter1.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(timeChangeReceiver,intentFilter1);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(timeChangeReceiver);
    }

    @Override
    public void updateTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);

        StringBuffer buffer = new StringBuffer();
        if(hour <10 ){
            buffer.append("0");
        }
        buffer.append(hour);
        buffer.append(":");

        if (min < 10){
            buffer.append("0");
        }
        buffer.append(min);

        tv_time.setText(hour +":" +min);

    }
}
