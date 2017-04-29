package com.example.wang_yi_cloudmusic.model.bean;

import android.provider.MediaStore;

import java.util.List;

/**
 * Created by 79069 on 2017/4/29.
 */

public class Music {
    private int musicId;

    private String musicName;

    private List<Artist> musicArtists;

    private String musicURL;

    private int code;

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public List<Artist> getMusicArtists() {
        return musicArtists;
    }

    public void setMusicArtists(List<Artist> musicArtists) {
        this.musicArtists = musicArtists;
    }

    public String getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class Artist{
        private String artistName;

        private int artistId;

        private String artistImage;

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        public int getArtistId() {
            return artistId;
        }

        public void setArtistId(int artistId) {
            this.artistId = artistId;
        }

        public String getArtistImage() {
            return artistImage;
        }

        public void setArtistImage(String artistImage) {
            this.artistImage = artistImage;
        }
    }

}
