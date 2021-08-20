package com.example.mycloudmusic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gsonClass.personalizedClass;
import com.example.tools.HttpRequestTool;
import com.example.tools.LogTool;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import com.example.gsonClass.loginStatusClass;

import org.jetbrains.annotations.NotNull;

public class PhoneLogin extends BaseActivity {
    public static final int UPDATE_TEXT = 1;
    private EditText phoneNumberEditText;
    private EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        //获取导航栏实例
        Toolbar phoneLoginToolbar = (Toolbar) findViewById(R.id.phone_login_toolbar);
        //获取登录界面上面的两个输入框实例
        phoneNumberEditText = (EditText) findViewById(R.id.phonenumber_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        //给导航栏左侧加上一个返回箭头按钮，用来返回前一页面
        setSupportActionBar(phoneLoginToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //获取登录按钮并实现登录逻辑
        Button phoneLoginBtn = (Button) findViewById(R.id.phone_login_btn);
        phoneLogin(phoneLoginBtn,phoneNumberEditText,passwordEditText);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(PhoneLogin.this,LoginActivity.class);
                startActivity(intent);
            default:
        }
        return true;
    }
    //点击登录按钮实现登录逻辑
    private void phoneLogin(Button btn,EditText phoneNumET,EditText passwordET){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String pn = phoneNumET.getText().toString();
               String ps = passwordET.getText().toString();
               String encodePs = Uri.encode(ps);
               String url ="http://10.0.2.2:3000/login/cellphone?phone="+pn+"&password="+encodePs;
                HttpRequestTool.get(url,new okhttp3.Callback(){
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        Gson gson = new Gson();
                        loginStatusClass loginRes = gson.fromJson(responseData,loginStatusClass.class);
                        int code = loginRes.getCode();
                        if (code == 200){
                            finish();
                            long userId = loginRes.getAccount().getId();
                            String nickName = loginRes.getProfile().getNickname();
                            String avatarUrl = loginRes.getProfile().getAvatarUrl();
                            String backgroundUrl = loginRes.getProfile().getBackgroundUrl();
                            String cookie =  Uri.encode(loginRes.getCookie());
                            Intent intent = new Intent(PhoneLogin.this,MainActivity.class);
                            intent.putExtra("nickName",nickName);
                            intent.putExtra("avatarUrl",avatarUrl);
                            intent.putExtra("backgroundUrl",backgroundUrl);
                            intent.putExtra("userId",userId);
                            intent.putExtra("cookie",cookie);
                            startActivity(intent);
                     } else {
                            Message message = new Message();
                            message.what = UPDATE_TEXT;
                            handler.sendMessage(message); //将Message对象给发送出去
                       }
                    }
                    @Override
                    public void  onFailure(Call call,IOException e){
                    }
                });
            }
        });
    }
    //异步任务处理机制
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    //在这里就可以进行UI操作了
                    Toast.makeText(PhoneLogin.this,"输入的手机号或密码有误",Toast.LENGTH_SHORT).show();
                    passwordEditText.setText(null);
                    phoneNumberEditText.setText(null);
            }
        }
    };
}