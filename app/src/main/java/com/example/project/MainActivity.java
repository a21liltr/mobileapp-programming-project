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
import androidx.cardview.widget.CardView;
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
        storeDucks(ducks);

        recyclerView = findViewById(R.id.recycler_view);
        filterList(selectedFilter);
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

    private void storeDucks(List<Duck> list) {
        sharedPreferences = getApplicationContext().getSharedPreferences("myDucks", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = gson.toJson(list);

        editor = sharedPreferences.edit();
        editor.remove("keyList").commit();
        editor.putString("keyList", json);
        editor.commit();
    }

    public void launchDetails(int position) {
        Duck duck = ducks.get(position);

        Intent details = new Intent(MainActivity.this, Details.class);
        details.putExtra("keyPosition", position);

        startActivity(details);
    }

    public void launchAbout() {
        Intent about = new Intent(MainActivity.this, About.class);
        Log.d("a21liltr", "Displaying ABOUT-page.");
        startActivity(about);
    }

    private void filterList(String status) {
        selectedFilter = status;
        ArrayList<Duck> filteredList = new ArrayList<Duck>();
        for(Duck duck: ducks) {
            if(duck.getCategory().toLowerCase().contains(status.toLowerCase())) {
                filteredList.add(duck);

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(filteredList, this, this);
                recyclerView.setAdapter(adapter);
                Log.d("a21liltr", "Category " + duck.getCategory() + " was clicked.");
            }
            if(selectedFilter.equals("all")) {
                filterAll(recyclerView);
            }
        }
        //storeFilter(selectedFilter);
        storeDucks(filteredList);
    }

    public void filterAll(View view) {
        selectedFilter = "all";
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(ducks, this, this);
        recyclerView.setAdapter(adapter);
        CardView cardAll = findViewById(R.id.card_filter_all);
    }

    public void filterFood(View view) {
        filterList("food");
    }

    public void filterPets(View view) {
        filterList("pets");
    }

    public void filterToys(View view) {
        filterList("toys");
    }

    private void storeFilter(String selectedFilter) {
        sharedPreferences = getApplicationContext().getSharedPreferences("myFilter", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.remove("myFilter").commit();
        editor.putString("myFilter", selectedFilter);
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("a21liltr", "Activity resumed.");
        String defaultFilter = "all";
        sharedPreferences = getSharedPreferences("myFilter", MODE_PRIVATE);
        selectedFilter = sharedPreferences.getString("myFilter", defaultFilter);
    }
}
