package com.example.wang_yi_cloudmusic.playMusicSection;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wang_yi_cloudmusic.R;

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
    @InjectView(R.id.disk_needle)
    ImageView mDiskNeedle;

    @InjectView(R.id.music_seekbar)
    SeekBar mMusicSeekbar;
    @InjectView(R.id.current_playing_time)
    TextView mCurrentPlayingTime;
    @InjectView(R.id.all_playing_time)
    TextView mAllPlayingTime;

    private PlayMusicContract.Presenter mPresenter;

    //判断是否在播放,用于显示播放按钮还是暂停按钮
    private boolean isPlaying = false;

    //磁盘动画
    private ObjectAnimator mDiskAnimator;

    //磁盘动画开始点
    private float mStartAnimator = 0 ;


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

        mMusicSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * Notification that the progress level has changed
             *
             * @param seekBar :The SeekBar whose progress has changed
             * @param progress :The current progress level. This will be in the range min..max where min and max were set by setMin(int) and setMax(int), respectively. (The default values for min is 0 and max is 100.)
             * @param fromUser :True if the progress change was initiated by the user.
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //用户触发的
                if (fromUser) {
                    setProgressSchedule(progress);
                    mPresenter.skipProgress(progress);
                }
            }

            /**
             * Notification that the user has started a touch gesture
             *
             * @param seekBar The SeekBar in which the touch gesture began
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            /**
             * Notification that the user has finished a touch gesture
             *
             * @param seekBar The SeekBar in which the touch gesture began
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
            endDiskNeedleAnimation();
            isPlaying = false;

        } else {//正在播放，显示暂停播放图片
            mPlayPauseBtn.setImageResource(R.drawable.playing_btn_pause);
            //重新播放
            if (mMusicSeekbar.getProgress() >= mMusicSeekbar.getMax()){
                mMusicSeekbar.setProgress(0);
                setMusicCurrentTime(0);
            }
            /**
             *开始播放，音乐条 滑到碟子上
             */
            startDiskNeedleAnimation();
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
     * 开始音乐动画
     */
    @Override
    public void startDiskNeedleAnimation() {
        Animator animatorStart = AnimatorInflater.loadAnimator(getActivity(), R.animator.needle_start_music);

        animatorStart.setTarget(mDiskNeedle);
//        animatorStart.start();

        AnimatorSet animatorSet = new AnimatorSet();

        mDiskAnimator = ObjectAnimator.ofFloat(mMusicDisk , "rotation" , mStartAnimator ,  mStartAnimator-1+360);
        mDiskAnimator.setDuration(10000);
        mDiskAnimator.setInterpolator(new LinearInterpolator());
        mDiskAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        animatorSet.play(animatorStart).before(mDiskAnimator);

        animatorSet.start();
    }

    /**
     * 停止音乐动画
     */
    @Override
    public void endDiskNeedleAnimation() {
        Animator animatorEnd = AnimatorInflater.loadAnimator(getActivity(), R.animator.needle_end_music);

        animatorEnd.setTarget(mDiskNeedle);
        animatorEnd.start();
        mStartAnimator =  mMusicDisk.getRotation();
        mDiskAnimator.cancel();
    }


    /**
     * 初始化Toolbar
     */
    @Override
    public void initToolbar() {

    }


    /**
     * 加载歌手图片
     *
     * @param url
     */
    @Override
    public void showSingerHearImage(String url) {
        if (url != null) {
            Glide.with(getActivity()).load(url).into(mSingerImage);
        } else {
            //默认图片
        }
    }

    /**
     * 获取当前进度时间
     * @param time
     */
    @Override
    public void setMusicCurrentTime(int time) {
        mCurrentPlayingTime.setText(time+"");
    }

    /**
     * 显示歌曲总时间
     *
     * @param time
     */
    @Override
    public void setMusicAllTime(int time) {
        Log.d(TAG, "setMusicAllTime: "+time);
        if (time != -1) {
            mAllPlayingTime.setText(time+"");
        }
    }

    /**
     * 显示进度
     * @param schedule
     */
    @Override
    public void setProgressSchedule(int schedule) {
        mMusicSeekbar.setProgress(schedule);
    }

    @Override
    public void switchBtnIcon(boolean isPlaying) {
        if (isPlaying){
            mPlayPauseBtn.setImageResource(R.drawable.playing_btn_play);
        }else {
            mPlayPauseBtn.setImageResource(R.drawable.playing_btn_pause);
        }
    }

    @Override
    public void setIsPlayingBoolean() {
        isPlaying = !isPlaying;
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
