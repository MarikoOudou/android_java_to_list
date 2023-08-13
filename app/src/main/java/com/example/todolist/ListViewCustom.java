package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ListViewCustom extends BaseAdapter {

    Context context;
    ArrayList<String> tasks = new ArrayList<String>();


    LayoutInflater inflater;

    public  ListViewCustom(Context context,  ArrayList<String> tasks) {
        this.context = context;
        this.tasks = tasks;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }


    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setTasks(ArrayList<String> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public void buttonSup (View view, int i) {
        Button button = (Button)  view.findViewById(R.id.supprission);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(v.getContext(), tasks.get(i), Toast.LENGTH_SHORT).show();
                tasks.remove(i);
                notifyDataSetChanged();

            }
        });


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView textView = (TextView) view.findViewById(R.id.textViewCustum);
        buttonSup(view, i);
        textView.setText(tasks.get(i));
        return view;
    }
}
