package com.example.lewis.headsup.data;

import android.app.Activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lewis on 04/03/2019.
 */

public class User implements Serializable{

    private int taskNo = 0, tasksCompleted = 0,
                tasksFinished = 0, tasksInProgress = 0;
    private List<Task> tasks;

    private User instance;

    public User(){
        tasks = new ArrayList<Task>();
    }

    public User getUser(Activity context){

        if(instance == null){
            loadUser(context);
            if(instance == null)
            instance = createNewUser(context);
        }
        return instance;
    }

    private User loadUser(Activity context){
            try {
                FileInputStream fileIn = new FileInputStream(context.getDir("user_inf", Activity.MODE_PRIVATE));
                ObjectInputStream obj = new ObjectInputStream(fileIn);
                instance = (User) obj.readObject();
                obj.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
    return instance;
    }

    private User createNewUser(Activity context){

        instance = new User();
        updateUser(context);
        return instance;

    }



    public void updateUser(Activity context){
        FileOutputStream fileout = null;
        try {
            fileout = new FileOutputStream(context.getDir("user_inf", Activity.MODE_PRIVATE));
            ObjectOutputStream obj = new ObjectOutputStream(fileout);
            obj.writeObject(instance);
            obj.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
