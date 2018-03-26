package com.example.gcalori.todo.main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gcalori.todo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewTodoActivity extends AppCompatActivity implements ViewTodoView,View.OnClickListener {

    private ViewTodoPresenter presenter;

    @BindView(R.id.todoList)
    RecyclerView mTodoListRecyclerView;

    @BindView(R.id.clearButton)
    Button clearButton;

    @BindView(R.id.addTaskButton)
    FloatingActionButton addTaskButton;

    private TodoListAdapter mTodoListAdapter;

    private RecyclerView.LayoutManager mTodoListLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new ViewTodoPresenterImpl(this);
        presenter.loadTasksFromPreferences();

        mTodoListLayoutManager = new LinearLayoutManager(this);
        mTodoListRecyclerView.setLayoutManager(mTodoListLayoutManager);
        mTodoListAdapter = new TodoListAdapter(getApplicationContext());
        mTodoListRecyclerView.setAdapter(mTodoListAdapter);



        clearButton.setOnClickListener(this);
        addTaskButton.setOnClickListener(this);

    }

    @Override protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.clearButton:
                presenter.onClear();
                break;
            case R.id.addTaskButton:
                presenter.showAddTask();
                break;
        }


    }


    @Override
    public void clearTasks() {
        mTodoListAdapter.clearTasks();
    }


    public void showAddTask() {
        Intent intent = new Intent(getApplicationContext(),CreateTodoActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Task task = (Task) data.getSerializableExtra("task");
                mTodoListAdapter.addTask(task);
                presenter.saveTasksToPreferences();
            }
        }
    }

    public void saveTasksToPreferences() {
        SharedPreferences preferences = getSharedPreferences("todoPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String jsonTasks = new Gson().toJson(TodoManager.getInstance().getTasks());
        editor.putString("tasksContainer",jsonTasks);
        editor.apply();
    }

    public void loadTasksFromPreferences() {
        Type taskTypeToken = new TypeToken<ArrayList<Task>>(){}.getType();
        SharedPreferences preferences = getSharedPreferences("todoPref",MODE_PRIVATE);
        String jsonTasks = preferences.getString("tasksContainer","[]");
        ArrayList<Task> tasks = new Gson().fromJson(jsonTasks,taskTypeToken);
        TodoManager.getInstance().setTasks(tasks);
    }

}
