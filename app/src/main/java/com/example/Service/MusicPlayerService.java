package com.example.Service;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
public class MusicPlayerService extends Service {
    private String oldMusicUrl="";
    private String newMusicUrl="";
    private static int  flag = 0;
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

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getStringExtra("action");
        newMusicUrl = intent.getStringExtra("musicUrl");
        if (oldMusicUrl.equals(newMusicUrl)){
            if("pause".equals(action)){
                pauseMusic();
            }else if("play".equals(action)){
                musicPlayer.start();
            }
        }else {
            oldMusicUrl = newMusicUrl;
            if ("play".equals(action)){
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