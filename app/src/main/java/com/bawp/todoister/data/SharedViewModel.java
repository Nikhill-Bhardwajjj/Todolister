package com.bawp.todoister.data;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bawp.todoister.model.Task;

import java.util.List;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<Task>
            Task = new MutableLiveData<>();
    private boolean isEdit;

    public void setItems(Task task){Task.setValue(task);}
    public LiveData<Task> getItems(){return  Task;}

   public void setEdit(boolean Edit){isEdit=Edit;}
    public boolean  getEdit(){ return isEdit;}
}
