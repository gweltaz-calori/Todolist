package com.example.gcalori.todo.main;

/**
 * Created by gcalori on 26/03/2018.
 */

public interface ViewTodoPresenter {
    void onResume();

    void onDestroy();

    void onClear();

    void showAddTask();

    void saveTasksToPreferences();

    void loadTasksFromPreferences();

}
