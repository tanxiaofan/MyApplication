package com.example.framework.recylerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2018/5/15.
 */

public class SimpleViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public B binding;

    public SimpleViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}
