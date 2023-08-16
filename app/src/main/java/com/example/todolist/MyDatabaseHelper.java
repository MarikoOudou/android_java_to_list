package com.example.todolist;

import static android.provider.Telephony.TextBasedSmsColumns.STATUS;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "DB_TASK.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "task";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";

    private SQLiteDatabase db;



    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT );";

        sqLiteDatabase.execSQL(query);
    }

    public long insertTask(Task task){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, task.getTitle());
        return db.insert(TABLE_NAME, null, cv);
    }

    @SuppressLint("Range")
    public List<Task> getAllTasks(){
        List<Task> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TABLE_NAME, null, null, null, null, null, COLUMN_ID +" DESC", null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        Task task = new Task();
                        task.setId(cur.getInt(cur.getColumnIndex(COLUMN_ID)));
                        task.setTitle(cur.getString(cur.getColumnIndex(COLUMN_TITLE)));
                        taskList.add(task);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return taskList;
    }

    public long deleteTask(int id){
        return db.delete(TABLE_NAME, COLUMN_ID + "= ?", new String[] {String.valueOf(id)});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }
}
