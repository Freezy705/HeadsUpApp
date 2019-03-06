package com.example.lewis.headsup;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lewis.headsup.data.Task;

import java.util.ArrayList;

/**
 * Created by Admin on 24/02/2019.
 */

public class TaskListAdapter extends ArrayAdapter {

    //private StorageReference storageRef;
    private final Activity context;
    private ArrayList<Task> data;

    public TaskListAdapter(@NonNull Activity context, ArrayList<Task> data) {
        super(context,R.layout.listview_row , data);
        this.context = context;
        this.data = data;
    }

    public View getView(final int position, final View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null,true);
        Task task = data.get(position);

        TextView clubNameText = rowView.findViewById(R.id.task_title);
        clubNameText.setText(task.getTitle());

        TextView clubStepText = rowView.findViewById(R.id.task_current_step);
        clubStepText.setText(task.getCurrent().getBody());

        rowView.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                Snackbar.make(view,"Placeholder",200);
            }
        });

        return rowView;
    };
}
