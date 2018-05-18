package com.example.administrator.myapplication;

import com.example.administrator.myapplication.bean.Student;
import com.example.administrator.myapplication.databinding.ItemMyStudentBinding;
import com.example.framework.recylerview.SimpleAdapter;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Administrator on 2018/5/18.
 */

public class MyStudentAdapter extends SimpleAdapter<ItemMyStudentBinding, Student> {
    private int defaultItemCount = 3;
    private boolean expand;

    @Override
    public int getLayoutIdForPosition(int position) {
        return R.layout.item_my_student;
    }

    @Override
    public void onBind(Student student, ItemMyStudentBinding binding, int position) {
        binding.stuName.setText(student.getName());
        binding.stuAge.setText(String.valueOf(student.getAge()));
        binding.btnExpand.setVisibility(isExpandable() && position == getItemCount() - 1 ? expand ? GONE : VISIBLE : GONE);
        binding.btnHide.setVisibility(isExpandable() && position == getItemCount() - 1 ? expand ? VISIBLE : GONE : GONE);
        binding.btnExpand.setOnClickListener(v -> {
            expand = true;
            notifyDataSetChanged();
        });
        binding.btnHide.setOnClickListener(v -> {
            expand = false;
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        int itemCount = super.getItemCount();
        if (isExpandable()) {
            return expand ? itemCount : defaultItemCount;
        }
        return itemCount;
    }

    private boolean isExpandable() {
        return super.getItemCount() > defaultItemCount;
    }
}
