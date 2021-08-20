package com.example.gsonClass;

import java.util.List;

public class songDt {

    private List<SongsDTO> songs;
    private Integer code;

    public List<SongsDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongsDTO> songs) {
        this.songs = songs;
    }


    public static class SongsDTO {
        private long dt;

        public long getDt() {
            return dt;
        }

        public void setDt(long dt) {
            this.dt = dt;
        }
    }
}
