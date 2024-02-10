package com.bawp.todoister.util;



import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bawp.todoister.data.TaskDao;
import com.bawp.todoister.model.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class},version = 1,exportSchema = false)
@TypeConverters(Converter.class)
public abstract  class TaskRoomDatabase extends RoomDatabase {
    public static final int NO_OF_THREAD =4;
    public static String Database_name ="task_database";

    private static volatile TaskRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor=
            Executors.newFixedThreadPool(NO_OF_THREAD);


    public static RoomDatabase.Callback sRoomDatabase =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    databaseWriteExecutor.execute(()-> {
                        //we can invoke Dao and write
                        TaskDao taskDao = INSTANCE.taskDao();
                        taskDao.delete_all();//clean the slate
                        //write to our table

                            });
                }
            };

    public static TaskRoomDatabase getDatabase1(Context context)
    {
        if(INSTANCE==null)
        {
            synchronized (TaskRoomDatabase.class) {
                if(INSTANCE==null)
                {
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),TaskRoomDatabase.class,Database_name)
                            .addCallback(sRoomDatabase)
                    .build();
                }

            }
        }
        return INSTANCE;
    }

    public abstract TaskDao taskDao();



}
