package com.example.administrator.myapplication;

import com.example.administrator.myapplication.bean.Student;
import com.example.administrator.myapplication.databinding.ItemStudentBinding;
import com.example.framework.recylerview.SimpleAdapter;

/**
 * Created by Administrator on 2018/5/14.
 */

public class StudentAdapter extends SimpleAdapter<ItemStudentBinding, Student> {
    @Override
    public int getLayoutIdForPosition(int position) {
        return R.layout.item_student;
    }

    @Override
    public void onBind(Student student, ItemStudentBinding itemStudentBinding, int position) {
        itemStudentBinding.stuName.setText(student.getName());
        itemStudentBinding.stuAge.setText(String.valueOf(student.getAge()));
    }

}
