package com.example.q.webtoon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.q.webtoon.Tab3.sitename;


/**
 * Created by q on 2017-01-10.
 */

public class Tab3Adapter extends ArrayAdapter<hashable.Info> {

    private ArrayList<hashable.Info> items;
    Context context;

   public Tab3Adapter(Context context, int ResourceId, ArrayList<hashable.Info> items) {
        super(context,ResourceId,items);
        this.items = items;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.lv_item, null);
        }

        hashable.Info in = items.get(position);

        if (in != null) {
            TextView tv = (TextView) v.findViewById(R.id.lv_sitetitle);
            WebView  wv= (WebView) v.findViewById(R.id.lv_image);

            if (tv != null){
                tv.setText(sitename.get(position));
            }
            if(wv != null){
                wv.getSettings().setJavaScriptEnabled(true);
                wv.getSettings().setLoadWithOverviewMode(true);
                wv.getSettings().setUseWideViewPort(true);
                wv.loadUrl(in.thumb);
            }
        }
        return v;
    }

}
