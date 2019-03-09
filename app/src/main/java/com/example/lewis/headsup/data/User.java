package com.example.lewis.headsup.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Lewis on 04/03/2019.
 */

public class User implements Serializable{

    private int taskNo = 0, tasksCompleted = 0,
                tasksInProgress = 0;

    private ArrayList<Task> tasks;

    public User(){
        tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addStep(Task task){
        tasks.add(task);
        taskNo++;
        tasksInProgress++;
    }
}
