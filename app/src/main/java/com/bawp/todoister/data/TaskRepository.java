package com.bawp.todoister.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bawp.todoister.model.Task;
import com.bawp.todoister.util.TaskRoomDatabase;

import java.util.List;

public class TaskRepository {

public  final TaskDao taskDao;
public  final LiveData<List<Task>> allTask;

    public TaskRepository(Application application) {
        TaskRoomDatabase database = TaskRoomDatabase.getDatabase1(application);
        taskDao = database.taskDao();
        allTask = taskDao.getTasks();
    }

    public  LiveData<List<Task>> getAllTask(){
        return allTask;
    }

    public void Insert_task(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(()->{

            taskDao.Insert_task(task);
        });
    }
    public LiveData<Task> getTask(long id )
    {
        return   taskDao.getTask(id);
    }

    public void update_task(Task task){
        TaskRoomDatabase.databaseWriteExecutor.execute(()->{
            taskDao.update_task(task);
        });
    }

    public void Delete_task(Task task )
    {
        TaskRoomDatabase.databaseWriteExecutor.execute(()->{
            taskDao.Delete_task(task);
        });
    }

}
