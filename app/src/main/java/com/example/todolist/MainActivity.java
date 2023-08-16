package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDoalog;
    private MyDatabaseHelper db;

    private List<Task> taskList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTaskButton = (Button) findViewById(R.id.add_task);
        ArrayList<Task> tasks = new ArrayList<Task>();

        db = new MyDatabaseHelper(this);
        db.openDatabase();
        tasks = (ArrayList<Task>) db.getAllTasks();

        /*for (int i = 0; i < taskList.size(); i++) {
            tasks.add(taskList.get(i).getTitle());
        }*/

        /*progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();*/


        ListView list_view_tasks = (ListView) findViewById(R.id.list);
        ListViewCustom adapter = new ListViewCustom(getApplicationContext(), tasks);
        list_view_tasks.setAdapter(adapter);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogFieldAddTask(adapter, adapter.getTasks());
            }
        });


        /*Create handle for the RetrofitInstance interface*/
        /*ApiTask service = RetrofitClientInstance.getRetrofitInstance().create(ApiTask.class);
        Call<List<Task>> call = service.getAllTask();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                progressDoalog.dismiss();


                for (int i = 0; i < 9; i++) {
                    tasks.add(response.body().get(i).getTitle());
                    adapter.setTasks(tasks);
                }

                System.out.println("VALEUR TO DO LIST : -------------- " + response.body().size());
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                progressDoalog.dismiss();

            }

        });*/

    }

    private void dialogFieldAddTask(ListViewCustom adapter,  ArrayList<Task> tasks ) {
        DialogCustom dialogCustom = new DialogCustom(adapter, tasks, this);
        dialogCustom.show(getSupportFragmentManager(), "Dialogue Custom");
    }
}