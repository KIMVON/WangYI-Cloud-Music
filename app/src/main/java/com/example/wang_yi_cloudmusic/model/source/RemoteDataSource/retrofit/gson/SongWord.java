package com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson;

/**
 * Created by 79069 on 2017/4/27.
 */

public class SongWord {
    private boolean sgc;

    private boolean sfy;

    private boolean qfy;

    private Lrc lrc;

    private Lrc klyric;

    private Lrc tlyric;

    private int code;

    public boolean isSgc() {
        return sgc;
    }

    public void setSgc(boolean sgc) {
        this.sgc = sgc;
    }

    public boolean isSfy() {
        return sfy;
    }

    public void setSfy(boolean sfy) {
        this.sfy = sfy;
    }

    public boolean isQfy() {
        return qfy;
    }

    public void setQfy(boolean qfy) {
        this.qfy = qfy;
    }

    public Lrc getLrc() {
        return lrc;
    }

    public void setLrc(Lrc lrc) {
        this.lrc = lrc;
    }

    public Lrc getKlyric() {
        return klyric;
    }

    public void setKlyric(Lrc klyric) {
        this.klyric = klyric;
    }

    public Lrc getTlyric() {
        return tlyric;
    }

    public void setTlyric(Lrc tlyric) {
        this.tlyric = tlyric;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private class Lrc {
        private int version;

        private String lyric;

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
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
