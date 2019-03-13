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

    public void addTask(Task task){
        tasks.add(task);
        taskNo++;
        tasksInProgress++;
    }

    public void addStep(TaskStep taskStep, int pointer){
        tasks.get(pointer).addSteps(taskStep);
    }

    public int[] getStates(){
        int[] states = new int[]{0,0,0};

        for (Task t : tasks){
            if(t.finished)
                states[0]++;
            else{
                if (t.isUntouched())
                    states[1]++;
                else
                    states[2]++;
            }
        }
        System.out.println(states[0]);
        System.out.println(states[1]);
        System.out.println(states[2]);

        return states;
    }
}
