package com.example.gcalori.todo.main;

/**
 * Created by gcalori on 26/03/2018.
 */

public class ViewTodoPresenterImpl implements ViewTodoPresenter {

    private ViewTodoView view;

    public ViewTodoView getView() {
        return view;
    }

    ViewTodoPresenterImpl(ViewTodoView view) {
        this.view = view;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onClear() {
        System.out.println("on clear");
        this.view.clearTasks();
    }

    @Override
    public void showAddTask() {
        this.view.showAddTask();
    }

    @Override
    public void saveTasksToPreferences() {
        this.view.saveTasksToPreferences();
    }

    @Override
    public void loadTasksFromPreferences() {
        this.view.loadTasksFromPreferences();
    }

}
