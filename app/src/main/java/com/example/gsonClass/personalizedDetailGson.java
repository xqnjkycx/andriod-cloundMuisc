package com.example.gsonClass;

import java.util.List;

public class personalizedDetailGson {

    private Integer code;
    private Object relatedVideos;
    private PlaylistDTO playlist;
    private Object urls;
    private Object sharedPrivilege;
    public PlaylistDTO getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistDTO playlist) {
        this.playlist = playlist;
    }

    public Object getUrls() {
        return urls;
    }

    public void setUrls(Object urls) {
        this.urls = urls;
    }


    public Object getSharedPrivilege() {
        return sharedPrivilege;
    }

    public void setSharedPrivilege(Object sharedPrivilege) {
        this.sharedPrivilege = sharedPrivilege;
    }

    public static class PlaylistDTO {

        private List<TracksDTO> tracks;


        public List<TracksDTO> getTracks() {
            return tracks;
        }

        public void setTracks(List<TracksDTO> tracks) {
            this.tracks = tracks;
        }
        public static class TracksDTO {
            private String name;
            private List<ArDTO> ar;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ArDTO> getAr() {
                return ar;
            }

            public void setAr(List<ArDTO> ar) {
                this.ar = ar;
            }

            public static class AlDTO {
                private long id;
                private String name;
                private String picUrl;
                private List<?> tns;
                private String pic_str;
                private Long pic;

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public List<?> getTns() {
                    return tns;
                }

                public void setTns(List<?> tns) {
                    this.tns = tns;
                }

                public String getPic_str() {
                    return pic_str;
                }

                public void setPic_str(String pic_str) {
                    this.pic_str = pic_str;
                }

                public Long getPic() {
                    return pic;
                }

                public void setPic(Long pic) {
                    this.pic = pic;
                }
            }

            public static class ArDTO {
                private Integer id;
                private String name;
                private List<?> tns;
                private List<?> alias;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<?> getTns() {
                    return tns;
                }

                public void setTns(List<?> tns) {
                    this.tns = tns;
                }

                public List<?> getAlias() {
                    return alias;
                }

                public void setAlias(List<?> alias) {
                    this.alias = alias;
                }
            }
        }
    }

}
