package com.example.wang_yi_cloudmusic.playMusicSection;

import com.example.wang_yi_cloudmusic.BasePresenter;
import com.example.wang_yi_cloudmusic.BaseView;
import com.example.wang_yi_cloudmusic.model.bean.Music;

/**
 * Created by 79069 on 2017/4/22.
 */

public interface PlayMusicContract {
    interface Presenter extends BasePresenter {
        void startOrPausePlaying();

        void skipProgress(int msec);

        void initMusicSource();
    }


    interface View extends BaseView<Presenter>{

        void playOrPauseMusic();

        void startDiskNeedleAnimation();

        void endDiskNeedleAnimation();

        void initToolbar();

        void showSingerHearImage(String url);

        void setMusicCurrentTime(int time);

        void setMusicAllTime(int time);

        void setProgressSchedule(int schedule);

        void switchBtnIcon(boolean isPlaying);

        void setIsPlayingBoolean();

        void finishActivity();
    }

}
