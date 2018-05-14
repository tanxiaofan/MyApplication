package com.example.administrator.myapplication;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2018/5/14.
 */

public class StudentViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private B binding;

    public StudentViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public B getBinding() {
        return binding;
    }
}
