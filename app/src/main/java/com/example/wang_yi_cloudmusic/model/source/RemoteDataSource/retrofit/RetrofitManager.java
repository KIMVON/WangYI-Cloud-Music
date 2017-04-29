package com.example.wang_yi_cloudmusic.model.source.RemoteDataSource.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 79069 on 2017/4/27.
 */

public class RetrofitManager {
    private static final String BASE_URL = "http://music.163.com/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    private static final ApiStores apiStores = retrofit.create(ApiStores.class);

    public static ApiStores getApiStores() {
        return apiStores;
    }
}
