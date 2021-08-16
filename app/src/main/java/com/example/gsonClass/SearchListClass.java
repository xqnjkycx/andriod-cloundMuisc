package com.example.gsonClass;

import java.util.List;

public class SearchListClass {

    private ResultDTO result;
    private Integer code;

    public ResultDTO getResult() {
        return result;
    }

    public void setResult(ResultDTO result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static class ResultDTO {
        private List<SongsDTO> songs;
        private Boolean hasMore;
        private Integer songCount;

        public List<SongsDTO> getSongs() {
            return songs;
        }

        public void setSongs(List<SongsDTO> songs) {
            this.songs = songs;
        }

        public Boolean getHasMore() {
            return hasMore;
        }

        public void setHasMore(Boolean hasMore) {
            this.hasMore = hasMore;
        }

        public Integer getSongCount() {
            return songCount;
        }

        public void setSongCount(Integer songCount) {
            this.songCount = songCount;
        }

        public static class SongsDTO {
            private long id;
            private String name;
            private List<ArtistsDTO> artists;

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

            public List<ArtistsDTO> getArtists() {
                return artists;
            }

            public void setArtists(List<ArtistsDTO> artists) {
                this.artists = artists;
            }

            public static class AlbumDTO {
                public static class ArtistDTO {
                    private Integer id;
                    private String name;
                    private Object picUrl;
                    private List<?> alias;
                    private Integer albumSize;
                    private Integer picId;
                    private String img1v1Url;
                    private Integer img1v1;
                    private Object trans;

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

                    public Object getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(Object picUrl) {
                        this.picUrl = picUrl;
                    }

                    public List<?> getAlias() {
                        return alias;
                    }

                    public void setAlias(List<?> alias) {
                        this.alias = alias;
                    }

                    public Integer getAlbumSize() {
                        return albumSize;
                    }

                    public void setAlbumSize(Integer albumSize) {
                        this.albumSize = albumSize;
                    }

                    public Integer getPicId() {
                        return picId;
                    }

                    public void setPicId(Integer picId) {
                        this.picId = picId;
                    }

                    public String getImg1v1Url() {
                        return img1v1Url;
                    }

                    public void setImg1v1Url(String img1v1Url) {
                        this.img1v1Url = img1v1Url;
                    }

                    public Integer getImg1v1() {
                        return img1v1;
                    }

                    public void setImg1v1(Integer img1v1) {
                        this.img1v1 = img1v1;
                    }

                    public Object getTrans() {
                        return trans;
                    }

                    public void setTrans(Object trans) {
                        this.trans = trans;
                    }
                }
            }

            public static class ArtistsDTO {
                private Integer id;
                private String name;
                private Object picUrl;
                private List<?> alias;
                private Integer albumSize;
                private Integer picId;
                private String img1v1Url;
                private Integer img1v1;
                private Object trans;

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

                public Object getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(Object picUrl) {
                    this.picUrl = picUrl;
                }

                public List<?> getAlias() {
                    return alias;
                }

                public void setAlias(List<?> alias) {
                    this.alias = alias;
                }

                public Integer getAlbumSize() {
                    return albumSize;
                }

                public void setAlbumSize(Integer albumSize) {
                    this.albumSize = albumSize;
                }

                public Integer getPicId() {
                    return picId;
                }

                public void setPicId(Integer picId) {
                    this.picId = picId;
                }

                public String getImg1v1Url() {
                    return img1v1Url;
                }

                public void setImg1v1Url(String img1v1Url) {
                    this.img1v1Url = img1v1Url;
                }

                public Integer getImg1v1() {
                    return img1v1;
                }

                public void setImg1v1(Integer img1v1) {
                    this.img1v1 = img1v1;
                }

                public Object getTrans() {
                    return trans;
                }

                public void setTrans(Object trans) {
                    this.trans = trans;
                }
            }
        }
    }
}
