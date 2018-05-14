package com.example.administrator.myapplication;

import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.myapplication.databinding.ActivityMainBinding;
import com.example.framework.base.BaseActivity;
import com.example.framework.mvp.XPresent;

public class MainActivity extends BaseActivity<ActivityMainBinding, XPresent> {

    @Override
    public int initFrameID() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void bindUI(View rootView) {
        super.bindUI(rootView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        showDetail();
    }

    private void showDetail() {
        int sdkInt = Build.VERSION.SDK_INT;
        String brand = Build.BRAND;
        String device = Build.DEVICE;
        int sw = getResources().getConfiguration().smallestScreenWidthDp;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int densityDpi = metrics.densityDpi;
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        String detail = "device:" + device +
                "\n" + "brand:" + brand +
                "\n" + "sdkInt:" + sdkInt +
                "\n" + "widthPixels:" + widthPixels +
                "\n" +
                "heightPixels:" + heightPixels +
                "\n" +
                "densityDpi:" + densityDpi +
                "\n" +
                "dpi:" + getString(R.string.app_dpi) +
                "\n" +
                "sw:" + sw +
                "\n";
        binding.detail.setText(detail);
    }

    private void setWindowAlpha(float alpha) {
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        wl.alpha = alpha;//这句就是设置窗口里控件的透明度的．０.０全透明．１.０不透明．
        window.setAttributes(wl);
    }
}
