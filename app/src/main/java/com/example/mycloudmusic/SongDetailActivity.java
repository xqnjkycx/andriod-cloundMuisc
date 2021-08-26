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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.example.bean.MusicInfo;
import com.example.bean.lyricBean;
import com.example.fragment.songdetail.leftFragment;
import com.example.fragment.songdetail.rightFragment;
import com.example.gsonClass.Lyric;
import com.example.gsonClass.MusicUrl;
import com.example.tools.HttpRequestTool;
import com.example.tools.MyTool;
import com.google.gson.Gson;
import com.example.gsonClass.songDt;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import okhttp3.Call;
import okhttp3.Response;

public class SongDetailActivity extends BaseActivity {
    private int flag; //歌曲歌词行数计数器
    private String bgImgUrl;  //背景图片地址
    private String songName;  //当前歌曲名字
    private String authorName;//当前歌曲作者名
    private String durationTime;      //当前歌曲的总时间
    private long id;             //当前歌曲id
    private String lyric = "纯音乐，尽情享受"; //歌曲歌词
    private String nowTime; //当前时间
    private MusicInfo musicInfo;
    private TextView startTimeView;
    private long dt = 3599;                  //当前歌曲的总秒数
    private String music;     //当前歌曲url
    private leftFragment lf;  //CD碎片实例
    private rightFragment lr; //歌词碎片实例
    private SeekBar seekBar;//进度条实例
    private Map<String,String> lyricMap = new HashMap<>(); //时间 -- 歌词 映射对象
    private static String PLAY = "play";//判断当前歌曲播放状态变量（播放、暂停）
    private static String HANDLER_STATUS = "handler_stop";
    private Intent musicPlayerIntent; //开启服务intent
    private float finalProgress; //进度条进度
    private RecyclerView lyricRecyclerView; //歌词滚动界面的recycler
    private LyricAdapter adapter;   //歌此滚动界面的适配器adapter
    private List<lyricBean> lyricList = new ArrayList<>(); //存放歌词的数组
    private List<String> timeList = new ArrayList<>();
    private int currentPosition; //当前播放的歌词行数
    private MediaPlayer musicPlayer; //歌曲播放器实例
    private ImageView nextBtn;
    public static final String ACTION_SERVICE_NEED = "action.serviceneed";
    public MusicPlayerServiceReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        //设置状态栏透明化,
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //初始化适配器，并且动态添加给viewpager2，其内容主要包含CD界面和歌词界面
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
        viewPager2.setOffscreenPageLimit(2); //setOffscreenPagelimit能够缓存fragment的状态，解决了歌词界面切换到CD界面，CD闪动的问题
        seekBar = (SeekBar) findViewById(R.id.progress_bar); //初始化进度条
        initSongDetail(); //发送请求信息，得到歌词、歌曲时长...
        musicPlayerIntent = new Intent(this, MusicPlayerService.class); //开启播放服务，进入到歌曲详情页就自动播放
        startTimeView = (TextView) findViewById(R.id.start_time);
        initClick();//初始化各类点击事件
        IntentFilter fliter = new IntentFilter();
        fliter.addAction(ACTION_SERVICE_NEED);
        receiver = new MusicPlayerServiceReceiver();
        registerReceiver(receiver,fliter);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Boolean flag = true;
        int size = MusicPlayerService.getMusicPlayList().size();
        for(int i = 0 ; i < MusicPlayerService.getMusicPlayList().size();i++){
            if(musicInfo.getId() ==MusicPlayerService.getMusicPlayList().get(i).getId()){
                flag = false;
                break;
            }
        }
        if(flag == true){
            MusicPlayerService.getMusicPlayList().add(musicInfo);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        HANDLER_STATUS = "handler_stop";//活动销毁的时候，暂停计时线程
        unregisterReceiver(receiver);
    }
    //广播内部类，用于接收服务发过来的消息
    class MusicPlayerServiceReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            nextBtn.performClick();
            Log.d("收到了来自服务传递过来的信息","------");
        }
    }


    //请求歌曲基本详情信息数据
    private void initSongDetail(){
        /**
         * 由于歌曲的一些信息，比如歌名和作者名在上一页的歌单详情界面就已经请求到了
         * 这里就不再发请求，直接intent传过来
         * */
        Intent intent = getIntent();
        id = intent.getLongExtra("id",0);
        songName = intent.getStringExtra("songName");
        authorName = intent.getStringExtra("authorName");
        bgImgUrl = intent.getStringExtra("bgImg");
        musicInfo = new MusicInfo(bgImgUrl,songName,authorName,id);
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
                durationTime = formatTime(dt);    //传过来的duration比较复杂，这里将其处理为 xx:xx 的形式
                musicInfo.setDurationTime(durationTime);
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
                musicInfo.setMusic(music);
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
                //请求完成之后开启计时线程，计算当前播放的时间
                HANDLER_STATUS = "handler_begin";
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
                String responseData = response.body().string();
                Lyric obj = gson.fromJson(responseData,Lyric.class);
                Lyric.LrcDTO lyricObj = obj.getLrc();
                    if(lyricObj != null ) {
                    lyric = lyricObj.getLyric();
                    //歌词处理函数
                    formatLyric(lyric);
                }else {
                        lyricList.add(new lyricBean(lyric));
                    }
                    musicInfo.setLyric(lyric);
                Message message = new Message();
                message.what = 3;
                handler.sendMessage(message);
            }
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }
    //初始化各种点击、拖动事件
    private void initClick(){
        //播放暂停点击事件
        ImageView playBtn = (ImageView) findViewById(R.id.play_btn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PLAY.equals("play")){
                    PLAY = "pause";  //如果现在是播放状态，点击按钮则暂停
                    Glide.with(SongDetailActivity.this)
                            .load(R.drawable.song_detail_play)
                            .into(playBtn);
                    lf.pauseCD();//CD停止转动
                    musicPlayerIntent.putExtra("action","pause");//给服务发送消息，暂停音乐播放
                    startService(musicPlayerIntent);
                    HANDLER_STATUS = "handler_stop";//关闭计时线程
                }else{
                    PLAY = "play";
                    HANDLER_STATUS="handler_begin"; //开启计时线程
                    handler.sendEmptyMessage(4);
                    Glide.with(SongDetailActivity.this)
                            .load(R.drawable.song_detail_pause)
                            .into(playBtn);
                    lf.resumeCD();//CD继续转动
                    playMusic();
                }
            }
        });
        //进度条拖动事件
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //拖动过程中
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                finalProgress = progress;
            }
            //开始拖动
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //停止拖动s
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                long currentTime;
                currentTime = (long) Math.floor(dt/1000 * finalProgress / 100);
                String nowTime;
                long sec = currentTime % 60;
                long min = (currentTime - sec)/60;
                String mint;
                String sect;
                mint = min < 10 ? "0" + min : "" + min;
                sect = sec < 10 ? "0" + sec : "" + sec;
                nowTime = mint + ":" + sect;
                startTimeView.setText(nowTime);
                MusicPlayerService.getMusicPlayer().seekTo((int)currentTime*1000);
                //定位歌词所在行
                for(int i = 0 ; i < timeList.size();i++){
                    if(i == timeList.size()-1){
                        flag = i + 1;
                        MusicPlayerService.setFlag(flag);
                        highLightLyric(i,lyricList.get(i).getLyric());
                        break;
                    }else{
                        if(currentTime>=changeTimeToSecond(timeList.get(i))&&currentTime<changeTimeToSecond(timeList.get(i+1))){
                            flag = i + 1;
                            MusicPlayerService.setFlag(flag);
                            highLightLyric(i,lyricList.get(i).getLyric());
                            lyricRecyclerView.scrollToPosition(i);
                            break;
                        }
                    }
                }

            }
        });
        //修改播放模式事件
        ImageView modeBtn = (ImageView) findViewById(R.id.mode_btn);
        modeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceMode = MusicPlayerService.getMode();
                String text = "";
                if(serviceMode.equals("LOOP")){
                    //修改为顺序播放
                   Glide.with(SongDetailActivity.this).load(R.drawable.order).into(modeBtn);
                   MusicPlayerService.setMode("ORDER");
                   text = "顺序播放";
                }else if(serviceMode.equals("ORDER")){
                    //修改为自动播放
                    Glide.with(SongDetailActivity.this).load(R.drawable.random).into(modeBtn);
                    MusicPlayerService.setMode("RANDOM");
                    text="随机播放";
                }else if(serviceMode.equals("RANDOM")){
                    //修改为随机播放
                    Glide.with(SongDetailActivity.this).load(R.drawable.loop).into(modeBtn);
                    MusicPlayerService.setMode("LOOP");
                    text="单曲循环";
                }
                Toast.makeText(SongDetailActivity.this,text,Toast.LENGTH_SHORT).show();
            }
        });
        //播放下一首事件
        nextBtn = (ImageView) findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mode = MusicPlayerService.getMode();
                int index = MusicPlayerService.getPlayIndex();
                int size = MusicPlayerService.getMusicPlayList().size();
                if(mode.equals("ORDER")){
                    //正常播放下一首，如果是最后一首就跳转到第一首去
                   if(index == size - 1){
                       MusicPlayerService.setPlayIndex(0);
                   }else {
                       MusicPlayerService.setPlayIndex(index+1);
                   }
                   index = MusicPlayerService.getPlayIndex();
                }else if(mode.equals("LOOP")){

                }else if(mode.equals("RANDOM")){
                    index = MyTool.generateRandomNumberTool(0,size);
                    MusicPlayerService.setPlayIndex(index);
                }
                changeMusic(index);
            }
        });
        //播放上一首事件
        ImageView prvBtn = (ImageView) findViewById(R.id.prv_btn);
        prvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mode = MusicPlayerService.getMode();
                int index = MusicPlayerService.getPlayIndex();
                int size = MusicPlayerService.getMusicPlayList().size();
                if(mode.equals("ORDER")){
                    //正常播放下一首，如果是最后一首就跳转到第一首去
                    if(index == 0){
                        MusicPlayerService.setPlayIndex(size - 1);
                    }else {
                        MusicPlayerService.setPlayIndex(index - 1);
                    }
                    index = MusicPlayerService.getPlayIndex();
                }else if(mode.equals("LOOP")){

                }else if(mode.equals("RANDOM")){
                    index = MyTool.generateRandomNumberTool(0,size);
                    MusicPlayerService.setPlayIndex(index);
                }
                changeMusic(index);
            }
        });
    }
    //绘制歌曲详情页界面的背景图
    private void drawBg(){
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.song_detail_bgView);
        //用Gilde给非View类加载图片
        Glide.with(this)
                .load(bgImgUrl)
                //背景图的高斯模糊就用这个
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(10,100)))
                .into(new CustomTarget<Drawable>() {//通过Glide给布局添加背景和给imageView加背景不一样，以下为正确写法
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
        /**
         * 拿到CD碎片实例，但是它的调用者应该在fragment的生命周期函数里面
         * 如果在activity里面的生命周期函数获取这个碎片实例，不能保证碎片这时已经完全创建好了
         * 所以应该在碎片的onStart的生命周期里面调用这个方法，保证lf能获取到
         * 下面获取右侧碎片实例的道理也是一样的
         * */
        FragmentManager manager = getSupportFragmentManager();
        lf = (leftFragment) manager.findFragmentByTag("f0");
    }
    //获取右侧碎片实例
    public void getRightFragmentInstance(){
        FragmentManager manager = getSupportFragmentManager();
        lr = (rightFragment) manager.findFragmentByTag("f1");
    }
    //绘制进度条
    public void drawProgress(){
        TextView endTime = (TextView) findViewById(R.id.end_time);
        endTime.setText(durationTime);
    }
    //绘制歌词界面
    private void drawLyric(){
        //recycler适配
        lyricRecyclerView = (RecyclerView) findViewById(R.id.lyric_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        lyricRecyclerView.setLayoutManager(layoutManager);
        adapter = new LyricAdapter(lyricList);
        lyricRecyclerView.setAdapter(adapter);
    }
    //播放音乐方法
    public void playMusic(){
         musicPlayerIntent.putExtra("musicUrl",music);
         musicPlayerIntent.putExtra("action","play");
         startService(musicPlayerIntent);//开启服务开始放音乐
    }
    //异步任务处理机制
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    drawProgress();
                    break;
                case 2:
                    drawTitlebar();
                    playMusic();
                    drawBg();
                    lf.drawCD(bgImgUrl);
                    break;
                case 3:
                    drawLyric();
                    break;
                case 4:
                    if(HANDLER_STATUS.equals("handler_begin")){
                        try {
                            //计时线程，当计时线程开启的时候，不断去更新progress的进度和左侧的当前播放时间
                            musicPlayer = MusicPlayerService.getMusicPlayer();
                            Log.d("musicPlayer",musicPlayer+"");
                            finalProgress = musicPlayer.getCurrentPosition()*100/dt;
                            seekBar.setProgress((int)finalProgress);
                            nowTime = formatTime(musicPlayer.getCurrentPosition());
                            startTimeView.setText(nowTime);
                            progressListener(nowTime);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessageDelayed(4,1000);
                        Log.d("Handler_status",""+HANDLER_STATUS);
                    }
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        }
    };

    //整理歌曲总时间
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
    //处理歌词
    private void formatLyric(String lyric){
        //主要做了HashMap的布置和歌词list的更新
        lyricMap.clear();
        lyricList.clear();
        String pattern = "(\\[)(.*?)\\n";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(lyric);
        String tl;
        String key;
        String value;
        int condition;
        int length;
        while (m.find()){
            tl = m.group(0);
            length = tl.length();
            key = tl.substring(1,6);
            condition = Integer.parseInt(tl.substring(7,8)) ;
            if(condition >= 8){
                int total = changeTimeToSecond(key) + 1;
                int sec = total % 60;
                int min = (total - sec)/60;
                String mint;
                String sect;
                mint = min < 10 ? "0" + min : "" + min;
                sect = sec < 10 ? "0" + sec : "" + sec;
                key = mint + ":" + sect;
            }
            value = tl.substring(tl.indexOf("]")+1,length);
            if(value.length()!=1){
                lyricList.add(new lyricBean(value));
                timeList.add(key);
                lyricMap.put(key,value);
            }
        }
    }
    //进度监听者
    private  void  progressListener(String t){
        String lyric = lyricMap.get(t);
        flag = MusicPlayerService.getFlag();
        Log.d("flag",flag+"");
        if(lyric!=null){
            currentPosition = flag;
            if(lyricList.get(currentPosition).getLyric().equals(lyric)&&currentPosition<lyricList.size()){
                flag++;
                MusicPlayerService.setFlag(flag);
                highLightLyric(currentPosition,lyric);
            }
        }
    }
    //更新歌词高亮
    private void highLightLyric(int position,String currentLyric){
        adapter.selectedItem(position,currentLyric);
        if(position > 5 && position+5<lyricList.size()){
            lyricRecyclerView.scrollToPosition(position+5);
        }
        if(position == lyricList.size()-1){
            lyricRecyclerView.scrollToPosition(position);
        }
    }
    //工具类，将秒数变为xx:xx的时间形式 转化为 总秒数。 例如03：00 转化为 180秒
    private int changeTimeToSecond(String time){
        int minTotalSecond = Integer.parseInt(time.substring(0,2) )* 60 ;
        int secTotalSecond = Integer.parseInt(time.substring(3,5));
        return minTotalSecond + secTotalSecond;
    }
    //切换歌曲
    private void changeMusic(int index){
        //根据索引拿到对应的 歌曲信息
        HANDLER_STATUS = "handler_begin"; //先把歌词线程给阻止了，等一会再开
        MusicInfo item = MusicPlayerService.getMusicPlayList().get(index);
        authorName = item.getAuthorName();
        songName = item.getSongName();
        bgImgUrl = item.getBgImgUrl();
        durationTime = item.getDurationTime();
        music = item.getMusic();
        lyric = item.getLyric();
        //更新标题栏
        drawTitlebar();
        //更新背景
        drawBg();
        //更新CD
        lf.drawCD(bgImgUrl);
        //更新进度条
        drawProgress();
        //更新歌词
        if(lyric.equals("纯音乐，尽情享受")){

        }else {
            formatLyric(lyric);
        }
        drawLyric();
        //重新开启音乐
        musicPlayerIntent.putExtra("musicUrl",music);
        //重播这首歌
        musicPlayerIntent.putExtra("action","overplay");
        startService(musicPlayerIntent);//开启服务开始放音乐

        HANDLER_STATUS="handler_begin";
        handler.sendEmptyMessage(4);
    }
}