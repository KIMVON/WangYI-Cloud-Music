package com.example.wang_yi_cloudmusic.model.source.LocalDataSource;

import com.example.wang_yi_cloudmusic.model.bean.Music;
import com.example.wang_yi_cloudmusic.model.source.DataSource;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.Song;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.SongList;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.SongWord;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 79069 on 2017/4/29.
 */

public class LocalDataSource implements DataSource {
    private static LocalDataSource INSTANCE;

    private LocalDataSource() {
        LitePal.getDatabase();
    }

    public static LocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource();
        }

        return INSTANCE;
    }


    /**
     * 从本地数据库获取Music
     *
     * @param id
     * @return
     */
    @Override
    public Observable<Music> getMusic(final int id) {
        return Observable.create(new Observable.OnSubscribe<Music>() {
            @Override
            public void call(Subscriber<? super Music> subscriber) {
                List<Music> musicList = DataSupport.where("id=?", id + "").find(Music.class);

                if (musicList.size() > 0) {//判断数据库里面是否有目标，没有返回空,有就next
                    subscriber.onNext(musicList.get(0));
                }else {
                    subscriber.onCompleted();
                }
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


    /**
     * 本地存储Music
     *
     * @param music
     */
    @Override
    public void saveMusic(Music music) {
        checkNotNull(music, "本地存储音乐，音乐为Null");

        music.save();
    }
}
