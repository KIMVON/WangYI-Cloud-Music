package com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit;

import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.Song;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.SongList;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.SongWord;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 79069 on 2017/4/27.
 */

public interface ApiStores {
    /**
     * 获取歌词
     * @param id
     * @return
     */
    @GET("api/song/lyric?os=pc&lv=-1&kv=-1&tv=-1")
    Observable<SongWord> getSongWord(@Query("id")int id);

    /**
     * 获取歌单
     * @param id
     * @return
     */
    @GET("api/playlist/detail?&updateTime=-1")
    Observable<SongList> getSongList(@Query("id")int id);


    /**
     * 获取歌曲
     * @param id
     * @return
     */
    @GET("api/song/detail/?ids=%5B28377211%5D")
    Observable<Song> getSong(@Query("id")int id);


}
