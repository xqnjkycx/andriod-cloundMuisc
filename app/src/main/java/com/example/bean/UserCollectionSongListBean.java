package com.example.bean;

public class UserCollectionSongListBean {
    private int trackCount;
    private String coverImgUrl;
    private String name;
    private String nickName;
    private long id;
    private String cookie;

    public UserCollectionSongListBean(int trackCount,String coverImgUrl,String nickName,String name,long id,String cookie){
        this.trackCount = trackCount;
        this.coverImgUrl = coverImgUrl;
        this.name = name;
        this.nickName = nickName;
        this.id = id;
        this.cookie = cookie;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
