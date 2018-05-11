package com.example.framework.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.example.framework.BuildConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;


public abstract class BaseApplication extends Application {
    public static BaseApplication app;

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> new ClassicsHeader(context));
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> new ClassicsFooter(context));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initLog();
    }

    public static BaseApplication getInstance() {
        return app;
    }

    private void initLog() {
        LogConfiguration logConfiguration = new LogConfiguration.Builder()
                .b()    // 允许打印日志边框，默认禁止
                .t()    // 允许打印线程信息，默认禁止
                .logLevel(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE)
                .build();
        XLog.init(logConfiguration);
    }
}
