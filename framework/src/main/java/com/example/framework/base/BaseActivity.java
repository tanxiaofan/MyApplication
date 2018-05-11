package com.example.framework.base;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.framework.mvp.IPresent;
import com.example.framework.mvp.XActivity;


/**
 * BaseActivity
 *
 * @author lee
 * @date 2016/5/3
 */
public abstract class BaseActivity<B extends ViewDataBinding, P extends IPresent> extends XActivity<B, P> {
    //    protected LoadingDialog loading;//加载框
    public int idFrame = -1;


    public abstract int initFrameID();

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public P newP() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        idFrame = initFrameID();
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        idFrame = initFrameID();
        super.onCreate(savedInstanceState);
//        this.loading = LoadingDialog.build(context);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        loading.dismiss();
    }

    public void showLoading() {
//        loading.show();
    }

    public void hideLoading() {
//        loading.dismiss();
    }

    @Override
    public void onBackPressed() {
        removeFragment();
    }

    /**
     * @param fragment Fragment
     */
    public void addFragment(Fragment fragment) {
        try {
            String clazz = fragment.getClass().getSimpleName();

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(idFrame, fragment, clazz);
            transaction.addToBackStack(clazz);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * Fragment替换
     *
     * @param fragment 需要被替换的Fragment
     * @param needBack 是否需要被保存到回退
     */
    public void replace(Fragment fragment, boolean needBack) {
        replace(idFrame, fragment, needBack);
    }

    /**
     * Fragment替换
     *
     * @param fragment 需要被替换的Fragment
     * @param needBack 是否需要被保存到回退
     */
    public void replace(@IdRes int id, Fragment fragment, boolean needBack) {
        try {
            String clazz = fragment.getClass().getSimpleName();

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(id, fragment, clazz);
            if (needBack)
                transaction.addToBackStack(clazz);
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 回退到指定fragment
     *
     * @param name Fragment name
     */
    public boolean popToFragment(String name) {
        FragmentManager mgr = getSupportFragmentManager();
        if (mgr == null) {
            Log.d("popToFragment", "getSupportFragmentManager() returned null?!");
        }

        try {
            Fragment frag = mgr.findFragmentByTag(name);
            Log.d("popToFragment", String.valueOf(frag));
            if (frag != null) {
                mgr.popBackStack(name, 0);
                return true;
            }
        } catch (Exception var4) {
            Log.e("RIO", var4.getMessage());
        }

        return false;
    }

    /**
     * @return 当前Fragment的名字
     */
    public String getCurrentFragmentName() {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            int count = fragmentManager.getBackStackEntryCount();
            if (count > 0) {
                --count;
            }
            if (count < 0) {
                return "";
            }
            return fragmentManager.getBackStackEntryAt(count).getName();
        } catch (Exception e) {
            e.printStackTrace();

        }

        return "";
    }

    /**
     * @return 当前Fragment
     */
    public Fragment getCurrentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return fragmentManager.findFragmentByTag(getCurrentFragmentName());
    }

    /**
     * 重新加载当前Fragment
     */
    public void refreshCurrentFragment() {
        try {
            Fragment fragment = getCurrentFragment();
            if (fragment == null) return;

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
            fragTransaction.detach(fragment);
            fragTransaction.attach(fragment);
            fragTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 回退到上一个Fragment
     */
    public void toBackFragment() {
        try {
            FragmentManager manager = getSupportFragmentManager();
            manager.popBackStack();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    //移除fragment
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

//    //返回键返回事件
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (KeyEvent.KEYCODE_BACK == keyCode) {
//            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
//                finish();
//                return true;
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    assert v != null;
                    //点击EditText之外的地方自动隐藏软键盘
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                    v.clearFocus();
                    View activity_view = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
                    activity_view.setClickable(true);
                    activity_view.setFocusable(true);
                    activity_view.setFocusableInTouchMode(true);
                    activity_view.requestFocusFromTouch();
                    onHideSoftInput((EditText) v);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }

    protected void onHideSoftInput(EditText editText) {
        //todo
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }
}
