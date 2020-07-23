package com.example.http_get_post;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity" ;
    private static final int GET_RESULT =1001 ;
    private Button btn_get;
    private Button btn_post;
    private Handler handler;
    private EditText et_keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_get = findViewById(R.id.btn_get);
        btn_post = findViewById(R.id.btn_post);

        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        useHttpClientGet("http://www.baidu.com");
                    }
                }).start();
            }
        });

        et_keyword = findViewById(R.id.et_keyword);
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String phone = et_keyword.getText().toString();
                        useHttpPost(phone);
                    }
                }).start();
            }
        });


    }

    private void useHttpPost(String phone){
        String url ="http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo";
        HttpPost httpPost = new HttpPost(url);

        HttpClient httpClient = creatHttpClient();
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("mobileCode",phone));
        nameValuePairList.add(new BasicNameValuePair("userID",""));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            String htmlContent = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            Log.e(TAG,htmlContent);

            Message message = Message.obtain();
            message.what = GET_RESULT;
            message.obj = htmlContent;
            handler.sendMessage(message);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private HttpClient creatHttpClient(){
        HttpParams mDefaultHttpParams = new BasicHttpParams();
        //设置连接超时
        HttpConnectionParams.setConnectionTimeout(mDefaultHttpParams,15000);
        //设置请求超时
        HttpConnectionParams.setSoTimeout(mDefaultHttpParams,15000);
        HttpConnectionParams.setTcpNoDelay(mDefaultHttpParams,true);
        HttpProtocolParams.setVersion(mDefaultHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(mDefaultHttpParams, HTTP.UTF_8);

        //持续握手
        HttpProtocolParams.setUseExpectContinue(mDefaultHttpParams,true);
        HttpClient mHttpClient = new DefaultHttpClient(mDefaultHttpParams);
        return mHttpClient;

    }

    private void useHttpClientGet(String url){
        HttpGet mHttpGet = new HttpGet(url);
        mHttpGet.addHeader("Connection","Keep-Alive");
        HttpClient mHttpClient = creatHttpClient();
        HttpResponse mHttpResponse = null;
        try {
            mHttpResponse = mHttpClient.execute(mHttpGet);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            int code = mHttpResponse.getStatusLine().getStatusCode();
            if(null != mHttpEntity){
                InputStream mInputStream = mHttpEntity.getContent();
                String respose = converStreamToString(mInputStream);
                Log.e("useHttpClientGet","请求状态码:"+code+"\n请求结构:\n"+respose);
                mInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String converStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while((line = reader.readLine())!=null){
            sb.append(line+"\n");
        }
        String respose = sb.toString();
        return respose;
    }
}
