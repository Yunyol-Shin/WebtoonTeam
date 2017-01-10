package com.example.q.webtoon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import java.security.InvalidParameterException;
import java.util.Map;

/**
 * Created by q on 2017-01-06.
 */

public class WebViewer extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        WebView webView= (WebView)findViewById(R.id.webView2);
        Intent i = getIntent();
        String name=i.getStringExtra("name");
        int pos=i.getIntExtra("position",-1);

        if(pos==-1)
            throw new InvalidParameterException();


        Map<String, hashable.Info> map=hashable.getToonMap(pos);



        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        String url=map.get(name).url;
        String begin=hashable.siteMap[pos];
        webView.loadUrl(begin+ url);
    }
}
