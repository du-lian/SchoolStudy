package com.example.webview_js;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private WebView my_webview;
    private Button btn_callJS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_webview = findViewById(R.id.my_webview);
        btn_callJS = findViewById(R.id.btn_callJS);

        WebSettings webSettings = my_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        my_webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("安卓的警告框")
                        .setMessage(message)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                result.confirm();
                            }
                        })
                        .setCancelable(false)
                        .create();
                alertDialog.show();
                return true;


            }
        });
//        my_webview.addJavascriptInterface(new AndroidtoJS(),"test");
        my_webview.loadUrl("file:///android_asset/test.html");
        my_webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                if(uri.getScheme().equals("js")){

                    if (uri.getAuthority().equals("webview")){
                        System.out.println("js调用了Android的方法");
                        HashMap<String,String> params = new HashMap<>();
                        Set<String> collection = uri.getQueryParameterNames();
                    }
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

        });




        btn_callJS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* my_webview.post(new Runnable() {
                    @Override
                    public void run() {
                        my_webview.loadUrl("javascript:callJS()");
                    }
                });*/

               my_webview.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                   @Override
                   public void onReceiveValue(String value) {

                   }
               });
            }
        });

    }
}
