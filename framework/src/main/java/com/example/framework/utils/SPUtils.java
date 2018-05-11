package com.example.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.framework.base.BaseApplication;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * SP相关工具类
 * Created by Seven on 2017/2/15.
 */
public class SPUtils {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public static SPUtils create(Context context) {
        return new SPUtils(context, AppUtils.getPackageName(context));
    }

    public static SPUtils create(Context context, String spName) {
        return new SPUtils(context, spName);
    }

    private SPUtils(Context context, String spName) {
        sp = context.getApplicationContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.apply();
    }

    public void put(String key, String value) {
        editor.putString(key, value).apply();
    }

    public String getString(String key) {
        return getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public void put(String key, int value) {
        editor.putInt(key, value).apply();
    }

    public int getInt(String key) {
        return getInt(key, -1);
    }

    public int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public void put(String key, long value) {
        editor.putLong(key, value).apply();
    }

    public long getLong(String key) {
        return getLong(key, -1L);
    }

    public long getLong(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public void put(String key, float value) {
        editor.putFloat(key, value).apply();
    }

    public void put(String key, Object object) {
        String value = new Gson().toJson(object);
        put(key, value);
    }

    public <T> T get(String key, Class<T> classOfT) {
        String value = getString(key);
        return new Gson().fromJson(value, classOfT);
    }

    public <T> T get(String key, Type typeOfT) {
        String value = getString(key);
        return new Gson().fromJson(value, typeOfT);
    }

    public float getFloat(String key) {
        return getFloat(key, -1f);
    }

    public float getFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public void put(String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * SP中获取所有键值对
     *
     * @return Map对象
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * SP中移除该key
     *
     * @param key 键
     */
    public void remove(String key) {
        editor.remove(key).apply();
    }

    /**
     * SP中是否存在该key
     *
     * @param key 键
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public boolean contains(String key) {
        return sp.contains(key);
    }

    /**
     * SP中清除所有数据
     */
    public void clear() {
        editor.clear().apply();
    }


}