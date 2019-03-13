package com.example.lewis.headsup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lewis.headsup.data.Task;
import com.example.lewis.headsup.data.TaskStep;

import java.util.ArrayList;

/**
 * Created by Admin on 24/02/2019.
 */

public class EditTaskAdapter extends ArrayAdapter {

    private final Activity context;
    private ArrayList<TaskStep> data;
    private TaskStep step;
    private TextView stepText;

    public EditTaskAdapter(@NonNull Activity context, ArrayList<TaskStep> data) {
        super(context,R.layout.listview_row,data);
        this.context = context;
        this.data = data;
    }

    public View getView(final int position, final View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.taskview_row, null,true);
        step = data.get(position);

        TextView stepText = rowView.findViewById(R.id.txt_step_desc);
        stepText.setText(step.getBody());


        rowView.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                editContent(position);
                setText();
            }
        });

        Button button =  rowView.findViewById(R.id.btn_delete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContent(position);
            }
        });

        return rowView;
    };

    private void setText(){
            stepText.setText(step.getBody());
    }

    private void editContent(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Edit Step Text");

        final EditText input = new EditText(context);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                step.setBody(input.getText().toString());
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    private void deleteContent(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Are you sure you wish to delete this step?");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                data.remove(position);
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
