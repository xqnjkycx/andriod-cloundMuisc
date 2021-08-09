//轮播图的适配bean

package com.example.bean;

public class BannerBean {
    private String url;
    public BannerBean(String url){
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
    public void setUrlPath(String url) {
        this.url = url;
    }
}
