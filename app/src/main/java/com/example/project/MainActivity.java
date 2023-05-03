package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener, IRecyclerView {

    private final String url = "https://mobprog.webug.se/json-api?login=a21liltr";
    private List<Duck> ducks;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new JsonTask(this).execute(url);
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);

        Gson gson = new Gson();
        Type duckListType = new TypeToken<List<Duck>>() {}.getType();
        ducks = gson.fromJson(json, duckListType);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(ducks, this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (Duck d : ducks) {
            Log.d("a21liltr", d.getName());
        }
    }

    @Override
    public void onItemClick(int position) {
        System.out.println("Item " + position + " was clicked");
        // TODO: detailed info on second screen
    }


    /*
        TODO: Separate "about"-screen
          -Target audience for app

        TODO: Include screenshots/videos
      */
}
