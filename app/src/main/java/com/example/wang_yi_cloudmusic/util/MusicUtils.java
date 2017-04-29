package com.example.wang_yi_cloudmusic.util;

import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.example.wang_yi_cloudmusic.app.MyApplication;
import com.example.wang_yi_cloudmusic.model.bean.Music;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static android.content.ContentValues.TAG;

/**
 * Created by 79069 on 2017/4/29.
 */

public class MusicUtils {
    private static Set<Music> sMusicSet = new HashSet<>();


    /**
     * 返回存储音乐的文件
     *
     * @param musicName
     * @return
     */
    public static File getMusicFile(String musicName) {
        File file = null;

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC), musicName);
        } else {
            //如果在内部存储的时候构成路径
            String musicURI = MyApplication.getContext().getFilesDir() + File.separator + "Music" + File.separator;
            file = new File(musicURI, musicName);
            file.mkdirs();
        }

        return file;
    }

    /**
     * 获取目录下的所有歌曲
     *
     * @param dir
     * @return
     */
    public static void queryMusic(String dir) {
        Cursor cursor = MyApplication.getContext()
                .getContentResolver()
                .query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                        , null
                        , MediaStore.Audio.Media.DATA + "like ?"
                        , new String[]{dir + "%"}
                        , MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        if (cursor == null) {
            Log.d(TAG, "queryMusic: cursor为空");
            return;
        }

        Music music = null;

        for(cursor.moveToFirst(); !cursor.isAfterLast() ; cursor.moveToNext()) {
            String isMusic = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.IS_MUSIC));
            if (isMusic.equals("") && isMusic != null){
                continue;
            }

            String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));

            music = new Music();

            /**
             * 存储music的内容
             */

            sMusicSet.add(music);
        }

        cursor.close();
    }

}
