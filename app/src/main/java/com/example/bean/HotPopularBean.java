package com.example.bean;

import com.example.gsonClass.HotPopularClass;

public class HotPopularBean {
    private String name;
    private String picUrl;
    public HotPopularBean(String name , String picUrl){
        this.name = name;
        this.picUrl = picUrl;
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
}
