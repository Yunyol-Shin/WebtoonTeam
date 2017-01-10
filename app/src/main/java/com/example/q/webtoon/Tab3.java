package com.example.q.webtoon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by q on 2017-01-07.
 */

public class Tab3 extends Fragment {

    ArrayList<hashable.Info> toonsite= new ArrayList<>();
    public static ArrayList<String> sitename=new ArrayList<>();
    public static Tab3 newInstance() {
        return new Tab3();
    }
    ListView l;
    ArrayAdapter adapter;

    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        toonsite.add(new hashable.Info("http://toptoon.com/","http://www.toptoon.com/assets/img/head_logo/logo_image.png"));
        toonsite.add(new hashable.Info("http://www.zzamtoon.com/","http://www.zzamtoon.com/assets/img/web_v2/global/header-logo.png"));
        toonsite.add(new hashable.Info("http://www.mrblue.com/section_webtoon/","http://www.mrblue.com/img/logo.gif"));
        toonsite.add(new hashable.Info("http://bomtoon.com/","http://bomtoon.com/assets/img/www/common/logo_bomtoon2.gif"));
        toonsite.add(new hashable.Info("http://m.comics.nate.com/main/","http://crayondata.nate.com/upload/segment/201611/1338705638333642686001.png"));
        toonsite.add(new hashable.Info("http://www.foxtoon.com/","http://cdn.foxtoon.com/assets/image/logo_gray2.png"));
        toonsite.add(new hashable.Info("http://ttale.com/","http://webtooninsight.co.kr/Content/assets/images/logo.png\n"));
        toonsite.add(new hashable.Info("http://page.kakao.com/","http://static-page.kakao.com//resources/kakaopage/mobile/images/logo.png"));
        toonsite.add(new hashable.Info("http://www.big-toon.com/","http://www.big-toon.com/images/common/logo.png"));

        sitename.add("탑툰");
        sitename.add("짬툰");
        sitename.add("미스터 블루");
        sitename.add("봄툰");
        sitename.add("네이트");
        sitename.add("폭스툰");
        sitename.add("티테일");
        sitename.add("카카오페이지");
        sitename.add("빅툰");
        adapter=new Tab3Adapter(getContext(),R.layout.lv_item,toonsite);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab3, container, false);
        l = (ListView) rootView.findViewById(R.id.lv);


        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String url= toonsite.get(position).url;

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                parent.getContext().startActivity(intent);

            }
        });

        l.setAdapter(adapter);
        return rootView;

    }

}
