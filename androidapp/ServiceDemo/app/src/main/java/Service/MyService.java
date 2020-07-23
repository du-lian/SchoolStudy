package Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import Binder.myBinder;

public class MyService extends Service {

    private boolean isService = true;
    private myBinder myBinder = new myBinder();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyService","onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isService){
                    Log.e("MyService","onStartCommand()");

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();



        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyService","onDestroy()");
    }
}
