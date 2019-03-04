package com.example.lewis.headsup.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lewis on 04/03/2019.
 */

public class User implements Serializable{

    private int taskNo, tasksCompleted, tasksFinished, tasksInProgress;
    private List<Task> tasks;


}
