package com.example.lewis.headsup.data;

import java.util.Date;

/**
 * Created by Lewis on 03/03/2019.
 */

public class TaskStep{

    private String body;
    private Date timeStarted;
    private Date timeFinished;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
}
