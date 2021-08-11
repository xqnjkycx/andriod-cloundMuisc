package com.example.bean;

public class SongListBean {
    private String songListName;
    private String songListPics;
    private long id;
    public SongListBean(String songListName,String songListPics,long id){
        this.songListName = songListName;
        this.songListPics = songListPics;
        this.id = id;
    }
    public String getSongListName(){
        return songListName;
    }
    public String getSongListPics(){
        return songListPics;
    }
    public long getSongListId(){return id;}
    public void setSongListName( String songListName){
        this.songListName = songListName;
    }
    public void setSongListPics(String songListPics){
        this.songListPics = songListPics;
    }
    public void setSongListId(long id){
        this.id = id;
    }
}
