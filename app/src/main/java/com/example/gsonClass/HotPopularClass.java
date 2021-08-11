package com.example.gsonClass;

import java.util.List;

public class HotPopularClass {

    private List<PlaylistsDTO> playlists;

    public List<PlaylistsDTO> getPlaylists() {
        return playlists;
    }
    public void setPlaylists(List<PlaylistsDTO> playlists) {
        this.playlists = playlists;
    }
    public static class PlaylistsDTO {
        private String name;
        private String coverImgUrl;
        private long id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }
        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }
        public long getId(){return id;}
        public void setId(long id){
            this.id = id;
        }
    }
}
