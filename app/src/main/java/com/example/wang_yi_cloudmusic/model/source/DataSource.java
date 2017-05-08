package com.example.wang_yi_cloudmusic.model.source;

import com.example.wang_yi_cloudmusic.model.bean.Music;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.Song;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.SongList;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.SongWord;

import rx.Observable;

/**
 * Created by 79069 on 2017/4/22.
 */

public interface DataSource {

    Observable<Music> getMusic(int id);

    Observable<SongList> getSongList(int id);

    Observable<SongWord> getSongWord(int id);

    void saveMusic(Music music);

}
