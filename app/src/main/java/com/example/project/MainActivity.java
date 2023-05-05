package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
    private Gson gson = new Gson();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_collapsing);
        setSupportActionBar(toolbar);

        new JsonTask(this).execute(url);
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
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
        launchDetails(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_option_about) {
            launchAbout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void store(List<Duck> list) {
        SharedPreferences sharedP;
        SharedPreferences.Editor editor;

        sharedP = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = gson.toJson(list);

        editor = sharedP.edit();
        editor.remove("key").commit();
        editor.putString("key", json);
        editor.commit();
    }

    public void launchDetails(int position) {
        Duck duck = ducks.get(position);
        String info = "The " + duck.getName() + " is " + duck.getCharacteristics() + ". "
                + "\nFun fact about it:\n\n" + duck.getCuriosity();

        store(ducks);

        Intent details = new Intent(MainActivity.this, Details.class);

        details.putExtra("keyPosition", position);
        details.putExtra("keyInfo", info);

        System.out.println("Displaying details about " + duck.getName() + ". ");
        startActivity(details);
    }

    public void launchAbout() {
        Intent about = new Intent(MainActivity.this, About.class);
        startActivity(about);
    }


    /*
        TODO: Separate "about"-screen
          -Target audience for app

        TODO: Include screenshots/videos
      */
}
