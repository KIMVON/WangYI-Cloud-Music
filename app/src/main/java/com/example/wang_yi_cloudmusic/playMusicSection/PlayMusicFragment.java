package com.example.wang_yi_cloudmusic.playMusicSection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wang_yi_cloudmusic.R;
import com.example.wang_yi_cloudmusic.app.AppService;
import com.example.wang_yi_cloudmusic.app.MyApplication;
import com.example.wang_yi_cloudmusic.model.bean.Music;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

/**
 * Created by 79069 on 2017/4/30.
 * 调用了Buffer Knife
 */

public class PlayMusicFragment extends Fragment implements PlayMusicContract.View {
    public static Fragment newInstance() {
        Fragment fragment = new PlayMusicFragment();

        return fragment;
    }


    @InjectView(R.id.background_play_music)
    ImageView mMusicBackground;
    @InjectView(R.id.music_disk)
    FrameLayout mMusicDisk;
    @InjectView(R.id.my_love_btn)
    ImageView mMyLoveBtn;
    @InjectView(R.id.download_music_btn)
    ImageView mDownloadMusicBtn;
    @InjectView(R.id.music_comment_btn)
    ImageView mMusicCommentBtn;
    @InjectView(R.id.more_btn)
    ImageView mMoreBtn;
    @InjectView(R.id.order_play_btn)
    ImageView mOrderPlayBtn;
    @InjectView(R.id.previous_music_btn)
    ImageView mPreviousMusicBtn;
    @InjectView(R.id.play_pause_btn)
    ImageView mPlayPauseBtn;
    @InjectView(R.id.next_music_btn)
    ImageView mNextMusicBtn;
    @InjectView(R.id.music_list_btn)
    ImageView mMusicListBtn;
    @InjectView(R.id.singer_image)
    CircleImageView mSingerImage;


    private PlayMusicContract.Presenter mPresenter;

    //判断是否在播放,用于显示播放按钮还是暂停按钮
    private boolean isPlaying = false;

    //判断是否重新初始化Music Source
    private boolean isInitMusicSource = true;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_music, container, false);

        ButterKnife.inject(this, view);

        initToolbar();

        return view;
    }


    @Override
    public void playOrPauseMusic() {
        //切换按钮图标
        if (isPlaying) {//暂停播放，显示开始播放图片
            mPlayPauseBtn.setImageResource(R.drawable.playing_btn_play);
            /**
             * 暂停播放，音乐条 滑开
             */
            isPlaying = false;

        } else {//正在播放，显示暂停播放图片
            mPlayPauseBtn.setImageResource(R.drawable.playing_btn_pause);
            /**
             *开始播放，音乐条 滑到碟子上
             */
            isPlaying = true;
        }


        if (isInitMusicSource) {
            //里面有startOrPausePlaying()
            mPresenter.initMusicSource();
            isInitMusicSource = false;
        } else {

            mPresenter.startOrPausePlaying();
        }
    }

    /**
     * 初始化Toolbar
     */
    @Override
    public void initToolbar() {

    }

    @Override
    public void showSingerHearImage(String url) {
        if (url != null) {
            Glide.with(getActivity()).load(url).into(mSingerImage);
        }else {
            //默认图片
        }
    }

    @Override
    public void finishActivity() {
        getActivity().finish();
    }

    @Override
    public void setPresenter(PlayMusicContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.my_love_btn, R.id.download_music_btn, R.id.music_comment_btn, R.id.more_btn, R.id.order_play_btn, R.id.previous_music_btn, R.id.play_pause_btn, R.id.next_music_btn, R.id.music_list_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_love_btn:
                break;
            case R.id.download_music_btn:
                break;
            case R.id.music_comment_btn:
                break;
            case R.id.more_btn:
                break;
            case R.id.order_play_btn:
                break;
            case R.id.previous_music_btn:
                break;
            case R.id.play_pause_btn: {
                playOrPauseMusic();
                break;
            }
            case R.id.next_music_btn:
                break;
            case R.id.music_list_btn:
                break;
            default:
                break;
        }
    }
}
