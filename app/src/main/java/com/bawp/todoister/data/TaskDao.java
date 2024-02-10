package com.bawp.todoister.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bawp.todoister.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void Insert_task(Task task);
    @Query("DELETE FROM Task_table")
     void delete_all();
    @Delete()
     void Delete_task(Task task);
    @Query("SELECT * FROM Task_table")
    LiveData<List<Task>> getTasks();
    @Query("SELECT * FROM Task_table WHERE TaskId =:id")
    LiveData<Task> getTask(long id );
    @Update
    void update_task(Task task);


}
