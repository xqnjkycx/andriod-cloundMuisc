package com.example.LitePal;

import org.litepal.crud.LitePalSupport;

public class HistorySearch extends LitePalSupport {
    private String historySearchName;

    public String getHistorySearchName() {
        return historySearchName;
    }

    public void setHistorySearchName(String historySearchName) {
        this.historySearchName = historySearchName;
    }
}
