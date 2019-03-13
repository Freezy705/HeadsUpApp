package com.example.lewis.headsup.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lewis on 02/03/2019.
 */

public class Task {

    private String title;
    private Date timeStarted;
    private Date timeFinished;
    private List<TaskStep> steps;
    private int pointer = 0;
    public boolean finished = false;

    public Task(String titleIn, ArrayList<TaskStep> taskSteps){
        title = titleIn;
        timeStarted = new Date();
        steps =  taskSteps;
    }


    public TaskStep getCurrent(){
        if(steps.size() == 0)
            return null;
        else
        return steps.get(pointer);
    }

    public void nextStep(){
        if(!finished){
            if(pointer == steps.size() - 1){
                finish(new Date());
            }
            pointer ++;
        }
    }

    @Override
    public String toString() {
        return title + "\n" +
                timeStarted.toString() + "\n" +
                timeFinished.toString() + "\n" +
                pointer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTimeStarted() {
        return timeStarted;
    }

    public Date getTimeFinished() {
        return timeFinished;
    }

    public void finish(Date timeFinished) {
        this.timeFinished = timeFinished;
        setFinished();
    }

    public List<TaskStep> getSteps() {
        return steps;
    }

    public boolean isUntouched(){
        return pointer == 0;
    }

    public void setSteps(List<TaskStep> steps) {
        this.steps = steps;
    }

    public void addSteps(TaskStep step){
        steps.add(step);
    }

    public void setFinished(){
        finished = true;
    }
}
