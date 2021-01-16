package com.uttom41mitra.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View view) {
        switch (view.getId()){
            case R.id.noteBtn:
                startActivity(new Intent(MainActivity.this,NotesActivity.class));
                break;
            case R.id.dailyBtn:
                startActivity(new Intent(MainActivity.this,DailyActivity.class));
                break;
            case R.id.taskBtn:
                startActivity(new Intent(MainActivity.this,TaskActivity.class));
                break;
            case R.id.expensesBtn:
                startActivity(new Intent(MainActivity.this,ExpensesActivity.class));
                break;
        }
    }
}