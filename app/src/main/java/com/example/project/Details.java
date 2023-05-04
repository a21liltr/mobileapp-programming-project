package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ResourceBundle;

public class Details extends AppCompatActivity {
    ConstraintLayout layout;
    TextView tvTitle, tvQuality, tvDescription;
    String dataInfo;
    int dataPosition;
    List<Duck> ducks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ducks = sharedDucks();
        dataPosition = getIntent().getIntExtra("keyPosition", -1);
        Duck duck = ducks.get(dataPosition);

        imageSwitch(dataPosition);

        tvTitle = findViewById(R.id.tv_item_title);
        tvTitle.setText(duck.getName());

        tvQuality = findViewById(R.id.tv_item_characteristics);
        tvQuality.setText(duck.getCharacteristics().toUpperCase());

        tvDescription = findViewById(R.id.tv_item_description);
        dataInfo = getIntent().getStringExtra("keyInfo");
        tvDescription.setText(dataInfo);


        layout = findViewById(R.id.layout_details);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // Loads an url into ImageView using Picasso Library
    private void imageSwitch(int position) {
        ImageView image = findViewById(R.id.imageView);
        String resource;
        switch (position) {
            case 2:
                resource = "https://en.wikipedia.org/wiki/Crested_(duck_breed)#/media/File:Domestic-crested-duck-CamdenME.jpg";
                break;
            case 3:
                resource = "https://en.wikipedia.org/wiki/Rubber_duck#/media/File:Rubber_Duck_(8374802487).jpg";
                break;
            default:
                resource = "@drawable/unknown";
                break;
        }
        Picasso.get().load(resource).into(image);
    }

    List<Duck> sharedDucks(){
        Gson gson = new Gson();
        SharedPreferences sharedP;
        sharedP = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        String getShared = sharedP.getString("key", "");
        List<Duck> ducks = gson.fromJson(getShared, new TypeToken<List<Duck>>(){}.getType());

        return ducks;
    }
}