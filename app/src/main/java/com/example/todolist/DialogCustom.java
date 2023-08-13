package com.example.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import java.util.ArrayList;

public class DialogCustom extends AppCompatDialogFragment {

    private EditText name_task;
    ListViewCustom adapter;
    ArrayList<String> tasks;

    public DialogCustom (ListViewCustom adapter, ArrayList<String> tasks) {
        this.adapter = adapter;
        this.tasks = tasks;
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
                            tasks.add(name_task.getText().toString());
                            adapter.setTasks(tasks);
                        }

                    }
                });


        return builder.create();
    }
}
