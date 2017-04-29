package com.example.wang_yi_cloudmusic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.wang_yi_cloudmusic.util.ActivityUtils;

/**
 * Created by 79069 on 2017/4/22.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    protected abstract Fragment createFragment();

    protected abstract T createPresenter(Fragment fragment);

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragment = createFragment();

            ActivityUtils.addFragmentToActivity(fragmentManager , fragment , R.id.fragment_container);
        }


        mPresenter = createPresenter(fragment);

    }


}
