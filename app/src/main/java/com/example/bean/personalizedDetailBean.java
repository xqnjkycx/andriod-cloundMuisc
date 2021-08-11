package com.example.bean;

public class personalizedDetailBean {
    String songName;
    String authorName;

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
    public personalizedDetailBean(String authorName,String songName){
        this.authorName = authorName;
        this.songName = songName;
    }
}
