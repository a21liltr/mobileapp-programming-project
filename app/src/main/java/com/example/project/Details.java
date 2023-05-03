package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

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

        layout = findViewById(R.id.layout_details);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}