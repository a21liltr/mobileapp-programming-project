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
    private List<Duck> ducks;
    private Gson gson = new Gson();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        String url = "https://mobprog.webug.se/json-api?login=a21liltr";
        new JsonTask(this).execute(url);


    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
        Type duckListType = new TypeToken<List<Duck>>() {}.getType();
        ducks = gson.fromJson(json, duckListType);
        store(ducks);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(ducks, this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        sharedPreferences = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = gson.toJson(list);

        editor = sharedPreferences.edit();
        editor.remove("key").commit();
        editor.putString("key", json);
        editor.commit();
    }

    public void launchDetails(int position) {
        Duck duck = ducks.get(position);

        Intent details = new Intent(MainActivity.this, Details.class);
        details.putExtra("keyPosition", position);

        Log.d("a21liltr", "Displaying details about " + duck.getName() + ". ");
        startActivity(details);
    }

    public void launchAbout() {
        Intent about = new Intent(MainActivity.this, About.class);
        Log.d("a21liltr", "Displaying ABOUT-page.");
        startActivity(about);
    }
}
