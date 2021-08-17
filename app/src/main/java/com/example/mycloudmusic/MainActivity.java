package com.example.mycloudmusic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.example.component.Personalized;
import com.example.fragment.indexFragment;
import com.example.fragment.rankFragment;
import com.example.fragment.searchFragment;
import com.example.fragment.userFragment;
import com.google.android.material.navigation.NavigationView;

import org.litepal.LitePal;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    private RelativeLayout tabbarIndexLayout;
    private ImageView tabbarIndexIcon;
    private TextView tabbarIndexText;

    private RelativeLayout tabbarRankLayout;
    private ImageView tabbarRankIcon;
    private TextView tabbarRankText;

    private RelativeLayout tabbarSearchLayout;
    private ImageView tabbarSearchIcon;
    private TextView tabbarSearchText;

    private RelativeLayout tabbarUserLayout;
    private ImageView tabbarUserIcon;
    private TextView tabbarUserText;

    private   Fragment indexFragment;
    private Fragment rankFragment;
    private Fragment searchFragment;
    private Fragment userFragment;

    FragmentManager fragmentManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化本应用的数据库
        LitePal.initialize(this);

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
        //初始化tabbaer
        fragmentManager = getSupportFragmentManager();
        tabbarInitView();
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
    //初始化Tabbar
    public void tabbarInitView(){
        tabbarIndexLayout = (RelativeLayout) findViewById(R.id.tabbar_index_layout);
        tabbarIndexIcon = (ImageView) findViewById(R.id.tabbar_index_icon);
        tabbarIndexText = (TextView) findViewById(R.id.tabbar_index_text);
        tabbarRankLayout = (RelativeLayout) findViewById(R.id.tabbar_rank_layout);
        tabbarRankIcon = (ImageView) findViewById(R.id.tabbar_rank_icon);
        tabbarRankText = (TextView) findViewById(R.id.tabbar_rank_text);
        tabbarSearchLayout = (RelativeLayout) findViewById(R.id.tabbar_search_layout);
        tabbarSearchIcon = (ImageView) findViewById(R.id.tabbar_search_icon);
        tabbarSearchText = (TextView) findViewById(R.id.tabbar_search_text);
        tabbarUserLayout = (RelativeLayout) findViewById(R.id.tabbar_user_layout);
        tabbarUserIcon = (ImageView) findViewById(R.id.tabbar_user_icon);
        tabbarUserText = (TextView) findViewById(R.id.tabbar_user_text);

        tabbarIndexLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChioceItem(0);
            }
        });
        tabbarRankLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChioceItem(1);
            }
        });
        tabbarSearchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChioceItem(2);
            }
        });
        tabbarUserLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChioceItem(3);
            }
        });
        //默认首页被选中
        setChioceItem(0);
    }
    //定义一个重置所有选项的方法，清除所有样式，排他思想
    public void clearChioce()
    {
        tabbarIndexIcon.setImageResource(R.drawable.tabbar_index_unselector);
        tabbarIndexLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tabbarIndexText.setTextColor(Color.parseColor("#707070"));
        tabbarRankIcon.setImageResource(R.drawable.tabbar_rank_unselector);
        tabbarRankLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tabbarRankText.setTextColor(Color.parseColor("#707070"));
        tabbarSearchIcon.setImageResource(R.drawable.tabbar_search_unselector);
        tabbarSearchLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tabbarSearchText.setTextColor(Color.parseColor("#707070"));
        tabbarUserIcon.setImageResource(R.drawable.tabbar_user_unselector);
        tabbarUserLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tabbarUserText.setTextColor(Color.parseColor("#707070"));
    }
    //定义点击行为
    public void setChioceItem(int index)
    {
        clearChioce();      // 既然是点击选择，那么在点的时候就应该清除一下上一个索引
        // 重置选项+隐藏所有的Fragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                tabbarIndexIcon.setImageResource(R.drawable.tabbar_index_select);
                tabbarIndexText.setTextColor(Color.parseColor("#E64A19"));
                if (indexFragment == null) {
                    indexFragment = new indexFragment();
                    transaction.add(R.id.content,indexFragment);
                } else {
                    transaction.show(indexFragment);
                }
                break;

            case 1:

                tabbarRankIcon.setImageResource(R.drawable.tabbar_rank_select);
                tabbarRankText.setTextColor(Color.parseColor("#E64A19"));
                if (rankFragment == null) {
                    rankFragment = new rankFragment();
                    transaction.add(R.id.content,rankFragment);
                } else {
                    transaction.show(rankFragment);
                }
                break;

            case 2:
                tabbarSearchIcon.setImageResource(R.drawable.tabbar_search_select);
                tabbarSearchText.setTextColor(Color.parseColor("#E64A19"));
                if (searchFragment == null) {
                    searchFragment = new searchFragment();
                    transaction.add(R.id.content,searchFragment);
                } else {
                    transaction.show(searchFragment);
                }
                break;

            case 3:
                tabbarUserIcon.setImageResource(R.drawable.tabbar_user_select);
                tabbarUserText.setTextColor(Color.parseColor("#E64A19"));
                if (userFragment == null) {
                    userFragment = new userFragment();
                    transaction.add(R.id.content,userFragment);
                } else {
                    transaction.show(userFragment);
                }
                break;
        }
        transaction.commit();
    }
    private void hideFragments(FragmentTransaction transaction)
    {
        if (indexFragment != null) {
            transaction.hide(indexFragment);
        }
        if (rankFragment != null) {
            transaction.hide(rankFragment);
        }
        if (searchFragment != null) {
            transaction.hide(searchFragment);
        }
        if (userFragment != null) {
            transaction.hide(userFragment);
        }
    }
}