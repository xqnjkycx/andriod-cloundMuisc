package com.example.bean;

public class personalizedDetailBean {
    String songName;
    String authorName;
    long id;
    String songImg;

    public String getSongImg() {
        return songImg;
    }

    public void setSongImg(String songImg) {
        this.songImg = songImg;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
    public personalizedDetailBean(String authorName,String songName,long id,String songImg){
        this.authorName = authorName;
        this.songName = songName;
        this.id = id;
        this.songImg = songImg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
