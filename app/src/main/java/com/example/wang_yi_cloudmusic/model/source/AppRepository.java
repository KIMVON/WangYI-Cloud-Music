package com.example.wang_yi_cloudmusic.model.source;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.Toast;

import com.example.wang_yi_cloudmusic.app.MyApplication;
import com.example.wang_yi_cloudmusic.model.bean.Music;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.SongList;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.SongWord;

import static android.content.ContentValues.TAG;
import static com.google.common.base.Preconditions.checkNotNull;


import rx.Observable;
import rx.functions.Action1;

/**
 * Created by 79069 on 2017/4/29.
 */

public class AppRepository implements DataSource {
    //缓存最大内存大小
    private static final int MAX_SIZE = (int) (Runtime.getRuntime().maxMemory() / 8);

    private static AppRepository INSTANCE;

    private final DataSource mLocalDataSource;

    private final DataSource mRemoteDataSource;


    //缓存
    @Nullable
    private LruCache<Integer, Music> mCache;


    private AppRepository(DataSource LocalDataSource, DataSource RemoteDataSource) {
        checkNotNull(LocalDataSource, "AppRepository LocalDataSource获取失败为null");
        checkNotNull(RemoteDataSource, "AppRepository RemoteDataSource获取失败为null");

        mLocalDataSource = LocalDataSource;

        mRemoteDataSource = RemoteDataSource;

        mCache = new LruCache<>(MAX_SIZE);

    }


    public static AppRepository getInstance(DataSource LocalDataSource,
                                            DataSource RemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new AppRepository(LocalDataSource, RemoteDataSource);
        }

        return INSTANCE;
    }


    /**
     * 获取Music，三层缓存
     * @param id
     * @return
     */
    @Override
    public Observable<Music> getMusic(int id) {
        /**
         * 测试
         */
        if (mCache.get(id) != null) {
            Log.d(TAG, "getMusic: cache");
            return Observable.just(mCache.get(id));
        } else {
            Observable<Music> localMusic = getAndSaveLocalMusic(id);
            Observable<Music> remoteMusic = getAndSaveRemoteMusic(id);

            return Observable
                    .concat(localMusic, remoteMusic)
                    .first();
        }

    }



    /**
     * 从本地数据库获取Music，并且存入缓存
     *
     * @param id
     * @return
     */
    private Observable<Music> getAndSaveLocalMusic(@NonNull int id) {
        return mLocalDataSource
                .getMusic(id)
                .doOnNext(new Action1<Music>() {
                    @Override
                    public void call(Music music) {
                        mCache.put(music.getMusicId(), music);
                    }
                });
    }



    /**
     * 从网络上获取Music，并且存入缓存
     *
     * @param id
     * @return
     */
    private Observable<Music> getAndSaveRemoteMusic(@NonNull int id) {
        return mRemoteDataSource
                .getMusic(id)
                .doOnNext(new Action1<Music>() {
                    @Override
                    public void call(Music music) {
                        saveMusic(music);
                    }
                });
    }



    @Override
    public Observable<SongList> getSongList(int id) {
        return null;
    }



    @Override
    public Observable<SongWord> getSongWord(int id) {
        return null;
    }



    @Override
    public void saveMusic(Music music) {
        checkNotNull(music, "AppRepository music为空");

        mLocalDataSource.saveMusic(music);
        /**
         * 缓存
         */
        if (mCache != null) {
            mCache.put(music.getMusicId(), music);
        } else {
            Log.d(TAG, "saveMusic: mCache为空");
            mCache = new LruCache<>(MAX_SIZE);
            mCache.put(music.getMusicId(), music);
        }
    }
}
