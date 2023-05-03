package com.example.project;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
    ConstraintLayout layout;
    TextView tvTitle, tvQuality, tvDescription;
    String dataName, dataCharacter, dataInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvTitle = findViewById(R.id.tv_item_title);
        dataName = getIntent().getStringExtra("keyName");
        tvTitle.setText(dataName);

        tvQuality = findViewById(R.id.tv_item_characteristics);
        dataCharacter = getIntent().getStringExtra("keyCharacter");
        tvQuality.setText(dataCharacter.toUpperCase());

        tvDescription = findViewById(R.id.tv_item_description);
        dataInfo = getIntent().getStringExtra("keyInfo");
        tvDescription.setText(dataInfo);

        int dataPosition = getIntent().getIntExtra("keyPosition", -1);
        imageSwitch(dataPosition);

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
                resource = "@drawable/food";
                break;
            case 3:
                resource = "@drawable/bath";
                break;
            default:
                resource = "@drawable/unknown";
                break;
        }
        Picasso.get().load(resource).into(image);
    }
}