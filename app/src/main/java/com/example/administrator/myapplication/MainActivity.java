package com.example.administrator.myapplication;

import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.myapplication.bean.Student;
import com.example.administrator.myapplication.databinding.ActivityMainBinding;
import com.example.administrator.myapplication.databinding.ItemMyStudentBinding;
import com.example.framework.base.BaseActivity;
import com.example.framework.mvp.XPresent;
import com.example.framework.recylerview.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, XPresent> {
    SimpleAdapter<ItemMyStudentBinding, Student> adapter;

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
        test();
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

    private void test() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapter = new MyStudentAdapter();
        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(layoutManager);
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student s = new Student("name" + i, 20);
            list.add(s);
        }
        adapter.setData(list);
        adapter.setOnClickPresenter((student, v, position) -> {
            //
        });
    }

    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_clear:
                adapter.setData(new ArrayList<>());
                break;
        }
    }
}
