package com.example.broadcast;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

class NetUtil {

    public static final int NET_NONE = 0;
    public static final int NET_WIFI = 1;
    public static final int NET_MOBI = 2;

    public static int getNetState(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info != null && info.isConnected()){
            if(info.getType() == ConnectivityManager.TYPE_WIFI){
                return NET_WIFI;
            }else if(info.getType() == ConnectivityManager.TYPE_MOBILE){
                return NET_MOBI;
            }
        }else{
            return NET_NONE;
        }

        return NET_NONE;


    }
}
