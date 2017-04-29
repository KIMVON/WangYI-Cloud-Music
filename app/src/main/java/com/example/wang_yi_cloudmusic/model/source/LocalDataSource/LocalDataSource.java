package com.example.wang_yi_cloudmusic.model.source.LocalDataSource;

import com.example.wang_yi_cloudmusic.model.bean.Music;
import com.example.wang_yi_cloudmusic.model.source.DataSource;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.Song;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.SongList;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.SongWord;

import rx.Observable;

/**
 * Created by 79069 on 2017/4/29.
 */

public class LocalDataSource implements DataSource {
    private static LocalDataSource INSTANCE;

    private LocalDataSource(){

    }

    public static LocalDataSource getInstance(){
        if (INSTANCE == null){
            INSTANCE = new LocalDataSource();
        }

        return INSTANCE;
    }


    @Override
    public Observable<Music> getSong(String id) {
        return null;
    }

    @Override
    public Observable<SongList> getSongList(String id) {
        return null;
    }

    @Override
    public Observable<SongWord> getSongWord(String id) {
        return null;
    }
}
