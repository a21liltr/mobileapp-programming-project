package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class About extends AppCompatActivity {
    Toolbar toolbar;
    ImageView header;
    String url =
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        toolbar = findViewById(R.id.toolbar_collapsing);
        setSupportActionBar(toolbar);
    }
}