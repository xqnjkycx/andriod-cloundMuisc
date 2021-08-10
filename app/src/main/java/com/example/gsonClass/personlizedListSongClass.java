package com.example.gsonClass;

import java.util.List;

public class personlizedListSongClass {

    private Boolean hasTaste;
    private Integer code;
    private Integer category;
    private List<ResultDTO> result;

    public Boolean getHasTaste() {
        return hasTaste;
    }

    public void setHasTaste(Boolean hasTaste) {
        this.hasTaste = hasTaste;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public List<ResultDTO> getResult() {
        return result;
    }

    public void setResult(List<ResultDTO> result) {
        this.result = result;
    }

    public static class ResultDTO {
        private Long id;
        private String name;
        private String copywriter;
        private String picUrl;
        private Integer playCount;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCopywriter() {
            return copywriter;
        }

        public void setCopywriter(String copywriter) {
            this.copywriter = copywriter;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public Integer getPlayCount() {
            return playCount;
        }

        public void setPlayCount(Integer playCount) {
            this.playCount = playCount;
        }
    }
}
