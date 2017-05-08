package com.example.wang_yi_cloudmusic.launchSection;

import com.example.wang_yi_cloudmusic.BasePresenter;
import com.example.wang_yi_cloudmusic.BaseView;

/**
 * Created by 79069 on 2017/4/29.
 */

public interface LaunchContract {
    interface Presenter extends BasePresenter{


    }

    interface View extends BaseView<Presenter>{
        void finishActivity();

    }
}
