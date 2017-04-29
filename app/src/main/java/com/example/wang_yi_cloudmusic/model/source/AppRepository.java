package com.example.wang_yi_cloudmusic.model.source;

import com.example.wang_yi_cloudmusic.model.bean.Music;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.Song;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.SongList;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.SongWord;

import static com.google.common.base.Preconditions.checkNotNull;


import rx.Observable;

/**
 * Created by 79069 on 2017/4/29.
 */

public class AppRepository implements DataSource {
    private static AppRepository INSTANCE;

    private final DataSource mLocalDataSource;

    private final DataSource mRemoteDataSource;



    private AppRepository(DataSource LocalDataSource , DataSource RemoteDataSource){
        checkNotNull(LocalDataSource , "AppRepository LocalDataSource获取失败为null");
        checkNotNull(RemoteDataSource , "AppRepository RemoteDataSource获取失败为null");

        mLocalDataSource = LocalDataSource;

        mRemoteDataSource = RemoteDataSource;
    }

    public static AppRepository getInstance(DataSource LocalDataSource ,
                                            DataSource RemoteDataSource){
        if (INSTANCE == null){
            INSTANCE = new AppRepository(LocalDataSource , RemoteDataSource);
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
