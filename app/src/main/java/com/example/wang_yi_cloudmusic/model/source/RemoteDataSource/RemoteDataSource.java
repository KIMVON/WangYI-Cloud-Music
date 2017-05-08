package com.example.wang_yi_cloudmusic.model.source.RemoteDataSource;

import android.support.annotation.NonNull;

import com.example.wang_yi_cloudmusic.model.bean.Artist;
import com.example.wang_yi_cloudmusic.model.bean.Music;
import com.example.wang_yi_cloudmusic.model.source.DataSource;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.RetrofitManager;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.Song;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.SongList;
import com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit.gson.SongWord;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by 79069 on 2017/4/27.
 */

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource INSTANCE;

    private final static Map<Integer , Music> MUSICS_SERVICE_DATA;

    static{
        MUSICS_SERVICE_DATA = new LinkedHashMap<>(2);
    }


    private RemoteDataSource() {

    }



    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }

        return INSTANCE;
    }


    /**
     * 通过ID获取歌曲，并解析为music
     * @param id
     * @return
     */
    @Override
    public Observable<Music> getMusic(int id) {
        return RetrofitManager.getApiStores().getSong(id)
                .map(new Func1<Song, Music>() {
                    @Override
                    public Music call(Song song) {
                        Music music = new Music();

                        music.setMusicId(song.getSongs().get(0).getId());
                        music.setCode(song.getCode());

                        List<Artist> artistList = music.getMusicArtists();
                        Artist artist = null;
                        for (int i = 0; i < song.getSongs().get(0).getArtists().size(); i++) {
                            artist = new Artist();
                            artist.setArtistId(song.getSongs().get(0).getArtists().get(i).getId());
                            artist.setArtistImage(song.getSongs().get(0).getArtists().get(i).getImg1v1Url());
                            artist.setArtistName(song.getSongs().get(0).getArtists().get(i).getName());

                            artistList.add(artist);
                        }

//                        music.setMusicArtists(artistList);
                        music.setMusicName(song.getSongs().get(0).getName());
                        music.setMusicURL(song.getSongs().get(0).getMp3Url());

                        return music;
                    }
                });
    }



    @Override
    public Observable<SongList> getSongList(int id) {
        return RetrofitManager.getApiStores().getSongList(id);
    }



    @Override
    public Observable<SongWord> getSongWord(int id) {
        return RetrofitManager.getApiStores().getSongWord(id);
    }



    @Override
    public void saveMusic(@NonNull Music music) {
        MUSICS_SERVICE_DATA.put(music.getMusicId() , music);
    }
}
