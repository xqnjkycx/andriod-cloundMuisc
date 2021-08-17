package com.example.bean;


import org.litepal.crud.LitePalSupport;

public class HistorySearchBean extends LitePalSupport {
    private String historySearchName;
    public HistorySearchBean(String historySearchName){
        this.historySearchName = historySearchName;
    }
    public String getHistorySearchName() {
        return historySearchName;
    }

    public void setHistorySearchName(String historySearchName) {
        this.historySearchName = historySearchName;
    }
}
