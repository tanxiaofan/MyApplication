package com.example.framework.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.framework.R;
import com.example.framework.databinding.FragmentSimpleDialogBinding;
import com.example.framework.mvp.IPresent;
import com.example.framework.mvp.XDialogFragment;


/**
 * Created by Administrator on 2018/5/17.
 */

public class SimpleDialogFragment extends XDialogFragment<FragmentSimpleDialogBinding, IPresent> {

    private OnLeftBtnClickListener onLeftBtnClickListener;
    private OnRightBtnClickListener onRightBtnClickListener;

    public interface OnLeftBtnClickListener {
        void onLeftBtnClick(SimpleDialogFragment simpleDialogFragment);
    }

    public interface OnRightBtnClickListener {
        void onRightBtnClick(SimpleDialogFragment simpleDialogFragment);
    }

    public static SimpleDialogFragment newInstance(String content, String leftString, String rightString) {

        Bundle args = new Bundle();
        args.putString("content", content);
        args.putString("leftString", leftString);
        args.putString("rightString", rightString);

        SimpleDialogFragment fragment = new SimpleDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static SimpleDialogFragment newInstance(Context context, int contentRes, int leftStringRes, int rightStringRes) {

        Bundle args = new Bundle();
        args.putString("content", context.getResources().getString(contentRes));
        args.putString("leftString", context.getResources().getString(leftStringRes));
        args.putString("rightString", context.getResources().getString(rightStringRes));

        SimpleDialogFragment fragment = new SimpleDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_simple_dialog;
    }

    @Override
    public IPresent newP() {
        return null;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.setCanceledOnTouchOutside(true);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void bindUI(View rootView) {
        super.bindUI(rootView);
        binding.content.setText(getArguments().getString("content"));
        binding.left.setText(getArguments().getString("leftString"));
        binding.right.setText(getArguments().getString("rightString"));

        binding.left.setOnClickListener(v -> {
            if (onLeftBtnClickListener != null) {
                onLeftBtnClickListener.onLeftBtnClick(this);
            }
        });
        binding.right.setOnClickListener(v -> {
            if (onRightBtnClickListener != null) {
                onRightBtnClickListener.onRightBtnClick(this);
            }
        });
    }

    public SimpleDialogFragment setOnLeftBtnClickListener(OnLeftBtnClickListener onLeftBtnClickListener) {
        this.onLeftBtnClickListener = onLeftBtnClickListener;
        return this;
    }

    public SimpleDialogFragment setOnRightBtnClickListener(OnRightBtnClickListener onRightBtnClickListener) {
        this.onRightBtnClickListener = onRightBtnClickListener;
        return this;
    }
}
