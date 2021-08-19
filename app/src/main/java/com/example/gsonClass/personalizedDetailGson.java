package com.example.gsonClass;

import java.util.List;

public class personalizedDetailGson {
    private PlaylistDTO playlist;
    public PlaylistDTO getPlaylist() {
        return playlist;
    }
    public void setPlaylist(PlaylistDTO playlist) {
        this.playlist = playlist;
    }
    public static class PlaylistDTO {
        private Long id;
        private String name;
        private String coverImgUrl;
        private List<TracksDTO> tracks;

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



        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }



        public List<TracksDTO> getTracks() {
            return tracks;
        }

        public void setTracks(List<TracksDTO> tracks) {
            this.tracks = tracks;
        }

        public static class TracksDTO {
            private String name;
            private Integer id;
            private List<ArDTO> ar;
            private AlDTO al;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public List<ArDTO> getAr() {
                return ar;
            }

            public void setAr(List<ArDTO> ar) {
                this.ar = ar;
            }

            public AlDTO getAl() {
                return al;
            }

            public void setAl(AlDTO al) {
                this.al = al;
            }

            public static class AlDTO {
                private Integer id;
                private String name;
                private String picUrl;

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

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
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
