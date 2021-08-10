package com.example.bean;

public class SongListBean {
    private String songListName;
    private String songListPics;
    public SongListBean(String songListName,String songListPics){
        this.songListName = songListName;
        this.songListPics = songListPics;
    }
    public String getSongListName(){
        return songListName;
    }
    public String getSongListPics(){
        return songListPics;
    }
    public void setSongListName( String songListName){
        this.songListName = songListName;
    }
    public void setSongListPics(String songListPics){
        this.songListPics = songListPics;
    }
}
