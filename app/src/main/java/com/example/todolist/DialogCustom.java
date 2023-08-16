package com.example.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import java.util.ArrayList;

public class DialogCustom extends AppCompatDialogFragment {

    private EditText name_task;
    ListViewCustom adapter;
    ArrayList<Task> tasks;

    private MyDatabaseHelper db;
    Context context;


    public DialogCustom (ListViewCustom adapter, ArrayList<Task> tasks, Context context) {
        this.adapter = adapter;
        this.tasks = tasks;
        this.context = context;
        db = new MyDatabaseHelper(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_field_task, null);


        name_task = view.findViewById(R.id.edit_taskname);


        builder.setView(view)
                .setTitle("Name of task")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.out.println("------------ GO GO GO " + name_task.getText().toString());

                        if (name_task.getText().length() > 0) {
                            // tasks.add(name_task.getText().toString());
                            //tasks = adapter.addElement(tasks, name_task.getText().toString());
                            Task task_new = new Task();
                            task_new.setTitle(name_task.getText().toString());
                            db.openDatabase();
                            long result = db.insertTask(task_new);

                            if (result == -1) {
                                Toast.makeText(context, "FAILED", Toast.LENGTH_SHORT);
                            } else {
                                Toast.makeText(context, "SUCCESSFULLY!", Toast.LENGTH_SHORT);
                                tasks = (ArrayList<Task>) db.getAllTasks();
                                adapter.setTasks(tasks);

                            }

                        }

                    }
                });


        return builder.create();
    }
}
