package com.bawp.todoister;

import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawp.todoister.data.SharedViewModel;
import com.bawp.todoister.data.TaskViewModel;
import com.bawp.todoister.model.Priority;
import com.bawp.todoister.model.Task;
import com.bawp.todoister.util.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;
import java.util.Date;

public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    private EditText taskTodo;
    private ImageButton calenderButton;
    private  ImageButton priorityButton;
    private RadioGroup priorityRadioGroup;
    private  boolean isEdit;
    private RadioButton selectedRadioButton;
    private int selectedButtonId;
    private ImageButton saveTodoButton;
    private CalendarView calendarView;
    SharedViewModel sharedViewModel ;

    private Group calenderGroup;
    private Date dueDate;
    private Priority priority;
    Calendar calendar = Calendar.getInstance();

    public BottomSheetFragment() {
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        calenderGroup = view.findViewById(R.id.calendar_group);
        calendarView = view.findViewById(R.id.calendar_view);
        calenderButton= view.findViewById(R.id.today_calendar_button);

        priorityButton= view.findViewById(R.id.priority_todo_button);
        priorityRadioGroup=view.findViewById(R.id.radioGroup_priority);
        //priorityRadioButton= view.findViewById(R.id.radio);


        Chip today_chip= view.findViewById(R.id.today_chip);
        today_chip.setOnClickListener(this);
        Chip tomorrow_chip= view.findViewById(R.id.tomorrow_chip);
        tomorrow_chip.setOnClickListener(this);
        Chip next_week = view.findViewById(R.id.next_week_chip);
        next_week.setOnClickListener(this);

        RadioButton radioButton_high = view.findViewById(R.id.radioButton_high);
        radioButton_high.setOnClickListener(this);
        RadioButton radioButton_medium = view.findViewById(R.id.radioButton_med);
        radioButton_medium.setOnClickListener(this);
        RadioButton radioButton_low = view.findViewById(R.id.radioButton_low);
        radioButton_low.setOnClickListener(this);


        taskTodo =view.findViewById(R.id.enter_todo_et);
        saveTodoButton =view.findViewById(R.id.save_todo_button);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        if(sharedViewModel.getItems().getValue()!= null) {
            Task task = sharedViewModel.getItems().getValue();

            taskTodo.setText(task.getTask_name());
            isEdit = sharedViewModel.getEdit();


           }

        }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);



            calenderButton.setOnClickListener(view12 -> {

                Utils.hideSoftKeyboard(view);

            calenderGroup.setVisibility(calenderGroup
                    .getVisibility()==View.GONE ? View.VISIBLE:View.GONE);


        });

        calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
            calendar.clear();
            calendar.set(year,month,dayOfMonth);
            dueDate = calendar.getTime();


        });
      saveTodoButton.setOnClickListener(view1 -> {

          String task = taskTodo.getText().toString();
          if (!TextUtils.isEmpty(task) && dueDate != null && priority!=null) {
              Task myTask = new Task(task, priority, dueDate
                      , Calendar.getInstance().getTime(), false);


              if (isEdit) {

                  Task update = sharedViewModel.getItems().getValue();
                  update.setTask_name(task);
                  update.setSet_date(Calendar.getInstance().getTime());
                  update.setTask_priority(Priority.HIGH);
                  update.setDue_date(dueDate);
                  TaskViewModel.update_task(update);
                  sharedViewModel.setEdit(false);


              } else {

                  TaskViewModel.insert_task(myTask);

              }
              taskTodo.setText("");
          }
          else{
              Snackbar.make(saveTodoButton,R.string.empty_field,Snackbar.LENGTH_SHORT)
                      .show();
          }
          if(this.isVisible()){this.dismiss();}

      });

       priorityButton.setOnClickListener(view13 -> {
           Utils.hideSoftKeyboard(view);
           priorityRadioGroup.setVisibility(
                   priorityRadioGroup.getVisibility()== View.GONE ?View.VISIBLE: View.GONE);

       });








    }

    @Override
    public void onClick(View view) {
        int getId=view.getId();

        if(getId == R.id.today_chip){
            calendar.add(Calendar.DAY_OF_YEAR,0);
            dueDate =calendar.getTime();
        }
        if(getId == R.id.tomorrow_chip){
            calendar.add(Calendar.DAY_OF_YEAR,1);
            dueDate =calendar.getTime();
        }
        if(getId == R.id.next_week_chip){
            calendar.add(Calendar.DAY_OF_YEAR,7);
            dueDate =calendar.getTime();
        }

       if(getId==R.id.radioButton_high){
           priority=Priority.HIGH;
       }
       if(getId==R.id.radioButton_med){
           priority = Priority.MEDIUM;
       }
        if(getId==R.id.radioButton_low){
            priority = Priority.LOW;
        }


    }

}