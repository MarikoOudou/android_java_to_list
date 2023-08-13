package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTaskButton = (Button) findViewById(R.id.add_task);

        ArrayList<String> tasks = new ArrayList<String>();
        /*String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };
        Collections.addAll(tasks, values);*/
        // Collections.reverse(tasks);


        ListView list_view_tasks = (ListView) findViewById(R.id.list);
        ListViewCustom adapter = new ListViewCustom(getApplicationContext(), tasks);
        list_view_tasks.setAdapter(adapter);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogFieldAddTask(adapter, tasks);
            }
        });

    }

    private void dialogFieldAddTask(ListViewCustom adapter,  ArrayList<String> tasks ) {
        DialogCustom dialogCustom = new DialogCustom(adapter, tasks);
        dialogCustom.show(getSupportFragmentManager(), "Dialogue Custom");
    }
}