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
import com.example.tools.HttpRequestTool;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;
import org.litepal.LitePal;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity{
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

    private String nickName;
    private String avatarUrl;
    private String backgroundUrl;
    private long userId;
    private String cookie;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //??????????????????????????????
        LitePal.initialize(this);

        Personalized personalizedSongsBlock = new Personalized();
        personalizedSongsBlock.init();
        //??????Toolbar??????
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //????????????????????????
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.user);
        }
        /**
         * ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
         * ????????????????????????????????????????????????????????????????????????findViewById?????????
         * ???????????????????????? getHeeaderView(0) ????????????????????????????????????
         * **/
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0);
        Intent intent = getIntent();
         nickName = intent.getStringExtra("nickName");
         avatarUrl = intent.getStringExtra("avatarUrl");
         backgroundUrl = intent.getStringExtra("backgroundUrl");
         userId = intent.getLongExtra("userId",0);
         cookie = intent.getStringExtra("cookie");
        ImageView navHeaderBg= (ImageView) headerView.findViewById(R.id.nav_header_bg);
        CircleImageView iconHeader = (CircleImageView) headerView.findViewById(R.id.icon_header);
        TextView avatarName = (TextView) headerView.findViewById(R.id.user_name);
        Glide.with(MainActivity.this).load(avatarUrl).into(iconHeader);
        Glide.with(MainActivity.this).load(backgroundUrl).into(navHeaderBg);
        avatarName.setText(nickName);
        //?????????tabbaer
        fragmentManager = getSupportFragmentManager();
        tabbarInitView();
    }
    //??????Toolbar?????????
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    //Toolbar????????????????????????
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
    //?????????Tabbar
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
        //?????????????????????
        setChioceItem(0);
    }
    //???????????????????????????????????????????????????????????????????????????
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
    //??????????????????
    public void setChioceItem(int index)
    {
        clearChioce();      // ?????????????????????????????????????????????????????????????????????????????????
        // ????????????+???????????????Fragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                tabbarIndexIcon.setImageResource(R.drawable.tabbar_index_select);
                tabbarIndexText.setTextColor(Color.parseColor("#E64A19"));
                if (indexFragment == null) {
                    indexFragment = new indexFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("cookie",cookie);
                    indexFragment.setArguments(bundle);
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
                    Bundle bundle = new Bundle();
                    bundle.putString("cookie",cookie);
                    rankFragment.setArguments(bundle);
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
                    Bundle bundle = new Bundle();
                    //?????????????????????????????????????????????????????????????????????id?????????cookie
                    bundle.putString("nickName",nickName);
                    bundle.putString("avatarUrl",avatarUrl);
                    bundle.putString("backgroundUrl",backgroundUrl);
                    bundle.putLong("userId",userId);
                    bundle.putString("cookie",cookie);
                    userFragment.setArguments(bundle);
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