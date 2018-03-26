package com.example.gcalori.todo.main;

/**
 * Created by gcalori on 26/03/2018.
 */

public class CreateTodoPresenterImpl implements CreateTodoPresenter {

    private CreateTodoView view;

    public CreateTodoView getView() {
        return view;
    }

    CreateTodoPresenterImpl(CreateTodoView view) {
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
    public void onValidate() {
        this.view.validateTask();
    }




}
