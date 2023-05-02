package com.example.project;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String json = "https://mobprog.webug.se/json-api?login=a21liltr";
    private ArrayList<Duck> ducks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new JsonTask(this).execute(json);
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
    }


    /*
        TODO: RecyclerView med JSON-data
          JSON-object w/ ID, Login, 3 <= attributes
          5 <= JSON-object

        TODO: Separate "about"-screen
          -Target audience for app

        TODO: Include screenshots/videos
      */
}
