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

    public Task(String title, Date timeStarted){
        title = this.title;
        timeStarted = this.timeStarted;

        steps = new ArrayList<TaskStep>();
    }

    public Task(String title, Date timeStarted, int pointer){

        title = this.title;
        timeStarted = this.timeStarted;
        pointer = this.pointer;

        steps = new ArrayList<TaskStep>();
    }

    public String nextStep(){
        pointer ++;
        return steps.get(pointer).getBody();
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

    public void setTimeStarted(Date timeStarted) {
        this.timeStarted = timeStarted;
    }

    public Date getTimeFinished() {
        return timeFinished;
    }

    public void setTimeFinished(Date timeFinished) {
        this.timeFinished = timeFinished;
    }

    public List<TaskStep> getSteps() {
        return steps;
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
