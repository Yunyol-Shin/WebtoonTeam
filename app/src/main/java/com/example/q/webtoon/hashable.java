package com.example.q.webtoon;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by q on 2017-01-06.
 */

public class hashable {

    public static String[] siteMap={"http://m.comic.naver.com/webtoon/list.nhn?", "http://m.lezhin.com/ko/comic/", "http://m.webtoon.daum.net/m/webtoon/view/"} ;
    public static Map<String, Info> NaverToonMap= new HashMap<String, Info>();
    public static Map<String, Info> LezhinToonMap= new HashMap<String, Info>();
    public static Map<String, Info> DaumToonMap= new HashMap<String, Info>();
    public static ArrayList<ArrayList<Item>> days=new ArrayList<>();

    public static class Info{
        String url;
        String thumb;
        String writer;
        public Info(String url, String thumb){
            this.url=url; this.thumb=thumb;
        }
        public Info(String url, String thumb, String writer){
            this.url=url; this.thumb=thumb; this.writer=writer;
        }
    }

    public static Info getInfo(String name){
        if(hashable.NaverToonMap.containsKey(name))
            return hashable.NaverToonMap.get(name);
        else if(hashable.LezhinToonMap.containsKey(name))
            return hashable.LezhinToonMap.get(name);
        else
            return hashable.DaumToonMap.get(name);

    }
    public static int getSite(String name){
        if(hashable.NaverToonMap.containsKey(name))
            return 0;
        else if(hashable.LezhinToonMap.containsKey(name))
            return 1;
        else
            return 2;

    }

    public static Map<String, Info> getToonMap(int position){
        if(position==0)
            return NaverToonMap;
        if(position==1)
            return LezhinToonMap;
        if(position==2)
            return DaumToonMap;
        return null;
    }

    public static void instantiate(Context context){

        for(int i=0;i<7;i++){
            days.add(new ArrayList<Item>());
        }

        try {
            AssetManager assetManager=context.getAssets();
            String[] str={null, "mon", "tue", "wed", "thu", "fri", "sat", "sun"};
            int day=0;
            InputStream inputStream=assetManager.open("naver.txt");
            InputStreamReader isr=new InputStreamReader(inputStream);
            BufferedReader br=new BufferedReader(isr);
            String line;
            String next;


            while ((line=br.readLine()) != null) {
                if(line.length()>0&&line.charAt(0)=='-') {
                    day++;
                }
                if(line.indexOf("http")>1) {
                    next = br.readLine();
                    int index=next.indexOf("\t");
                    String url="titleId="+next.substring(0,index)+"&week="+str[day];
                    String name=line.substring(0, line.indexOf("\t"));

                    days.get(day-1).add(new Item(name,0));
                    String thumb=line.substring(line.indexOf("http"));
                    NaverToonMap.put(name, new Info(url,thumb, next.substring(index+2)));
                }
            }
            br.close();
        }catch(Exception e){e.printStackTrace();}

        try {
            AssetManager assetManager=context.getAssets();
            InputStream inputStream=assetManager.open("daum.txt");
            InputStreamReader isr=new InputStreamReader(inputStream);
            BufferedReader br=new BufferedReader(isr);
            String line;
            String next;
            int day=0;

            while ((line=br.readLine()) != null) {

                if(line.length()>0&&line.charAt(0)=='-') {

                    day++;
                }

                if(line.indexOf("http")>1) {

                    next = br.readLine();
                    int index=next.indexOf("\t");
                    String url=next.substring(0,index);
                    String name=line.substring(0, line.indexOf("\t"));
                    String thumb=line.substring(line.indexOf("http"));
                    DaumToonMap.put(name, new Info(url,thumb, next.substring(index+2)));
                    days.get(day-1).add(new Item(name,2));
                }
            }
            br.close();
        }catch(Exception e){e.printStackTrace();}
        try {
            AssetManager assetManager=context.getAssets();
            InputStream inputStream=assetManager.open("lezhin.txt");
            InputStreamReader isr=new InputStreamReader(inputStream);
            BufferedReader br=new BufferedReader(isr);
            String line;
            String next;
            int day=0;

            while ((line=br.readLine()) != null) {

                if(line.length()>0&&line.charAt(0)=='-') {

                    day++;
                }

                if(line.indexOf("http")>1) {
                    next = br.readLine();
                    int index=next.indexOf("\t");
                    String url=next.substring(0,index);
                    String name=line.substring(0, line.indexOf("\t"));
                    String thumb=line.substring(line.indexOf("http"));
                    LezhinToonMap.put(name, new Info(url,thumb, next.substring(index+1)));
                    days.get(day-1).add(new Item(name,1));
                }
            }
            br.close();
        }catch(Exception e){e.printStackTrace();}


    }

}