package com.example.project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Details extends AppCompatActivity {
    ConstraintLayout layout;
    TextView tvTitle, tvOrigin, tvSize, tvCost,  tvCharacteristics, tvCategory, tvDescription;
    String jsonDuck;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Getting required info for ducks
        jsonDuck = getIntent().getStringExtra("keyDuck");
        Duck duck = gson.fromJson(jsonDuck, Duck.class);

        imageSwitch(duck);

        // Setting views
        tvTitle = findViewById(R.id.tv_item_title);
        tvTitle.setText(duck.getName());

        tvOrigin = findViewById(R.id.tv_item_origin);
        tvOrigin.setText(duck.getOrigin());

        tvSize = findViewById(R.id.tv_item_size);
        tvSize.setText("Average weight in grams: " + duck.getSize() + "g.");

        tvCost = findViewById(R.id.tv_item_cost);
        tvCost.setText("Average market price in USD: $" + duck.getCost() + ".");

        tvCategory = findViewById(R.id.tv_item_category);
        tvCategory.setText(duck.getCategory().toUpperCase());

        tvCharacteristics = findViewById(R.id.tv_item_characteristics);
        tvCharacteristics.setText(duck.getCharacteristics().toUpperCase());

        tvDescription = findViewById(R.id.tv_item_description);
        String info = "The " + duck.getName()
                + " is part of the "
                + duck.getCategory()
                + " category."
                + "\nHere is an interesting fact about it:\n\n"
                + duck.getCuriosity();
        String current = "\n\nThe current market price for a " + duck.getName()
                + " is currently an amount of about " + duck.getCost()
                + " USD according to a well established and widely known search engine that is frequented immensely.";
        tvDescription.setText(info + current);

        layout = findViewById(R.id.layout_details);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Log.d("a21liltr", "Displaying details about " + duck.getName() + ". ");
    }

    private void imageSwitch(Duck duck) {
        ImageView image = findViewById(R.id.imageView);
        String resource;
        switch (duck.getName()) {
            case "Crested Duck":
                resource = "https://cdn.pixabay.com/photo/2021/04/16/13/09/crested-duck-6183539_960_720.png";
                break;
            case "Mandarin Duck":
                resource = "https://cdn.pixabay.com/photo/2017/02/11/13/24/animal-2057645_960_720.png";
                break;
            case "Rubber Duck":
                resource = "https://images.unsplash.com/photo-1632877943287-64636ca57b7e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1974&q=80";
                break;
            case "White Pekin":
                resource = "https://images.unsplash.com/photo-1592676600551-fa8a3029947b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1025&q=80";
                break;
            default:
                resource = "R.drawable.unknown";
        }
        loadImage(resource, image);
    }

    private void loadImage(String resource, ImageView image) {
        Picasso.get().load(resource)
                .error(R.drawable.unknown)
                .placeholder(R.drawable.egg)
                .centerCrop()
                .fit()
                .into(image);
    }
}