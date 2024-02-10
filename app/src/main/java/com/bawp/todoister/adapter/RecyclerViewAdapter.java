package com.bawp.todoister.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bawp.todoister.R;
import com.bawp.todoister.model.Priority;
import com.bawp.todoister.model.Task;
import com.bawp.todoister.util.Utils;
import com.google.android.material.chip.Chip;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final List<Task> taskList;
    private final OnTodoClickListener onTodoClickListener;

    public RecyclerViewAdapter(List<Task> taskList,OnTodoClickListener onTodoClickListener) {
        this.taskList = taskList;
        this.onTodoClickListener =onTodoClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        String formatted = Utils.formatDate(task.getDue_date());


        ColorStateList colorStateList = new ColorStateList(new int[][]{
                new int[]{-android.R.attr.state_enabled},
                new int[]{android.R.attr.state_enabled}},

                new int []{
                        Color.LTGRAY,
                        Utils.priorityColor(task)
        });

      //  holder.todayChip.setTextColor(Utils.priorityColor(task));
        holder.todayChip.setChipIconTint(colorStateList);
        holder.radioButton.setButtonTintList(colorStateList);

       // holder.delete_button.setTextColor(colorStateList);
        holder.todo.setText(task.getTask_name());
        holder.todayChip.setText(formatted);


    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public AppCompatRadioButton radioButton;
        public AppCompatTextView todo;
        public Chip todayChip;
        public Button delete_button;
        OnTodoClickListener TodoClickListener;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.todo_radio_button);
            todo = itemView.findViewById(R.id.todo_row_todo);
            todayChip = itemView.findViewById(R.id.todo_row_chip);
            delete_button = itemView.findViewById(R.id.delete_button);
            this.TodoClickListener = onTodoClickListener;
            itemView.setOnClickListener(this);
            radioButton.setOnClickListener(this);
            delete_button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Task currtask= taskList.get(getAdapterPosition());
            int id = view.getId();
            if(id==R.id.todo_row_layout) {

                TodoClickListener.TodoClickListener(getAdapterPosition(), currtask,view);
            }
            else if(id==R.id.todo_radio_button)
            {
                TodoClickListener.OnTodoRadioListener(currtask,view);
            }
            else if(id==R.id.delete_button)
            {
                TodoClickListener.OnTodoDeleteListener(currtask,view);
            }

        }
    }
}
