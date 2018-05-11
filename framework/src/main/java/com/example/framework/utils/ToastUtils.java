package com.example.framework.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 吐司相关工具类
 * Created by zsw on 2017/10/20.
 */
public final class ToastUtils {

    private static Toast mToast;

    public static void showToastLong(Context context, String msg) {
        if (!TextUtils.isEmpty(msg))
            showNoRepeatToast(context, msg, Toast.LENGTH_LONG);
        //      showToast(context, msg, Toast.LENGTH_LONG);
    }

    public static void showToastShort(Context context, String msg) {
//        showToast(context, msg, Toast.LENGTH_SHORT);
        if (!TextUtils.isEmpty(msg))
            showNoRepeatToast(context, msg);
    }

    public static void showToastShort(Context context, int strRes) {
        if (!TextUtils.isEmpty(context.getString(strRes)))
            showNoRepeatToast(context, context.getString(strRes));
    }

    public static void showToastLong(Context context, int strRes) {
        if (!TextUtils.isEmpty(context.getString(strRes)))
            showNoRepeatToast(context, context.getString(strRes), Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, String msg, int duration) {
        if (!TextUtils.isEmpty(msg))
            Toast.makeText(context, msg, duration).show();
    }

    public static void showNoRepeatToast(Context context, String msg) {
        if (!TextUtils.isEmpty(msg))
            showNoRepeatToast(context, msg, Toast.LENGTH_SHORT);
    }

    public static void showNoRepeatToast(Context context, String msg, int duration) {
        if (!TextUtils.isEmpty(msg))
            if (mToast == null) {
                mToast = Toast.makeText(context.getApplicationContext(), msg, duration);
            }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setText(msg);
        mToast.show();
    }


}
