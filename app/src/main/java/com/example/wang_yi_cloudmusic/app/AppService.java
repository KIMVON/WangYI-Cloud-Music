package com.example.wang_yi_cloudmusic.app;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * Created by 79069 on 2017/4/29.
 */

public class AppService extends Service {

    public static Intent newIntent(Context context) {

        Intent intent = new Intent(context, AppService.class);

        return intent;
    }


    private static final int MAX_PROGRESS = 100;

    private static MediaPlayer sMediaPlayer;

    //初始化Binder
    private MusicBinder mMusicBinder = new MusicBinder();


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initMediaPlayer();

        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 初始化MediaPlayer
     */
    public void initMediaPlayer() {
        if (sMediaPlayer == null) {
            sMediaPlayer = new MediaPlayer();
            // 设置媒体流类型
            sMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }

    }

    public void setMediaPlayerDataSource(String url) {
        try {
            sMediaPlayer.reset();
            //设置数据源
            sMediaPlayer.setDataSource(url);

            //prepare自动播放
            sMediaPlayer.prepare();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    /**
     * 开始或继续播放
     */
    private void play() {
        sMediaPlayer.start();
    }

    /**
     * 暂停播放
     */
    private void pause() {
        sMediaPlayer.pause();
    }


    /**
     * 开始或暂停播放
     */
    public void playOrPauseMusic() {
        if (sMediaPlayer.isPlaying()) {
            Log.d(TAG, "playOrPauseMusic: pause");
            pause();
        } else {
            Log.d(TAG, "playOrPauseMusic: play");
            play();
        }
    }

    /**
     * 关闭播放，并释放资源
     */
    public void stop() {
        if (sMediaPlayer != null) {

            sMediaPlayer.stop();
            sMediaPlayer.release();

            sMediaPlayer = null;
        }
    }

    /**
     * 获取当前进度时间
     * @return
     */
    public int getCurrentTime() {
        if (sMediaPlayer != null) {
            return sMediaPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }

    /**
     * 获取音乐总时间
     *
     * @return
     */
    public int getMusicAllTime() {
        if (sMediaPlayer != null) {
            return sMediaPlayer.getDuration();
        } else {
            return -1;
        }
    }

    /**
     * 获取音乐进度
     *
     * @return
     */
    public int getMusicProgressSchedule() {
        int position = sMediaPlayer.getCurrentPosition();
        int duration = sMediaPlayer.getDuration();
        int pos = 0;
        if (duration > 0) {
            pos = position * MAX_PROGRESS / duration;
        }

        return pos;
    }

    /**
     * 跳转到指定的地方，通过拖拽
     * @param progress
     */
    public void skipAppointProgress(int progress){
        sMediaPlayer.seekTo(progress);
    }

    /**
     * 是否正在播放
     *
     * @return
     */
    public boolean isPlaying() {
        return sMediaPlayer.isPlaying();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMusicBinder;
    }


    public class MusicBinder extends Binder {
        //返回Service
        public Service getService() {
            return AppService.this;
        }

    }
}
