package com.example.mycloudmusic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.Adapter.LyricAdapter;
import com.example.Service.MusicPlayerService;
import com.example.bean.lyricBean;
import com.example.fragment.songdetail.leftFragment;
import com.example.fragment.songdetail.rightFragment;
import com.example.gsonClass.Lyric;
import com.example.gsonClass.MusicUrl;
import com.example.tools.HttpRequestTool;
import com.google.gson.Gson;
import com.example.gsonClass.songDt;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import okhttp3.Call;
import okhttp3.Response;

public class SongDetailActivity extends BaseActivity {
    private String bgImgUrl;  //背景图片地址
    private String songName;  //当前歌曲名字
    private String authorName;//当前歌曲作者名
    private String durationTime;      //当前歌曲的总时间
    private String nowTime; //当前时间
    private TextView startTimeView;
    private long dt = 3599;                  //当前歌曲的总秒数
    private String music;     //当前歌曲url
    private leftFragment lf;  //CD碎片实例
    private rightFragment lr; //歌词碎片实例
    private SeekBar seekBar;//进度条实例
    private static String PLAY = "play";//判断当前歌曲播放状态变量（播放、暂停）
    private Intent musicPlayerIntent; //开启服务intent
    private float progress; //进度条进度
    private List<lyricBean> lyricList = new ArrayList<>();
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
        viewPager2.setOffscreenPageLimit(2);
        seekBar = (SeekBar) findViewById(R.id.progress_bar);
        initSongDetail();
        musicPlayerIntent = new Intent(this, MusicPlayerService.class);
        startTimeView = (TextView) findViewById(R.id.start_time);
    }

    @Override
    protected void onStart() {
        super.onStart();
        drawTitlebar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initClick();
    }
    //初始化歌曲基本详情信息数据
    private void initSongDetail(){
        Intent intent = getIntent();
        long id = intent.getLongExtra("id",0);
        songName = intent.getStringExtra("songName");
        authorName = intent.getStringExtra("authorName");
        bgImgUrl = intent.getStringExtra("bgImg");
        String url = "http://10.0.2.2:3000/song/detail?ids="+id;
        //获取歌曲播放时长
        HttpRequestTool.get(url,new okhttp3.Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                songDt songdt = gson.fromJson(responseData,songDt.class);
                List<songDt.SongsDTO> list = songdt.getSongs();
                for(songDt.SongsDTO item : list){
                    dt = item.getDt();
                }
                durationTime = formatTime(dt);
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) { }
        });
        //获取歌曲播放url
        HttpRequestTool.get("http://10.0.2.2:3000/song/url?id="+id,new okhttp3.Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                MusicUrl musicObj = gson.fromJson(responseData,MusicUrl.class);
                List<MusicUrl.DataDTO> data = musicObj.getData();
                for(MusicUrl.DataDTO item : data){
                    music = item.getUrl();
                }
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
                handler.sendEmptyMessage(4);
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }
        });
        //获取歌曲播放歌词
        HttpRequestTool.get("http://10.0.2.2:3000/lyric?id="+id,new okhttp3.Callback(){
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Gson gson = new Gson();
                Log.d("id",id+"");
                String responseData = response.body().string();
                Lyric obj = gson.fromJson(responseData,Lyric.class);
                Lyric.LrcDTO lyricObj = obj.getLrc();
                String lyric = "纯音乐，尽情享受";
                    if(lyricObj != null ) {
                    lyric = lyricObj.getLyric();
                    String pattern = "\\](.*?)\\n";
                    Pattern r = Pattern.compile(pattern);
                    Matcher m = r.matcher(lyric);
                    while (m.find()){
                        int length = m.group(0).length();
                        lyricList.add(new lyricBean(m.group(0).substring(1,length)));
                    }
                }else {
                        lyricList.add(new lyricBean(lyric));
                    }
                Message message = new Message();
                message.what = 3;
                handler.sendMessage(message);
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }
    //初始化各种点击事件
    private void initClick(){
        //播放暂停点击事件
        ImageView playBtn = (ImageView) findViewById(R.id.play_btn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PLAY.equals("play")){
                    //如果现在是播放状态，点击按钮则暂停
                    PLAY = "pause";
                    Glide.with(SongDetailActivity.this)
                            .load(R.drawable.song_detail_play)
                            .into(playBtn);
                    //调用碎片的播放CD动画方法
                    lf.pauseCD();
                    //暂停音乐
                    musicPlayerIntent.putExtra("action","pause");
                    startService(musicPlayerIntent);
                }else{
                    PLAY = "play";
                    handler.sendEmptyMessage(4);
                    Glide.with(SongDetailActivity.this)
                            .load(R.drawable.song_detail_pause)
                            .into(playBtn);
                    //调用碎片的暂停动画方法
                    lf.resumeCD();
                    //开始音乐
                    playMusic();
                }
            }
        });

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
        lf = (leftFragment) manager.findFragmentByTag("f0");
    }
    //绘制进度条
    public void drawProgress(){
        TextView endTime = (TextView) findViewById(R.id.end_time);
        endTime.setText(durationTime);
    }
    //绘制歌词界面
    private void drawLyric(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lyric_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        LyricAdapter adapter = new LyricAdapter(lyricList);
        recyclerView.setAdapter(adapter);
    }
    //获取右侧碎片实例
    public void getRightFragmentInstance(){
        FragmentManager manager = getSupportFragmentManager();
        lr = (rightFragment) manager.findFragmentByTag("f1");
    }
    //播放音乐方法
    public void playMusic(){
        musicPlayerIntent.putExtra("musicUrl",music);
         musicPlayerIntent.putExtra("action","play");
         startService(musicPlayerIntent);
    }
    //异步任务处理机制
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    drawProgress();
                    break;
                case 2:
                    playMusic();
                    drawBg();
                    lf.drawCD(bgImgUrl);
                    break;
                case 3:
                    drawLyric();
                    break;
                case 4:
                    try {
                        progress = MusicPlayerService.getMusicPlayer().getCurrentPosition()*100/dt;
                        seekBar.setProgress((int)progress);
                        nowTime = formatTime(MusicPlayerService.getMusicPlayer().getCurrentPosition());
                        startTimeView.setText(nowTime);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessageDelayed(4,1100);

                    break;
                default:
                    break;
            }
        }
    };

    //整理时间
    private String formatTime(long dt){
        String time;
        long s = (dt - ( dt % 1000))/1000;
        long sec = s % 60;
        long min = (s - sec)/60;
        String mint;
        String sect;
        mint = min < 10 ? "0" + min : "" + min;
        sect = sec < 10 ? "0" + sec : "" + sec;
        time = mint + ":" + sect;
        return time;
    }
}