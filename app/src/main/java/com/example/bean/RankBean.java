package com.example.bean;

public class RankBean {
    private String updateFrequency;
    private String description;
    private String coverImgUrl;
    private String name;
    private long id;
    private String cookie;


    public RankBean(String updateFrequency, String description, String coverImgUrl, String name, long id,String cookie) {
        this.updateFrequency = updateFrequency;
        this.description = description;
        this.coverImgUrl = coverImgUrl;
        this.name = name;
        this.id = id;
        this.cookie = cookie;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getUpdateFrequency() {
        return updateFrequency;
    }

    public void setUpdateFrequency(String updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
