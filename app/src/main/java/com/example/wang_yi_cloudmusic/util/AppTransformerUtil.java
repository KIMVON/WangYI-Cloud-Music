package com.example.wang_yi_cloudmusic.util;

import android.support.annotation.MainThread;

import com.example.wang_yi_cloudmusic.model.bean.Music;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 79069 on 2017/5/8.
 */

public class AppTransformerUtil {
    /**
     * 转线程
     */
    public static class IOMainThreadTransformer implements Observable.Transformer<Music , Music>{

        @Override
        public Observable<Music> call(Observable<Music> musicObservable) {
            return musicObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }
}
