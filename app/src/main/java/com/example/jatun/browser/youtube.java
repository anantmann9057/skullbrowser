package com.example.jatun.browser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class youtube extends AppCompatActivity {
    WebView wb2;
    ProgressBar pb2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        wb2=(WebView)findViewById(R.id.webview2);
        pb2=(ProgressBar)findViewById(R.id.progressBar2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Youtube");
        setSupportActionBar(toolbar);
        wb2.setWebChromeClient(new WebChromeClient()
        {

            public void onPageStarted(WebView view, String url, Bitmap favicon){
                // Do something on page loading started
                // Visible the progressbar
                pb2.setVisibility(View.VISIBLE);
            }


            public void onPageFinished(WebView view, String url){
                // Do something when page loading finished
                youtube.this.setTitle(view.getTitle());
                Toast.makeText(getApplicationContext(),"Page Loaded.",Toast.LENGTH_SHORT).show();
            }
        });
        wb2.setWebViewClient(new WebViewClient(){
            public void onProgressChanged(WebView view, int newProgress){
                // Update the progress bar with page loading progress
                pb2.setProgress(newProgress);
                if(newProgress == 100){
                    // Hide the progressbar
                    pb2.setVisibility(View.GONE);
                }
            }
        });
        wb2.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wb2.getSettings().setJavaScriptEnabled(true);
        wb2.loadUrl("http://www.youtube.com");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
            }

        );
    }

}
