package com.example.administrator.myapplication;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.bean.Student;
import com.example.administrator.myapplication.databinding.ItemStudentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/14.
 */

public class StudentAdapter extends RecyclerView.Adapter {
    private List<Student> data;

    public StudentAdapter() {
        data = new ArrayList<>();
    }

    public void setData(List<Student> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, null);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Student student = data.get(position);

        StudentViewHolder studentViewHolder = (StudentViewHolder) holder;

        ViewDataBinding binding = studentViewHolder.getBinding();
        ItemStudentBinding itemStudentBinding = (ItemStudentBinding) binding;

        itemStudentBinding.stuAge.setText(String.valueOf(student.getAge()));
        itemStudentBinding.stuName.setText(student.getName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
