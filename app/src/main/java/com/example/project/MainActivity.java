package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener, IRecyclerView {
    private List<Duck> ducks;
    private Gson gson = new Gson();
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String selectedFilter = "all";
    private ArrayList<Duck> filteredDucks;

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
        Log.d("a21liltr", json);
        Type duckListType = new TypeToken<List<Duck>>() {}.getType();
        ducks = gson.fromJson(json, duckListType);

        recyclerView = findViewById(R.id.recycler_view);
        filteredDucks = filterList(selectedFilter);
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

    public void launchDetails(int position) {
        String jsonDuck;
        Duck duck = filteredDucks.get(position);
        jsonDuck = gson.toJson(duck);

        Intent details = new Intent(MainActivity.this, Details.class);

        // Passing clicked duck object as json string to details activity.
        details.putExtra("keyDuck", jsonDuck);

        Log.d("a21liltr", "Displaying details about " + filteredDucks.get(position) + ".");
        startActivity(details);
    }

    public void launchAbout() {
        Intent about = new Intent(MainActivity.this, About.class);
        Log.d("a21liltr", "Displaying ABOUT-page.");
        startActivity(about);
    }

    private ArrayList<Duck> filterList(String status) {
        selectedFilter = status;
        ArrayList<Duck> filteredList = new ArrayList<Duck>();
        for(Duck duck: ducks) {
            if(duck.getCategory().toLowerCase().contains(status.toLowerCase())) {
                filteredList.add(duck);
            }
            if(selectedFilter.equals("all")) {
                filteredList.add(duck);
            }
        }
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(filteredList, this, this);
        recyclerView.setAdapter(adapter);
        storeFilter(selectedFilter);

        return filteredList;
    }

    public void filterAll(View view) {
        filteredDucks = filterList("all");
    }

    public void filterFood(View view) {
        filteredDucks = filterList("food");
    }

    public void filterPets(View view) {
        filteredDucks = filterList("pets");
    }

    public void filterToys(View view) {
        filteredDucks = filterList("toys");
    }

    private void storeFilter(String selectedFilter) {
        sharedPreferences = getSharedPreferences("myFilter", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.remove("myFilter").commit();
        editor.putString("myFilter", selectedFilter);
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String defaultFilter = "all";
        sharedPreferences = getSharedPreferences("myFilter", MODE_PRIVATE);
        selectedFilter = sharedPreferences.getString("myFilter", defaultFilter);
    }
}
