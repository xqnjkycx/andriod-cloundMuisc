/**
 * 网络请求工具类：使用Okhttp库，这里将常用的请求方法封装起来
 * */
package com.example.tools;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpRequestTool {
    public static void get(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}