package com.example.mycloudmusic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.fragment.songdetail.leftFragment;
import com.example.fragment.songdetail.rightFragment;
import com.example.tools.HttpRequestTool;

import org.jetbrains.annotations.NotNull;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

public class SongDetailActivity extends AppCompatActivity {
    private String bgImgUrl;
    private String songName;
    private String authorName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        //设置状态栏透明化
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //初始化适配器，并且添加给viewpager2
        FragmentStateAdapter adapter = new FragmentStateAdapter(this) {
            @Override
            public Fragment createFragment(int position) {
                Fragment fragment = new leftFragment();
                switch (position){
                    case 0:
                        fragment = new leftFragment();
                        break;
                    case 1:
                        fragment = new rightFragment();
                        break;
                    default:
                        break;
                }
                return fragment;
            }
            @Override
            public int getItemCount() {
                return 2;
            }

        };
        ViewPager2 viewPager2 = findViewById(R.id.song_detail_viewPager);
        viewPager2.setAdapter(adapter);
        initSongDetail();
    }

    @Override
    protected void onStart() {
        super.onStart();
        drawBg();
        drawTitlebar();
        //test:
        ImageView play = (ImageView) findViewById(R.id.play_btn);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                leftFragment lf = (leftFragment) manager.findFragmentByTag("f0");
                Log.d("碎片的实例", "lf"+lf);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //初始化歌曲基本详情信息数据
    private void initSongDetail(){
        Intent intent = getIntent();
        long id = intent.getLongExtra("id",0);
        songName = intent.getStringExtra("songName");
        authorName = intent.getStringExtra("authorName");
        bgImgUrl = intent.getStringExtra("bgImg");
    }
    //绘制歌曲详情页界面的背景图
    private void drawBg(){
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.song_detail_bgView);
        //用Gilde给非View类加载图片
        Glide.with(this)
                .load(bgImgUrl)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(10,100)))
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull @NotNull Drawable resource, @Nullable @org.jetbrains.annotations.Nullable Transition<? super Drawable> transition) {
                        relativeLayout.setBackground(resource);
                    }
                    @Override
                    public void onLoadCleared(@Nullable @org.jetbrains.annotations.Nullable Drawable placeholder) {
                    }
                });
    }
    //绘制标题栏
    private void drawTitlebar(){
        TextView titleSongName = (TextView) findViewById(R.id.song_detail_song_name);
        TextView titleAuthorName = (TextView) findViewById(R.id.song_detail_song_author);
        titleSongName.setText(songName);
        titleAuthorName.setText(authorName);
    }
    //绘制left-fragment的CD图f0案
    public void drawCD(){
        FragmentManager manager = getSupportFragmentManager();
        leftFragment lf = (leftFragment) manager.findFragmentByTag("f0");
        lf.drawCD(bgImgUrl);
        Log.d("碎片的实例", "lf"+lf);
    }
}