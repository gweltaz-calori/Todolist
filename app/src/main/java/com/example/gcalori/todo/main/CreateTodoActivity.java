package com.example.gcalori.todo.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.gcalori.todo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateTodoActivity extends AppCompatActivity implements CreateTodoView,View.OnClickListener {

    private CreateTodoPresenter presenter;


    @BindView(R.id.importantCheckbox)
    CheckBox importantCheckbox;

    @BindView(R.id.contentTextField)
    EditText contentEditText;

    @BindView(R.id.validateButton)
    Button validateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new CreateTodoPresenterImpl(this);
        validateButton.setOnClickListener(this);
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
        if(v.getId() == R.id.validateButton) {
            presenter.onValidate();
        }
    }

    public void validateTask() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("task",new Task(contentEditText.getText().toString(), importantCheckbox.isChecked()));
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

}
