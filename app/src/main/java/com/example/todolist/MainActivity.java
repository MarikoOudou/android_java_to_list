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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTaskButton = (Button) findViewById(R.id.add_task);
        ArrayList<String> tasks = new ArrayList<String>();

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();


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
        ApiTask service = RetrofitClientInstance.getRetrofitInstance().create(ApiTask.class);
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
                // generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                progressDoalog.dismiss();

            }

        });

    }

    private void dialogFieldAddTask(ListViewCustom adapter,  ArrayList<String> tasks ) {
        DialogCustom dialogCustom = new DialogCustom(adapter, tasks);
        dialogCustom.show(getSupportFragmentManager(), "Dialogue Custom");
    }
}