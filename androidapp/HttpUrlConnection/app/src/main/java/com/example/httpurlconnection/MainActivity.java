package com.example.httpurlconnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.UrlQuerySanitizer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity" ;
    private static final int GET_PHONE = 1001;
    private static final int GET_IMG =1002;
    private Button btnConnectionGet;
    private Button btnConnectionPost;
    private EditText etPhone;
    private TextView tvInfo;
    private Handler handler;
    private ImageView ivImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnectionGet=findViewById(R.id.btn_connection_get);
        btnConnectionPost = findViewById(R.id.btn_connection_post);
        etPhone = findViewById(R.id.et_phone);
        tvInfo  = findViewById(R.id.tv_info);
        ivImg = findViewById(R.id.iv_img);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what == GET_PHONE){
                    String info = (String) msg.obj;
                    tvInfo.setText(info);
                    return true;
                }else if(msg.what ==GET_IMG){
                    Bitmap bitmap = (Bitmap) msg.obj;
                    ivImg.setImageBitmap(bitmap);
                    return true;
                }
                return false;
            }
        });

        btnConnectionGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String phone = etPhone.getText().toString();
                        useHttpConnectionGet(phone);
                    }
                }).start();
            }
        });

        btnConnectionPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String phone = etPhone.getText().toString();
                        useHttpConnectionPost(phone);
                    }
                }).start();

            }
        });
    }

    private void useHttpConnectionPost(String phone) {
        //获取网络地址
        String phoneBaseUrl="http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo";
        String strurl = phoneBaseUrl.concat("?mobileCode=").concat(phone).concat("&userID=");
        try {
            URL mUrl = new URL(strurl);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.setRequestMethod("POST");
            mHttpURLConnection.setDoInput(true);
            mHttpURLConnection.setDoOutput(true);

            String body = "mobileCode="+phone+"&userID=";
            OutputStream mOutputStream = mHttpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(mOutputStream));
            bufferedWriter.write(body);
            bufferedWriter.flush();
            bufferedWriter.close();


            //获取网页信息
            InputStream mInputStream = mHttpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mInputStream));
            String line = bufferedReader.readLine();
            StringBuffer stringBuffer = new StringBuffer();
            while(line!= null){
                stringBuffer.append(line);
                line = bufferedReader.readLine();
            }

            Log.e(TAG,"stringBuffer:"+stringBuffer);

            Message message = Message.obtain();
            message.what = GET_PHONE;
            message.obj = stringBuffer.toString();
            handler.sendMessage(message);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void useHttpConnectionGet(String phone) {
        //获取网络地址
        String phoneBaseUrl="http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo";
        String strurl = phoneBaseUrl.concat("?mobileCode=").concat(phone).concat("&userID=");
//      String strurl ="https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&hs=2&pn=3&spn=0&di=146679046140&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=4122685442%2C159580239&os=44661619%2C529605758&simid=3478508136%2C240599790&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=girl&bdtype=0&oriquery=%E7%BE%8E%E5%A5%B3%E5%9B%BE%E7%89%87&objurl=http%3A%2F%2Fgss0.baidu.com%2F-vo3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2F8435e5dde71190ef3bee9ce4cc1b9d16fdfa60f7.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fzit1w5_z%26e3Bkwt17_z%26e3Bv54AzdH3Fq7jfpt5gAzdH3Fccbl8lnbl8a0laddb9_z%26e3Bip4s&gsm=0&islist=&querylist=";
        try {
            URL mUrl = new URL(strurl);
            HttpURLConnection mHttpURLConnection = (HttpURLConnection) mUrl.openConnection();
            mHttpURLConnection.connect();
            int code = mHttpURLConnection.getResponseCode();
            Log.e(TAG,"code="+code);

            //获取图片
            Bitmap bitmap = BitmapFactory.decodeStream(mHttpURLConnection.getInputStream());
            Message message = Message.obtain();
            message.what = GET_IMG;
            message.obj = bitmap;
            handler.sendMessage(message);

            //获取网页信息
            /*InputStream mInputStream = mHttpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mInputStream));
            String line = bufferedReader.readLine();
            StringBuffer stringBuffer = new StringBuffer();
            while(line!= null){
                stringBuffer.append(line);
                line = bufferedReader.readLine();
            }

            Log.e(TAG,"stringBuffer:"+stringBuffer);

            Message message = Message.obtain();
            message.what = GET_PHONE;
            message.obj = stringBuffer.toString();
            handler.sendMessage(message);*/
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
