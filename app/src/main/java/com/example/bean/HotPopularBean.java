package com.example.bean;

import com.example.gsonClass.HotPopularClass;

public class HotPopularBean {
    private String name;
    private String picUrl;
    private long id;
    public HotPopularBean(String name , String picUrl,long id){
        this.name = name;
        this.picUrl = picUrl;
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public String getPicUrl(){
        return picUrl;
    }
    public void  setPicUrl(String picUrl){
        this.picUrl = picUrl;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setId(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
