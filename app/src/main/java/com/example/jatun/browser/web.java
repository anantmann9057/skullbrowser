package com.example.jatun.browser;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;


import static com.example.jatun.browser.MainActivity.query;
import static com.example.jatun.browser.MainActivity.tx;
import static com.example.jatun.browser.MainActivity.url;


public class web extends AppCompatActivity {
    WebView wb3;
    ProgressBar pb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);
        wb3=(WebView)findViewById(R.id.webview3);
        pb3=(ProgressBar)findViewById(R.id.progressBar3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("");

        wb3.setWebViewClient(new WebViewClient(){
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                // Do something on page loading started
                // Visible the progressbar
                pb3.setVisibility(View.VISIBLE);
            }


            public void onPageFinished(WebView view, String url){
                // Do something when page loading finished
                web.this.setTitle(view.getTitle());
                Toast.makeText(getApplicationContext(),"Page Loaded.",Toast.LENGTH_SHORT).show();
            }
        });
        wb3.setWebChromeClient(new WebChromeClient()
        {
            public void onProgressChanged(WebView view, int newProgress){
                // Update the progress bar with page loading progress
                pb3.setProgress(newProgress);
                if(newProgress == 100){
                    // Hide the progressbar
                    pb3.setVisibility(View.GONE);
                }
            }
        });

        wb3.getSettings().setJavaScriptEnabled(true);
        wb3.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        wb3.setDownloadListener(new DownloadListener()
        {
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength)
            {
                //for downloading directly through download manager
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setMimeType(mimetype);
                //------------------------COOKIE!!------------------------
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                //------------------------COOKIE!!------------------------
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading file...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition,mimetype));
                request.allowScanningByMediaScanner();
                request.setVisibleInDownloadsUi(true);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "download");
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
            }
        });




        if(!url.startsWith("www.")&& !url.startsWith("http://")){
        url = "www."+url;
    }
        if(!url.startsWith("http://")){
        url = "http://"+url;
    }
    if (url.endsWith(".com")){


        if (validateUrl(url)) {
        wb3.getSettings().setJavaScriptEnabled(true);
        wb3.loadUrl(url);


    }
        }else{

        String url2="https://www.google.com/search?q="+tx;
        if(!url.endsWith(""))
        {
            url2=tx+url2;
        }
        if (validateUrl2(url2))
        {
            wb3.getSettings().setJavaScriptEnabled(true);
            wb3.loadUrl(url2);
        }


}}



    private boolean validateUrl(String url) {
        return true;
    }
    private boolean validateUrl2(String url2){
        return true;
    }


    @Override
    public void onBackPressed() {
        if (wb3.canGoBack())
        {
            wb3.goBack();
        }
        else
        {
            this.finish();
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
