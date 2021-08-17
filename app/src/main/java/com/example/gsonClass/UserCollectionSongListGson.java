package com.example.gsonClass;

import java.util.List;

public class UserCollectionSongListGson {

    private List<PlaylistDTO> playlist;

    public List<PlaylistDTO> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<PlaylistDTO> playlist) {
        this.playlist = playlist;
    }

    public static class PlaylistDTO {
        private CreatorDTO creator;
        private int trackCount;
        private String coverImgUrl;
        private String name;
        private long id;

        public CreatorDTO getCreator() {
            return creator;
        }

        public void setCreator(CreatorDTO creator) {
            this.creator = creator;
        }

        public int getTrackCount() {
            return trackCount;
        }

        public void setTrackCount(int trackCount) {
            this.trackCount = trackCount;
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

        public void setId(Integer id) {
            this.id = id;
        }

        public static class CreatorDTO {
            private String nickname;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
