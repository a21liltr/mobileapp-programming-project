package com.example.project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private String json = "https://mobprog.webug.se/json-api?login=a21liltr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
