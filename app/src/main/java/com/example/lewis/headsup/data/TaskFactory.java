package com.example.lewis.headsup.data;

import java.util.Date;
import java.util.List;

/**
 * Created by Lewis on 04/03/2019.
 */

public class TaskFactory {

    private static TaskFactory instance;

    static public TaskFactory getInstance(){
        if(instance == null)
            instance = new TaskFactory();

        return instance;
    }

    public Task createTask(String title){
        return new Task(title, new Date());
    }

}
