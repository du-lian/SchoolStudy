package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

class TimeChangeReceiver extends BroadcastReceiver {
    private TextView tv_time;
    private IOnUpDateListener listener;


    public TimeChangeReceiver(IOnUpDateListener listener){
        this.listener = listener;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("TimeChangeReceiver", "onReceive" + intent.getAction());

        String action = intent.getAction();
        listener.updateTime();

        int state = NetUtil.getNetState(context);
        switch (state) {
            case NetUtil.NET_MOBI:
                Toast.makeText(context, "移动网络可用", Toast.LENGTH_SHORT).show();
                break;
            case NetUtil.NET_WIFI:
                Toast.makeText(context, "WIFI网络可用", Toast.LENGTH_SHORT).show();
                break;
            case NetUtil.NET_NONE:
                Toast.makeText(context, "网络不可用", Toast.LENGTH_SHORT).show();
                break;
        }




    }




}
