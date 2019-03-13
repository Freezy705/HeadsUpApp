package com.example.lewis.headsup;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lewis.headsup.data.Task;
import com.example.lewis.headsup.data.User;
import com.example.lewis.headsup.data.UserHandler;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected Activity context;
    private ArrayList<Task> tasks;
    private User user;
    private ListView taskListView;
    private static int REQUEST_READ_CONTACTS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        user = UserHandler.getUser(this);
        save();
        tasks = user.getTasks();

        final TaskListAdapter listAdapter = new TaskListAdapter(this, tasks);
        taskListView = findViewById(R.id.task_list);
        taskListView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new ClickListener(this));

        Button addTask = findViewById(R.id.btn_add);
        addTask.setOnClickListener(new AddClickListener(this));


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_activtity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id){
            case R.id.nav_career :

                Intent intent = new Intent(context,CareerActivity.class);
                startActivity(intent);

                break;
            case R.id.nav_completed :

                break;
            case R.id.nav_timeline :

                break;
            case R.id.nav_share :

                break;
            case R.id.nav_send :

                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class ClickListener implements View.OnClickListener {
        private Activity context;

        public ClickListener (Activity context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, CareerActivity.class);
            startActivity(intent);
        }
    }

    private class AddClickListener implements View.OnClickListener {
        private Activity context;

        public AddClickListener (Activity context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, AddTaskActivity.class);
            startActivityForResult(intent,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            save();
            Intent refresh = new Intent(this, HomeActivity.class);
            startActivity(refresh);
            this.finish();
        }
    }

    private void save(){

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE,
                         Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            UserHandler.saveUser();
                        }else{
                            Toast.makeText(context,"Saved",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
