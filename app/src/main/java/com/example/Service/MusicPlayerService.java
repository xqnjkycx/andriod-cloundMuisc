package com.example.Service;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import com.example.bean.MusicInfo;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayerService extends Service {
    private String oldMusicUrl="";
    private String newMusicUrl="";
    private static int  flag = 0;
    private static String mode = "ORDER"; //默认播放器是顺序播放
    private static List<MusicInfo> musicPlayList = new ArrayList<>();
    private static int playIndex = -1;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static int getFlag() {
        return flag;
    }

    public static void setFlag(int flag) {
        MusicPlayerService.flag = flag;
    }

    public static String getMode() {
        return mode;
    }

    public static int getPlayIndex() {
        return playIndex;
    }

    public static void setPlayIndex(int playIndex) {
        MusicPlayerService.playIndex = playIndex;
    }

    public static void setMode(String mode) {
        MusicPlayerService.mode = mode;
    }

    public static List<MusicInfo> getMusicPlayList() {
        return musicPlayList;
    }

    public static void setMusicPlayList(List<MusicInfo> musicPlayList) {
        MusicPlayerService.musicPlayList = musicPlayList;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        musicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getStringExtra("action");
        newMusicUrl = intent.getStringExtra("musicUrl");
        //如果是同一首歌说明上一次点进歌曲详情的歌曲 与 这次点进来的歌曲 是同一首歌曲
        if (oldMusicUrl.equals(newMusicUrl)){
            if("pause".equals(action)){
                pauseMusic();
            }else if("play".equals(action)){
                musicPlayer.start();
            }else if("overplay".equals(action)){
                //强制要求重新播放，用于使用切换上一首和下一首
                startMusic();
            }
        }else {
            oldMusicUrl = newMusicUrl;
            if ("play".equals(action)||"overplay".equals(action)){
                if(musicPlayer==null){
                    playMusic();
                }else if(musicPlayer!=null){
                    startMusic();
                }
            }else if("pause".equals(action)){
                pauseMusic();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private static MediaPlayer musicPlayer;
    /**
     * 给musicPlayer绑定一个监听事件，用于监听歌曲是否播放完成
     * */


    /**
     * 获取musicPlayer实例
     * */
    public static MediaPlayer getMusicPlayer() {
        return musicPlayer;
    }

    /**
     * 播放音乐
     * */
    private void playMusic(){
        musicPlayer = MediaPlayer.create(this, Uri.parse(newMusicUrl));
        musicPlayer.start();
    }
    /**
     * 暂停音乐
     * */
    private void pauseMusic(){
        if(musicPlayer!=null&&musicPlayer.isPlaying()){
            musicPlayer.pause();
        }
    }
    /**
     * 停止音乐
     * */
    private void stopMusic(){
            musicPlayer.stop();
            musicPlayer.reset();
            musicPlayer.release();
            musicPlayer = null;
    }
    /**
     * 开启音乐(当点入一首新歌进来的时候，应该把上一首的歌暂停释放并重新播放这首新歌)
     * */
    private void startMusic(){
        stopMusic();
        musicPlayer = MediaPlayer.create(this, Uri.parse(newMusicUrl));
        musicPlayer.start();
        flag = 0;
    }
}