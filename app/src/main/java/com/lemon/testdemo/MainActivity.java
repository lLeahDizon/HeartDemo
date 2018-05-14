package com.lemon.testdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    HeartView heartView;
    TextView textView;
    FadeInTextView ft1, ft2, ft3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heartView = findViewById(R.id.surfaceView);
        textView = findViewById(R.id.time);
        ft1 = findViewById(R.id.name_one);
        ft2 = findViewById(R.id.name_two);
        ft3 = findViewById(R.id.name_three);
        music.play(this, R.raw.backmusic);
        ft1.setTextString("Boy's name = HaiTao").startFadeInAnimation().setTextAnimationListener(new FadeInTextView.TextAnimationListener() {
            @Override
            public void animationFinish() {
                ft2.setTextString("Girl's name = JingJing").startFadeInAnimation();
            }
        });
        ft2.setTextAnimationListener(new FadeInTextView.TextAnimationListener() {
            @Override
            public void animationFinish() {
                ft3.setTextString("I Love You Forever").startFadeInAnimation();
            }
        });
        AnimationUtils.showAndHiddenAnimation(textView, AnimationUtils.AnimationState.STATE_SHOW, 10000);
        getTime();
    }

    @Override
    protected void onPause() {
        super.onPause();
        music.stop(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        music.play(this, R.raw.backmusic);
    }

    private void getTime() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Calendar calendar = Calendar.getInstance();
                        calendar.clear();
                        calendar.set(2014, 8, 8);
                        long startTime = calendar.getTimeInMillis();
                        long endTime = System.currentTimeMillis();
                        textView.setText(getSize(startTime, endTime));
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private String getSize(long start, long end) {
        long time = end - start;
        int days = (int) Math.floor(time / (24 * 3600 * 1000));
        long leave1 = time % (24 * 3600 * 1000);
        int hours = (int) Math.floor(leave1 / (3600 * 1000));
        long leave2 = leave1 % (3600 * 1000);
        int minutes = (int) Math.floor(leave2 / (60 * 1000));
        long leave3 = leave2 % (60 * 1000);
        int seconds = Math.round(leave3 / 1000);

        return days + " 天 " + hours + " 小时 " + minutes + " 分 " + seconds + " 秒";
    }
}
