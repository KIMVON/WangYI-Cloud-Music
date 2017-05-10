package com.example.wang_yi_cloudmusic.playMusicSection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.MainThread;
import android.util.Log;

import com.example.wang_yi_cloudmusic.app.AppService;
import com.example.wang_yi_cloudmusic.app.MyApplication;
import com.example.wang_yi_cloudmusic.model.bean.Music;
import com.example.wang_yi_cloudmusic.model.source.AppRepository;
import com.example.wang_yi_cloudmusic.playVideoSection.PlayVideoContract;
import com.example.wang_yi_cloudmusic.util.AppTransformerUtil;
import com.google.common.eventbus.Subscribe;
import com.google.common.util.concurrent.AbstractScheduledService;

import java.util.Timer;
import java.util.TimerTask;

import static com.google.common.base.Preconditions.checkNotNull;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by 79069 on 2017/4/30.
 */

public class PlayMusicPresenter implements PlayMusicContract.Presenter {
    private static final int UPDATE_PROGRESS_SCHEDULE = 0;

    private static final int MAX_PROGRESS_SCHEDULE = 100;

    private AppRepository mAppRepository;

    private PlayMusicContract.View mFragment;

    private AppService mService;

    private Context mContext;

    private Timer mTimer;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == UPDATE_PROGRESS_SCHEDULE) {
                mFragment.setProgressSchedule(mService.getMusicProgressSchedule());
                mFragment.setMusicCurrentTime(mService.getCurrentTime());
            }

            if (mService.getCurrentTime() >= mService.getMusicAllTime()) {
                if (mService.getMusicProgressSchedule() == MAX_PROGRESS_SCHEDULE) {
                    mFragment.endDiskNeedleAnimation();
                    mFragment.switchBtnIcon(mService.isPlaying());
                    mFragment.setIsPlayingBoolean();
                    mTimer.cancel();
                }
            }
        }
    };

    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (mService.isPlaying()) {
                mHandler.sendEmptyMessage(UPDATE_PROGRESS_SCHEDULE);
            }
        }
    };


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = (AppService) ((AppService.MusicBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    public PlayMusicPresenter(AppRepository appRepository, PlayMusicContract.View fragment) {
        checkNotNull(appRepository, "PlayMusicPresenter appRepository为空");
        checkNotNull(fragment, "PlayMusicPresenter View为空");

        mAppRepository = appRepository;

        mFragment = fragment;

        mFragment.setPresenter(this);

        mContext = MyApplication.getContext();

        mTimer = new Timer();


        //启动Service
        Intent intent = AppService.newIntent(mContext);
        mContext.startService(intent);
        Log.d(TAG, "PlayMusicPresenter: startService");
        mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }


    /**
     * 开始或者播放Music
     */
    @Override
    public void startOrPausePlaying() {
        mService.playOrPauseMusic();
    }


    /**
     * 跳转到手指拖拽到的地方
     *
     * @param msec
     */
    @Override
    public void skipProgress(int msec) {
        //求出占了总长度的多少
        int pos = msec * mService.getMusicAllTime() / MAX_PROGRESS_SCHEDULE;
        mService.skipAppointProgress(pos);
    }


    /**
     * 初始化Music的URL
     */
    @Override
    public void initMusicSource() {
        int id = 28377211;
        mAppRepository.getMusic(id)
                .compose(new AppTransformerUtil.IOMainThreadTransformer())
                .subscribe(new Subscriber<Music>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: 运行startOrPausePlaying()");

                        mFragment.setMusicAllTime(mService.getMusicAllTime());

                        mTimer.schedule(mTimerTask, 0, 1000);

                        startOrPausePlaying();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Music music) {
                        Log.d(TAG, "onNext: " + music.getMusicURL());
                        mService.setMediaPlayerDataSource(music.getMusicURL());

                        mFragment.showSingerHearImage(music.getMusicArtists().get(0).getArtistImage());
                    }
                });
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
