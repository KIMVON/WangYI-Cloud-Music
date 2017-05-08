package com.example.wang_yi_cloudmusic.model.bean;


import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 79069 on 2017/4/29.
 */

public class Music extends DataSupport {
    private int musicId;

    private String musicName;

    //表的关联，多对多
    private List<Artist> musicArtists = new ArrayList<>();

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


//    public static class Artist{
//
//    }

}
