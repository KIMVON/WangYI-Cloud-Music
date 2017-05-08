package com.example.wang_yi_cloudmusic.model.bean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 79069 on 2017/5/1.
 */

public class Artist extends DataSupport {
    private String artistName;

    private int artistId;

    private String artistImage;

    //表的关联，多对多
    private List<Music> musicList = new ArrayList<>();

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

    public List<Music> getMusicList() {


        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }
}
