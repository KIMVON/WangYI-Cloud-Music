package com.example.wang_yi_cloudmusic.playMusicSection;

import com.example.wang_yi_cloudmusic.BasePresenter;
import com.example.wang_yi_cloudmusic.BaseView;

/**
 * Created by 79069 on 2017/4/22.
 */

public interface PlayMusicContract {
    interface Presenter extends BasePresenter {
        void startPlaying();

        void stopPlaying();


    }


    interface View extends BaseView<Presenter>{


        void finishActivity();
    }

}
