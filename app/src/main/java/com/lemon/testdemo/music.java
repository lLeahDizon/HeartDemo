package com.lemon.testdemo;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Date: 2018/5/11
 * Author: YangHT
 * description: 音乐类
 */
public class music {
    private static MediaPlayer mp = null;

    public static void play(Context context, int resource) {
        stop(context);
        mp = MediaPlayer.create(context, resource);
        mp.setLooping(true);
        mp.start();
    }

    public static void stop(Context context) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
