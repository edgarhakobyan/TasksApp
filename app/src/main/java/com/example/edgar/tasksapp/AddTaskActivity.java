package com.example.edgar.tasksapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.edgar.tasksapp.db.TaskDbHelper;
import com.example.edgar.tasksapp.db.TaskProvider;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button addTask = (Button) findViewById(R.id.add);
        final TaskDbHelper mDbHelper = new TaskDbHelper(this);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView title = (TextView) findViewById(R.id.title);
                TextView description = (TextView) findViewById(R.id.description);

                TaskDbHelper mTaskDbHelper = TaskProvider.getTaskProvider(getBaseContext());
                SQLiteDatabase dbWrite = mTaskDbHelper.getWritableDatabase();
                TaskProvider.insertTaskIntoDb(dbWrite, title.getText()
                .toString(), description.getText().toString());

                Intent addTaskIntent = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivity(addTaskIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
