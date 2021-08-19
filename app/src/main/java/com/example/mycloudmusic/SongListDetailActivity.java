package com.example.mycloudmusic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.Adapter.SongListDetailAdapter;
import com.example.tools.HttpRequestTool;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import com.example.bean.personalizedDetailBean;
import com.example.gsonClass.personalizedDetailGson;

public class SongListDetailActivity extends AppCompatActivity {
    private List<personalizedDetailBean> detailList = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list_detail);
        //接收歌单跳转过来传递过来的数据
        Intent intent = getIntent();
        String picUrl = intent.getStringExtra("picUrl");
        String name = intent.getStringExtra("name");
        String cookie = intent.getStringExtra("cookie");
        long id = intent.getLongExtra("id",0);
        //设置状态栏透明色
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initToolBar( picUrl,name);
        initSongListDetail(id,cookie);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //初始化ToolBar
    private void initToolBar(String picUrl,String name){
        Toolbar toolbar = (Toolbar) findViewById(R.id.songList_detail_toolbar);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.songList_detail_collapsing_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.back);
        }
        ImageView imgView = (ImageView) findViewById(R.id.songList_detail_bg);
        Glide.with(imgView.getContext()).load(picUrl).into(imgView);
    }
    // 按钮后退功能
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            default:
        }
        return true;
    }
    //初始化歌单详情数据请求
    private void initSongListDetail(long id,String cookie){
        String url = "http://10.0.2.2:3000/playlist/detail?id="+id+"&cookie="+cookie;
        Log.d("url",url);
        HttpRequestTool.get(url,new okhttp3.Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                personalizedDetailGson obj = gson.fromJson(responseData,personalizedDetailGson.class);
                personalizedDetailGson.PlaylistDTO playlistObj = obj.getPlaylist();
                List<personalizedDetailGson.PlaylistDTO.TracksDTO> tracks = playlistObj.getTracks();
                for (personalizedDetailGson.PlaylistDTO.TracksDTO track : tracks){
                    String songName = track.getName();
                    String imgUrl = track.getAl().getPicUrl();
                    String authorName = null;
                    long id = track.getId();
                    List<personalizedDetailGson.PlaylistDTO.TracksDTO.ArDTO> ars = track.getAr();
                    for (personalizedDetailGson.PlaylistDTO.TracksDTO.ArDTO ar : ars){
                        if(authorName == null){
                            authorName = ar.getName();
                        }else {
                            authorName = authorName + " & " + ar.getName();
                        }
                    }
                    detailList.add(new personalizedDetailBean(authorName,songName,id,imgUrl));
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }
    //绘制歌单详情滚动模块
    private void drawUI(){
        recyclerView = (RecyclerView) findViewById(R.id.songList_detail_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SongListDetailAdapter adapter = new SongListDetailAdapter(detailList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
    //切回主线程做UI操作
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    drawUI();
                    break;
                default:
                    break;
            }
        }
    };
}