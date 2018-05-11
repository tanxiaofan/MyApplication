package com.example.framework.mvp;

import java.lang.ref.WeakReference;

/**
 * Created by lee on 2018/03/15.
 */

public class XPresent<V extends IView> implements IPresent<V> {
    private WeakReference<V> v;

    @Override
    public void attachV(V view) {
        v = new WeakReference<V>(view);
    }

    @Override
    public void detachV() {
        if (v.get() != null) {
            v.clear();
        }
        v = null;
    }

    protected V getV() {
        if (v == null) {
            throw new IllegalStateException("v can not be null");
        }
        return v.get();
    }
}
