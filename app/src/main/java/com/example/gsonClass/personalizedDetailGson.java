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
            private Integer id;
            private Integer pst;
            private Integer t;
            private List<ArDTO> ar;
            private List<?> alia;
            private Integer pop;
            private Integer st;
            private String rt;
            private Integer fee;
            private Integer v;
            private Object crbt;
            private String cf;
            private AlDTO al;
            private Integer dt;
            private HDTO h;
            private MDTO m;
            private LDTO l;
            private Object a;
            private String cd;
            private Integer no;
            private Object rtUrl;
            private Integer ftype;
            private List<?> rtUrls;
            private Integer djId;
            private Integer copyright;
            private Integer s_id;
            private Integer mark;
            private Integer originCoverType;
            private Object originSongSimpleData;
            private Integer single;
            private Object noCopyrightRcmd;
            private Integer cp;
            private Integer mv;
            private Integer rtype;
            private Object rurl;
            private Integer mst;


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

            public Integer getPst() {
                return pst;
            }

            public void setPst(Integer pst) {
                this.pst = pst;
            }

            public Integer getT() {
                return t;
            }

            public void setT(Integer t) {
                this.t = t;
            }

            public List<ArDTO> getAr() {
                return ar;
            }

            public void setAr(List<ArDTO> ar) {
                this.ar = ar;
            }

            public List<?> getAlia() {
                return alia;
            }

            public void setAlia(List<?> alia) {
                this.alia = alia;
            }

            public Integer getPop() {
                return pop;
            }

            public void setPop(Integer pop) {
                this.pop = pop;
            }

            public Integer getSt() {
                return st;
            }

            public void setSt(Integer st) {
                this.st = st;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public Integer getFee() {
                return fee;
            }

            public void setFee(Integer fee) {
                this.fee = fee;
            }

            public Integer getV() {
                return v;
            }

            public void setV(Integer v) {
                this.v = v;
            }

            public Object getCrbt() {
                return crbt;
            }

            public void setCrbt(Object crbt) {
                this.crbt = crbt;
            }

            public String getCf() {
                return cf;
            }

            public void setCf(String cf) {
                this.cf = cf;
            }

            public AlDTO getAl() {
                return al;
            }

            public void setAl(AlDTO al) {
                this.al = al;
            }

            public Integer getDt() {
                return dt;
            }

            public void setDt(Integer dt) {
                this.dt = dt;
            }

            public HDTO getH() {
                return h;
            }

            public void setH(HDTO h) {
                this.h = h;
            }

            public MDTO getM() {
                return m;
            }

            public void setM(MDTO m) {
                this.m = m;
            }

            public LDTO getL() {
                return l;
            }

            public void setL(LDTO l) {
                this.l = l;
            }

            public Object getA() {
                return a;
            }

            public void setA(Object a) {
                this.a = a;
            }

            public String getCd() {
                return cd;
            }

            public void setCd(String cd) {
                this.cd = cd;
            }

            public Integer getNo() {
                return no;
            }

            public void setNo(Integer no) {
                this.no = no;
            }

            public Object getRtUrl() {
                return rtUrl;
            }

            public void setRtUrl(Object rtUrl) {
                this.rtUrl = rtUrl;
            }

            public Integer getFtype() {
                return ftype;
            }

            public void setFtype(Integer ftype) {
                this.ftype = ftype;
            }

            public List<?> getRtUrls() {
                return rtUrls;
            }

            public void setRtUrls(List<?> rtUrls) {
                this.rtUrls = rtUrls;
            }

            public Integer getDjId() {
                return djId;
            }

            public void setDjId(Integer djId) {
                this.djId = djId;
            }

            public Integer getCopyright() {
                return copyright;
            }

            public void setCopyright(Integer copyright) {
                this.copyright = copyright;
            }

            public Integer getS_id() {
                return s_id;
            }

            public void setS_id(Integer s_id) {
                this.s_id = s_id;
            }

            public Integer getMark() {
                return mark;
            }

            public void setMark(Integer mark) {
                this.mark = mark;
            }

            public Integer getOriginCoverType() {
                return originCoverType;
            }

            public void setOriginCoverType(Integer originCoverType) {
                this.originCoverType = originCoverType;
            }

            public Object getOriginSongSimpleData() {
                return originSongSimpleData;
            }

            public void setOriginSongSimpleData(Object originSongSimpleData) {
                this.originSongSimpleData = originSongSimpleData;
            }

            public Integer getSingle() {
                return single;
            }

            public void setSingle(Integer single) {
                this.single = single;
            }

            public Object getNoCopyrightRcmd() {
                return noCopyrightRcmd;
            }

            public void setNoCopyrightRcmd(Object noCopyrightRcmd) {
                this.noCopyrightRcmd = noCopyrightRcmd;
            }

            public Integer getCp() {
                return cp;
            }

            public void setCp(Integer cp) {
                this.cp = cp;
            }

            public Integer getMv() {
                return mv;
            }

            public void setMv(Integer mv) {
                this.mv = mv;
            }

            public Integer getRtype() {
                return rtype;
            }

            public void setRtype(Integer rtype) {
                this.rtype = rtype;
            }

            public Object getRurl() {
                return rurl;
            }

            public void setRurl(Object rurl) {
                this.rurl = rurl;
            }

            public Integer getMst() {
                return mst;
            }

            public void setMst(Integer mst) {
                this.mst = mst;
            }
            public static class AlDTO {
                private Integer id;
                private String name;
                private String picUrl;
                private List<?> tns;
                private String pic_str;
                private Long pic;

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

            public static class HDTO {
                private Integer br;
                private Integer fid;
                private Integer size;
                private Integer vd;

                public Integer getBr() {
                    return br;
                }

                public void setBr(Integer br) {
                    this.br = br;
                }

                public Integer getFid() {
                    return fid;
                }

                public void setFid(Integer fid) {
                    this.fid = fid;
                }

                public Integer getSize() {
                    return size;
                }

                public void setSize(Integer size) {
                    this.size = size;
                }

                public Integer getVd() {
                    return vd;
                }

                public void setVd(Integer vd) {
                    this.vd = vd;
                }
            }

            public static class MDTO {
                private Integer br;
                private Integer fid;
                private Integer size;
                private Integer vd;

                public Integer getBr() {
                    return br;
                }

                public void setBr(Integer br) {
                    this.br = br;
                }

                public Integer getFid() {
                    return fid;
                }

                public void setFid(Integer fid) {
                    this.fid = fid;
                }

                public Integer getSize() {
                    return size;
                }

                public void setSize(Integer size) {
                    this.size = size;
                }

                public Integer getVd() {
                    return vd;
                }

                public void setVd(Integer vd) {
                    this.vd = vd;
                }
            }

            public static class LDTO {
                private Integer br;
                private Integer fid;
                private Integer size;
                private Integer vd;

                public Integer getBr() {
                    return br;
                }

                public void setBr(Integer br) {
                    this.br = br;
                }

                public Integer getFid() {
                    return fid;
                }

                public void setFid(Integer fid) {
                    this.fid = fid;
                }

                public Integer getSize() {
                    return size;
                }

                public void setSize(Integer size) {
                    this.size = size;
                }

                public Integer getVd() {
                    return vd;
                }

                public void setVd(Integer vd) {
                    this.vd = vd;
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
