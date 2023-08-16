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


public class ListViewCustom extends BaseAdapter {

    Context context;
    ArrayList<Task> tasks = new ArrayList<Task>();


    LayoutInflater inflater;

    private MyDatabaseHelper db;


    public  ListViewCustom(Context context,  ArrayList<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
        inflater = LayoutInflater.from(context);
        db = new MyDatabaseHelper(context);

    }

    /*public ArrayList<String> addElement(ArrayList<String> arr, String element) {
        String [] newArr = new String[arr.size() + 1];
        newArr[0] = element;
        for (int i = 0; i < arr.size(); i++) {
            // newArr[i + 1] = arr[i];
            newArr[i + 1] = arr.get(i);
        }
        tasks = new ArrayList<String>();
        Collections.addAll(tasks, newArr);
        return tasks;
    }*/

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

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public ArrayList<Task> getTasks () {
        return tasks;
    }

    public void buttonSup (View view, int i) {
        Button button = (Button)  view.findViewById(R.id.supprission);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(v.getContext(), tasks.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                // tasks.remove(i);
                db.openDatabase();
                long result = db.deleteTask(tasks.get(i).getId());
                tasks = (ArrayList<Task>) db.getAllTasks();
                setTasks(tasks);

            }
        });


    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView textView = (TextView) view.findViewById(R.id.textViewCustum);
        buttonSup(view, i);
        textView.setText(tasks.get(i).getTitle());
        return view;
    }
}
