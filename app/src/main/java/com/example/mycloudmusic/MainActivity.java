package com.example.mycloudmusic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.component.Personalized;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Personalized personalizedSongsBlock = new Personalized();
        personalizedSongsBlock.init();
        //获取Toolbar实例
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //获取抽屉画布实例
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.user);
        }
        /**
         * 这里是初始化左边侧边栏的样式，用户登录成功之后更新相应的头像、名字、背景图片
         * 注意点！！！抽屉布局的头部里面的实例不能直接通过findViewById来获取
         * 必须通过父布局的 getHeeaderView(0) 来获取，一定要记住！！！
         * **/
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0);
        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        String avatarUrl = intent.getStringExtra("avatarUrl");
        String backgroundUrl = intent.getStringExtra("backgroundUrl");
        ImageView navHeaderBg= (ImageView) headerView.findViewById(R.id.nav_header_bg);
        CircleImageView iconHeader = (CircleImageView) headerView.findViewById(R.id.icon_header);
        TextView avatarName = (TextView) headerView.findViewById(R.id.user_name);
        Glide.with(MainActivity.this).load(avatarUrl).into(iconHeader);
        Glide.with(MainActivity.this).load(backgroundUrl).into(navHeaderBg);
        avatarName.setText(nickName);
        //---
    }
    //加载Toolbar的布局
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    //Toolbar上的图标点击事件
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
}