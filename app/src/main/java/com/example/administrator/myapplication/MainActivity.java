package com.example.administrator.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtDetail = findViewById(R.id.detail);
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
        txtDetail.setText(detail);
    }

    private void setWindowAlpha(float alpha) {
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        wl.alpha = alpha;//这句就是设置窗口里控件的透明度的．０.０全透明．１.０不透明．
        window.setAttributes(wl);
    }

}
