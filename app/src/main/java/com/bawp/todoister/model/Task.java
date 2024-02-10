package com.bawp.todoister.model;

import android.renderscript.RenderScript;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Task_table")
public class Task {
    @ColumnInfo(name ="TaskId")
    @PrimaryKey(autoGenerate = true)
    private long TaskId;
    @ColumnInfo(name = "task_name")
    private String task_name;
    @ColumnInfo(name = "task_priority")
    private Priority  task_priority;
    @ColumnInfo(name = "due_date")
    private Date due_date;
    @ColumnInfo(name = "set_date")
    private Date  set_date;
    @ColumnInfo(name = "isDone")
    private boolean isDone;

    public Task(String task_name, Priority task_priority, Date due_date, Date set_date, boolean isDone) {
        this.task_name = task_name;
        this.task_priority = task_priority;
        this.due_date = due_date;
        this.set_date = set_date;
        this.isDone = isDone;
    }

    public long  getTaskId() {
        return TaskId;
    }

    public void setTaskId(long taskId) {
        TaskId = taskId;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public Priority getTask_priority() {
        return task_priority;
    }

    public void setTask_priority(Priority task_priority) {
        this.task_priority = task_priority;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Date getSet_date() {
        return set_date;
    }

    public void setSet_date(Date set_date) {
        this.set_date = set_date;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "TaskId=" + TaskId +
                ", task_name='" + task_name + '\'' +
                ", task_priority=" + task_priority +
                ", due_date=" + due_date +
                ", set_date=" + set_date +
                ", isDone=" + isDone +
                '}';
    }

}


