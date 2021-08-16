package com.example.bean;

public class HotSearchBean {
    private String searchWord;

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public HotSearchBean(String searchWord) {
        this.searchWord = searchWord;
    }
}
