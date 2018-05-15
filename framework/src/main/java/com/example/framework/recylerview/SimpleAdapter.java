package com.example.framework.recylerview;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/5/15.
 */

public abstract class SimpleAdapter<B extends ViewDataBinding, T> extends RecyclerView.Adapter<SimpleViewHolder<B>> {

    private List<T> data = new ArrayList<>();

    @Override
    public SimpleViewHolder<B> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, null);
        return new SimpleViewHolder<>(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder<B> holder, int position) {
        onBind(data.get(position), holder.binding, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public abstract int getLayoutIdForPosition(int position);

    public abstract void onBind(T t, B b, int position);

    public void setData(List<T> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    public void setData(T[] array) {
        setData(Arrays.asList(array));
    }


    public void clearData() {
        data.clear();
        notifyDataSetChanged();
    }

    public void addData(T t) {
        data.add(t);
        notifyDataSetChanged();
    }

    public void removeData(int position) {
        data.remove(position);
        notifyDataSetChanged();
    }

    public void updateData(T t, int position) {
        data.set(position, t);
        notifyDataSetChanged();
    }
}
