package com.bawp.todoister;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bawp.todoister.adapter.OnTodoClickListener;
import com.bawp.todoister.adapter.RecyclerViewAdapter;
import com.bawp.todoister.data.SharedViewModel;
import com.bawp.todoister.data.TaskViewModel;
import com.bawp.todoister.model.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


import java.util.List;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity implements OnTodoClickListener {
    private TaskViewModel taskViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    BottomSheetFragment bottomSheetFragment;
    private SharedViewModel sharedViewModel;
    public OnTodoClickListener todoclick;
    private LiveData<List<Task>>isempty;
    private Button  instructionToFirstTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        taskViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication())
                .create(TaskViewModel.class);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        instructionToFirstTask=  findViewById(R.id.instruction_button);

          isempty = taskViewModel.getAllTask();
          if(isempty==null) {

              instructionToFirstTask.setVisibility(View.VISIBLE);
              instructionToFirstTask.setText("Click here to create your first task");

          }
          else{
              instructionToFirstTask.setVisibility(View.GONE);
          }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        bottomSheetFragment = new BottomSheetFragment();
        ConstraintLayout constraintLayout2 = findViewById((R.id.pop_window));

        ConstraintLayout constraintLayout = findViewById(R.id.bottomSheet);
        BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.from(constraintLayout);
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        taskViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication())
                .create(TaskViewModel.class);

        taskViewModel.getAllTask().observe(this, tasks -> {
            recyclerViewAdapter = new RecyclerViewAdapter(tasks, this);
            recyclerView.setAdapter(recyclerViewAdapter);


        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {

            ShowButtonSheetDialog();
            if(taskViewModel.getAllTask()!=null){
                instructionToFirstTask.setVisibility(View.GONE);
            }

        });
    }


    private void ShowButtonSheetDialog() {
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
       // return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_settings:
                gotoabout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void gotoabout() {
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void TodoClickListener(int adapterPosition, Task task, View view) {
        sharedViewModel.setItems(task);
        sharedViewModel.setEdit(true);
        ShowButtonSheetDialog();

    }

    @Override
    public void OnTodoRadioListener(Task task, View view) {

         task.setDone(true);
        // onButtonShowPopupWindowClick(view);


    }

    @Override
    public void OnTodoDeleteListener(Task task, View view) {

        TaskViewModel.Delete_task(task);
        if(isempty==null){
            instructionToFirstTask.setVisibility(View.VISIBLE);
            instructionToFirstTask.setText("Click here to create your first task");
        }
        else{
            instructionToFirstTask.setVisibility(View.GONE);
        }
        recyclerViewAdapter.notifyDataSetChanged();

    }
}


