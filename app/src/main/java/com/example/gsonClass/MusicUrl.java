package com.example.gsonClass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MusicUrl {

    private List<DataDTO> data;

    public List<DataDTO> getData() {
        return data;
    }

    public void setDataX(List<DataDTO> data) {
        this.data = data;
    }


    public static class DataDTO {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }
}
