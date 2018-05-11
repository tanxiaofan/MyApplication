package com.example.framework.base;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.framework.mvp.IPresent;
import com.example.framework.mvp.XFragment;


/**
 * BaseFragment
 *
 * @author lee
 * @date 2016/5/3
 */
public abstract class BaseFragment<B extends ViewDataBinding, P extends IPresent> extends XFragment<B, P> {
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////

    public void addFragment(Fragment fragment) {
        Activity ac = getActivity();
        if (ac instanceof BaseActivity) {
            ((BaseActivity) ac).addFragment(fragment);
        }
    }

    /**
     * Fragment的替换
     *
     * @param fragment 需要被替换的Fragment
     * @param needBack 是否需要被保存到回退
     */
    public void replace(BaseFragment fragment, boolean needBack) {
        Activity ac = getActivity();
        if (ac instanceof BaseActivity) {
            ((BaseActivity) ac).replace(fragment, needBack);
        }
    }

    /**
     * 回退到指定fragment
     *
     * @param name Fragment name
     * @return 回退是否成功
     */
    public boolean popToFragment(String name) {
        Activity ac = getActivity();
        if (ac instanceof BaseActivity) {
            return ((BaseActivity) ac).popToFragment(name);
        }
        return false;
    }

    /**
     * 重新加载当前Fragment
     */
    public void refreshCurrentFragment() {
        Activity ac = getActivity();
        if (ac instanceof BaseActivity) {
            ((BaseActivity) ac).refreshCurrentFragment();
        }
    }

    /**
     * 回退到上一个Fragment
     */
    public void toBackFragment() {
        Activity ac = getActivity();
        if (ac instanceof BaseActivity) {
            ((BaseActivity) ac).toBackFragment();
        }
    }

    public void removeFragment(){
        Activity ac = getActivity();
        if (ac instanceof BaseActivity) {
            ((BaseActivity) ac).removeFragment();
        }
    }

    protected <T extends View> T $(View view, int id) {
        return ((T) view.findViewById(id));
    }

    public void setStatusBar(){
        Activity ac = getActivity();
        if (ac instanceof BaseActivity) {
            ((BaseActivity) ac).setStatusBar();
        }
    }
}
