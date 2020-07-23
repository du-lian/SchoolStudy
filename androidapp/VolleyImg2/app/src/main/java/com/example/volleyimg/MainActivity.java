package com.example.volleyimg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final int GET_IMG = 1002;
    private Button btnImageRequest;
    private Button btnImageLoader;
    private ImageView ivImg;
    private RequestQueue requestQueen;
    private String stuutl ="http://jsxy.gdcp.cn/UploadFile/2/2019/3/15/2019315193634465.png";
    private MyImageCache myImageCache;
    private Button btnHttpUrlConnection;
    private Handler handler;
    private  Button btnNewwordImg;
    private NetworkImageView networkImageView;
    private ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnImageRequest = findViewById(R.id.btn_ImageRequest);
        btnImageLoader  = findViewById(R.id.btn_ImageLoader);
        ivImg           = findViewById(R.id.iv_img);
        requestQueen = Volley.newRequestQueue(MainActivity.this);
        myImageCache = new MyImageCache();
        btnHttpUrlConnection = findViewById(R.id.btn_httpUrl);
        btnNewwordImg = findViewById(R.id.btnNetword);
        networkImageView = findViewById(R.id.networdimg);
        imageLoader = new ImageLoader(requestQueen,myImageCache);

        /**
         * 使用NetworkImageView加载图片
         */

        btnNewwordImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkImageView.setDefaultImageResId(R.drawable.default_img);
                networkImageView.setErrorImageResId(R.drawable.error_img);
                networkImageView.setImageUrl(stuutl,imageLoader);
            }
        });


        /**
         * 接受消息
         */

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what ==GET_IMG){
                    Bitmap bitmap = (Bitmap) msg.obj;
                    ivImg.setImageBitmap(bitmap);
                    return true;
                }
                return false;
            }
        });
        /**
         * 使用HttpUrlConnection加载图片
         */
        btnHttpUrlConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(stuutl);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            InputStream inputStream = httpURLConnection.getInputStream();
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            // 将图片放在消息的obj中发出去
                            Message message = Message.obtain();
                            message.what = GET_IMG;
                            message.obj  = bitmap;
                            handler.sendMessage(message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        /**
         * 使用ImageRequest加载图片
         */
        btnImageRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageRequest imageRequest = new ImageRequest(stuutl, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        ivImg.setImageBitmap(bitmap);
                    }
                }, 200, 200, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        ivImg.setImageResource(R.drawable.error_img);
                    }

                });
                requestQueen.add(imageRequest);
            }
        });
        /**
         * 使用ImageLoader加载图片
         */
        btnImageLoader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(ivImg,R.drawable.default_img,R.drawable.error_img);
                imageLoader.get(stuutl,imageListener);
            }
        });


    }
}
