package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.appbar.AppBarLayout;

public class About extends AppCompatActivity {
    Toolbar toolbar;
    AppBarLayout toolbarLayout;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        toolbar = findViewById(R.id.toolbar_collapsing);
        setSupportActionBar(toolbar);

        toolbarLayout = findViewById(R.id.appBarLayout);
        toolbarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("a21liltr", "Closing ABOUT-page.\nGoing back to MAIN-page.");
                finish();
            }
        });

        WebViewClient webViewClient = new WebViewClient();
        webView = findViewById(R.id.wv_about);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebViewClient(webViewClient);

        // Shows html file from assets as about page.
        webView.loadUrl("file:///android_asset/about.html");
    }
}