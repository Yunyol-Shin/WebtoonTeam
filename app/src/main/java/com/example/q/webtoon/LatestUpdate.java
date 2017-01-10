package com.example.q.webtoon;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by q on 2017-01-06.
 */

public class LatestUpdate extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);



        String url=getIntent().getStringExtra("url");

        WebView wb = (WebView) findViewById(R.id.webView2);


        wb .getSettings().setJavaScriptEnabled(true);
        wb .getSettings().setLoadWithOverviewMode(true);
        wb .getSettings().setUseWideViewPort(true);


        wb.loadUrl(url);
        wb .setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
            }
        });

    }



}