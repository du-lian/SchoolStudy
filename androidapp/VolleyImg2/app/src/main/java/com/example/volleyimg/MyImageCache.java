package com.example.volleyimg;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

public class MyImageCache implements ImageLoader.ImageCache {
    private static final String TAG = "MyImageCache";
    private LruCache<String,Bitmap> lruCache;

    public MyImageCache(){
        int maxSize = 10 * 1024 *1024 ;
        lruCache = new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }
    @Override
    public Bitmap getBitmap(String s) {
        Bitmap bitmap = lruCache.get(s);
        if(bitmap == null){
            Log.e(TAG,"getBitmap failed bitmap == null");
        }else{
            Log.e(TAG,"getBitmap failed bitmap != null");
        }
        return bitmap;
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        if(bitmap == null){
            Log.e(TAG,"putBitmap failed bitmap == null");
        }else{
            Log.e(TAG,"putBitmap failed bitmap != null");
        }

        bitmap = lruCache.put(s, bitmap);
    }
}
