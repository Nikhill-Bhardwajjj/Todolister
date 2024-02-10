package com.bawp.todoister.adapter;

import android.view.View;

import com.bawp.todoister.model.Task;

public interface OnTodoClickListener {

    void TodoClickListener(int adapterPosition, Task task, View view);
    void OnTodoRadioListener(Task task, View view);
    void OnTodoDeleteListener(Task task,View view);
   // Boolean TodoConfirmListener(Boolean bool);

}
