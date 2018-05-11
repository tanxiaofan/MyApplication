package com.example.framework.utils;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liujing on 2017/9/20.
 */

public class DownTimeUtil extends CountDownTimer {
    private TextView tv;

    public DownTimeUtil(long millisInFuture, long countDownInterval, TextView tv) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;
    }

    @Override
    public void onTick(long l) {
        tv.setText("距开课："+longToStr(l));
    }

    @Override
    public void onFinish() {
        tv.setVisibility(View.GONE);
    }

    private String longToStr(long l){
        Date addTime = new Date(l);
        SimpleDateFormat format =new SimpleDateFormat("HH:mm:ss");
        String time = format.format(addTime);
        return time;
    }
}
