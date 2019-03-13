package com.example.lewis.headsup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lewis.headsup.data.Task;
import com.example.lewis.headsup.data.TaskStep;
import com.example.lewis.headsup.data.User;
import com.example.lewis.headsup.data.UserHandler;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {

    private String taskTitle;
    private ArrayList<TaskStep> insert;
    private ListView stepListView;
    private User user;
    private Task out;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user = UserHandler.getUser(this);
        if(user == null)
            Toast.makeText(this,"null user",Toast.LENGTH_SHORT);
        insert = new ArrayList<>();

        setTaskTitle();
        final EditTaskAdapter listAdapter = new EditTaskAdapter(this,insert);
        stepListView = findViewById(R.id.edit_listview);
        stepListView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //testTask();
                out = new Task(taskTitle,insert);
                if(out != null){
                    user.addTask(out);
                    setResult(RESULT_OK,null);
                    finish();
                }
                //AAToast.makeText(context,"null ref",Toast.LENGTH_SHORT).show();

            }
        });

        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setBody();
                listAdapter.notifyDataSetChanged();

            }
        });
    }

    private void setTaskTitle(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Please enter the title of the Task.");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String in = input.getText().toString();
                taskTitle = in;
                setTitle(in);
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setResult(RESULT_CANCELED,null);
                finish();
            }
        });

        builder.show();
    }

    private void setBody(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Please enter the body of the step.");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println(input);
                insert.add(new TaskStep(input.getText().toString()));
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setResult(RESULT_CANCELED,null);
                finish();
            }
        });

        builder.show();
    }

    private void testTask(){

        insert.add(new TaskStep("Test1"));
        insert.add(new TaskStep("Test2"));
        insert.add(new TaskStep("Test3"));

        taskTitle = "Test Name";
    }
}
