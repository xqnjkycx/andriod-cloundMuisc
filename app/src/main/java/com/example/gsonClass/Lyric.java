package com.example.gsonClass;

public class Lyric {
    private LrcDTO lrc;

    public LrcDTO getLrc() {
        return lrc;
    }

    public void setLrc(LrcDTO lrc) {
        this.lrc = lrc;
    }

    public static class LrcDTO {
        private Integer version;
        private String lyric;

        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }

        public String getLyric() {
            return lyric;
        }

        public void setLyric(String lyric) {
            this.lyric = lyric;
        }
    }
}
