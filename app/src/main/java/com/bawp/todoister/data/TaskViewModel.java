package com.bawp.todoister.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bawp.todoister.model.Task;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    public static TaskRepository repository;
    public final LiveData<List<Task>> allTask;


    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTask=repository.getAllTask();
    }

    public  LiveData<List<Task>> getAllTask(){return repository.getAllTask();}
    public static void insert_task(Task task){repository.Insert_task(task);}
    public static LiveData<Task> getTask(long id ){return repository.getTask(id);}
    public static void update_task(Task task) {repository.update_task(task);}
    public static void Delete_task(Task task){repository.Delete_task(task);}



}
