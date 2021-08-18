package com.example.gsonClass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RankGson {

    private Integer code;
    private List<ListDTO> list;

    public List<ListDTO> getList() {
        return list;
    }

    public void setList(List<ListDTO> list) {
        this.list = list;
    }

    public static class ListDTO {
        private String updateFrequency;
        private String description;
        private String coverImgUrl;
        private String name;
        private long id;

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
}
