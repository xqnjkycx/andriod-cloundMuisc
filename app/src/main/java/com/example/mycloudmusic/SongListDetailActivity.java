package com.example.mycloudmusic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list_detail);
        //接收歌单跳转过来传递过来的数据
        Intent intent = getIntent();
        String picUrl = intent.getStringExtra("picUrl");
        String name = intent.getStringExtra("name");
        long id = intent.getLongExtra("id",0);
        initToolBar( picUrl,name);
        initSongListDetail(id);
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
    private void initSongListDetail(long id){
        String url = "http://10.0.2.2:3000/playlist/detail?id="+id;
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
                    String authorName = null;
                    List<personalizedDetailGson.PlaylistDTO.TracksDTO.ArDTO> ars = track.getAr();
                    for (personalizedDetailGson.PlaylistDTO.TracksDTO.ArDTO ar : ars){
                        if(authorName == null){
                            authorName = ar.getName();
                        }else {
                            authorName = authorName + " & " + ar.getName();
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }

                    }
                    detailList.add(new personalizedDetailBean(authorName,songName));
                }

            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }
    //绘制歌单详情滚动模块
    private void drawUI(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.songList_detail_recycler);
        Log.d("TAG",""+recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        Log.d("TAG",layoutManager+"");
        recyclerView.setLayoutManager(layoutManager);
        SongListDetailAdapter adapter = new SongListDetailAdapter(detailList);
        recyclerView.setAdapter(adapter);
    }
    //切回主线程做UI操作
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    //do some UI
                    drawUI();
                    break;

                default:
                    break;
            }
        }
    };
}