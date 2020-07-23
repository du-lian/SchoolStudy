package com.example.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
    private WebView my_webView;
    private ProgressBar my_progressBar;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_webView=findViewById(R.id.my_webView);
        my_progressBar = findViewById(R.id.my_progressbar);

//        my_webView.loadUrl("http://www.baidu.com");
//        my_webView.loadUrl("file:///android_asset/test.html");

        my_webView.loadDataWithBaseURL(null,"<html>\n" + "<body>\n" + "i'm love<br>\n" + "<img src=\"file:///android_asset/a1.jpg\" width=\"50dp\" height=\"50dp\">\n" + "</body>\n" + "</html>","text/html" , "utf-8", null);

        my_webView.addJavascriptInterface(this,"android");
        my_webView.setWebChromeClient(webChromeClient);
        my_webView.setWebViewClient(webViewClient);

        WebSettings webSettings = my_webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不用缓存，只从网络获取数据

//        支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);






    }

    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient = new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {
            //网页加载完成
            my_progressBar.setVisibility(view.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //网页开始加载
            my_progressBar.setVisibility(view.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }


    };

    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private WebChromeClient webChromeClient = new WebChromeClient(){
        //不支持js得alert弹窗，需要自己监听然后通过dialog弹窗
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            AlertDialog.Builder localBuiler = new AlertDialog.Builder(my_webView.getContext());
            localBuiler.setMessage(message).setPositiveButton("确认",null);
            localBuiler.setCancelable(false);
            localBuiler.create().show();

            result.confirm();
            return true;
        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.i("ansen","网页标题"+title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            my_progressBar.setProgress(newProgress);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("ansen","是否有上一个页面："+my_webView.canGoBack());
        if (my_webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK){
            my_webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    public void getClient(String str){
        Log.i("ansen","html调用客户端"+str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //释放资源
        my_webView.destroy();
        my_webView=null;
    }
}
