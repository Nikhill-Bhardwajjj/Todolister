package com.bawp.todoister.util;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bawp.todoister.model.Priority;
import com.bawp.todoister.model.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String formatDate(Date date ){
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("EEE, MMM d");
        return simpleDateFormat.format(date);

    }
    public static void hideSoftKeyboard(View view){
        InputMethodManager im = (InputMethodManager)  view.
                getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
         im.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    public static int priorityColor(Task task) {
        int color;
        if(task.getTask_priority()== Priority.HIGH){
            color = Color.argb(200,255,99,71);
        }
        else if(task.getTask_priority()== Priority.MEDIUM)
        {
            color = Color.argb(200,245,199,26);
        }
        else{

            color = Color.argb(200,65,105,245);
        }
        return color;
    }
}

