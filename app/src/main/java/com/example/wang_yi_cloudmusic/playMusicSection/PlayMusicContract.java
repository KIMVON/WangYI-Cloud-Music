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

        void initMusicSource();
    }


    interface View extends BaseView<Presenter>{
//        void isPlayingMusic();

        void playOrPauseMusic();

        void initToolbar();

        void showSingerHearImage(String url);

        void finishActivity();
    }

}
