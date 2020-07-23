package com.example.webview_js;

import android.webkit.JavascriptInterface;

public class AndroidtoJS extends Object {
    @JavascriptInterface
    public void hello(String msg){
        System.out.println("js调用Android的hello方法");
    }
}
