package com.example.gcalori.todo.main;

import java.io.Serializable;

/**
 * Created by gcalori on 26/03/2018.
 */

public class Task implements Serializable {
    private String text;
    private boolean important;

    public Task(String text, boolean important) {
        this.text = text;
        this.important = important;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
