package com.example.bean;

public class SearchListBean {
    String songName;
    String authorName;
    long id;

    public SearchListBean(String songName,String authorName,long id){
        this.songName = songName;
        this.authorName = authorName;
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
}
