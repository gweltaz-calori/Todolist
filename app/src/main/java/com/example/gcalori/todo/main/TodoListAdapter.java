package com.example.gcalori.todo.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gcalori.todo.R;

import java.util.ArrayList;

/**
 * Created by gcalori on 26/03/2018.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {

    private Context context;

    public TodoListAdapter(Context context)  {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View row = inflater.inflate(R.layout.row_todo_list, parent, false);

        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Task task = TodoManager.getInstance().getTasks().get(position);

        TextView textView = holder.nameTextView;
        textView.setText(task.getText());

        if(task.isImportant()) {
            textView.setBackgroundColor(this.context.getResources().getColor(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return TodoManager.getInstance().getTasks().size();
    }

    public void addTask(Task task) {
        TodoManager.getInstance().getTasks().add(task);
        notifyItemInserted(TodoManager.getInstance().getTasks().size() - 1);
    }

    public void clearTasks() {
        final int size = TodoManager.getInstance().getTasks().size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                TodoManager.getInstance().getTasks().remove(0);
            }

            notifyItemRangeRemoved(0, size);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.rowTaskTextview);
        }
    }


}
