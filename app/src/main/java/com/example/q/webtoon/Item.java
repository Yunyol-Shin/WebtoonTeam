package com.example.q.webtoon;

/**
 * Created by q on 2017-01-05.
 */

public class Item{

    private String title;
    private int site; //naver:0 lezhin:1 daum:2

    public String getTitle(){return title;}
    public int getSite(){return site;}


    public Item()
    {
    }

    public Item(String title, int site)
    {
        this.title=title;
        this.site=site;

    }

}
