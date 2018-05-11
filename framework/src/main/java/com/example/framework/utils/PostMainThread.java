package com.example.framework.utils;

import android.os.Handler;
import android.os.Looper;

/** Created by kyle on 2016/4/9. */
public class PostMainThread {
  /**
   * post一个runnable到主线程
   *
   * @param runnable
   * @return
   */
  public static boolean post(Runnable runnable) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      runnable.run();
      return true;
    } else {
      Handler handler = new Handler(Looper.getMainLooper());
      return handler.post(runnable);
    }
  }

  /**
   * post一个runnable到主线程
   *
   * @param runnable
   * @param delayMillis 时间
   * @return
   */
  public static boolean postDelayed(Runnable runnable, long delayMillis) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      runnable.run();
      return true;
    }
    Handler handler = new Handler(Looper.getMainLooper());
    return handler.postDelayed(runnable, delayMillis);
  }
}
