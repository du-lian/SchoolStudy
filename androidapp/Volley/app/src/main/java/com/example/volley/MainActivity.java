package com.example.volley;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity" ;
    private Button btnStringRequestGET;
    private Button btnStringRequestPOST;
    private Button btnJsonRequest;
    private TextView tvResult;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStringRequestGET = findViewById(R.id.btn_StringRequestGET);
        btnStringRequestPOST = findViewById(R.id.btn_StringRequestPOST);
        btnJsonRequest = findViewById(R.id.btn_JsonRequest);
        tvResult = findViewById(R.id.tv_result);

        btnStringRequestGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useStringRequestGet();
            }
        });

        btnStringRequestPOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useStringRequestPost();
            }
        });

        
        btnJsonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useJsonRequest();
            }
        });
    }

    private void useJsonRequest() {
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url ="http://t.weather.sojson.com/api/weather/city/101280106";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,null,new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e(TAG,jsonObject.toString());
                try {
                    String city = jsonObject.getJSONObject("cityInfo").getString("city");
                    String wendu = jsonObject.getJSONObject("date").getString("wendu");
                    String quality = jsonObject.getJSONObject("date").getString("quality");
                    String shidu   = jsonObject.getJSONObject("date").getString("shidu");

                    tvResult.setText("您所在得城市："+city+",目前的温度为："+wendu+",空气质量："+quality+"，湿度为："+shidu);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,volleyError.getMessage(),volleyError);
            }
        });

        requestQueue.add(jsonObjectRequest);
    }


    private void useStringRequestPost() {

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url ="http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                tvResult.setText(s);

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,volleyError.getMessage(),volleyError);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("mobileCode","13422206521");
                map.put("userID","");
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void useStringRequestGet() {
         requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://www.baidu.com";
        StringRequest stringRequest = new StringRequest(url,  new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                tvResult.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,volleyError.getMessage(),volleyError);
            }
        });

        requestQueue.add(stringRequest);
    }
}
