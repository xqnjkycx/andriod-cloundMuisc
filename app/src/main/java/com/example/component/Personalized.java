/**
 * 歌单推荐组件
 * */
package com.example.component;

import android.util.Log;

import com.example.tools.HttpRequestTool;
import com.example.gsonClass.personalizedClass;
import com.example.tools.LogTool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class Personalized extends componentHandle {
    @Override
    public void dataRequest(){
        HttpRequestTool.get("http://10.0.2.2:3000/personalized?limit=2",new okhttp3.Callback(){
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                personalizedClass personalized = gson.fromJson(responseData,personalizedClass.class);
                List<personalizedClass.ResultDTO> resList = personalized.getResult();
                for(personalizedClass.ResultDTO obj : resList){
                    String name = obj.getName();
                   // LogTool.d("推荐出来的歌单的歌单名","response"+name);
                }
            }
            @Override
            public void  onFailure(Call call,IOException e){
            }
        });
    };
    @Override
    public void init(){
        this.dataRequest();
    }
}
