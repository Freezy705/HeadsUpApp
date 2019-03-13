package com.example.lewis.headsup.data;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import androidx.annotation.NonNull;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Lewis on 09/03/2019.
 */

public class UserHandler {

    private static Activity context;
    private static User instance;

    public static User getUser(Activity in){

        if(context == null)
            context = in;

        /*if(instance == null){
            loadUser();
            if(instance == null)
                instance = createNewUser();
        } */

        if(instance == null){
            instance = new User();
        }
        return instance;
    }

    private static User loadUser(){
    try {
        FileInputStream fileIn = new FileInputStream(context.getDir("user_inf", MODE_PRIVATE));
        ObjectInputStream obj = new ObjectInputStream(fileIn);
        instance = (User) obj.readObject();
        obj.close();

    } catch (Exception ex) {
        ex.printStackTrace();
        return null;
    }
    return instance;

    }

    private static User createNewUser(){

        instance = new User();
        saveUser();
        return instance;

    }

    public static void saveUser(){
        FileOutputStream fileout;
        try {
            fileout = new FileOutputStream(context.getDir("user_inf", MODE_PRIVATE));
            ObjectOutputStream obj = new ObjectOutputStream(fileout);
            obj.writeObject(instance);
            obj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void addTask(Task task){
        instance.addTask(task);
        saveUser();
    }

}
