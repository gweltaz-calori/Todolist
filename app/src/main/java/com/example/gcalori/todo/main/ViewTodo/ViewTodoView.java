package com.example.gcalori.todo.main.ViewTodo;

/**
 * Created by gcalori on 26/03/2018.
 */

public interface ViewTodoView {

    void clearTasks();

    void showAddTask();

    void saveTasksToPreferences();

    void loadTasksFromPreferences();

}
