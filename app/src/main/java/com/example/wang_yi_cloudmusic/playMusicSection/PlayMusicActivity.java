package com.example.wang_yi_cloudmusic.playMusicSection;

import android.support.v4.app.Fragment;

import com.example.wang_yi_cloudmusic.BaseActivity;
import com.example.wang_yi_cloudmusic.BasePresenter;
import com.example.wang_yi_cloudmusic.util.ActivityUtils;

/**
 * Created by 79069 on 2017/4/30.
 */

public class PlayMusicActivity extends BaseActivity<PlayMusicPresenter> {

    @Override
    protected Fragment createFragment() {
        return PlayMusicFragment.newInstance();
    }

    @Override
    protected PlayMusicPresenter createPresenter(Fragment fragment) {
        return new PlayMusicPresenter(ActivityUtils.getAppRepository() , (PlayMusicContract.View) fragment);
    }
}
