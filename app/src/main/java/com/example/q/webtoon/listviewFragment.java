package com.example.q.webtoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by q on 2017-01-05.
 */

public class listviewFragment extends Fragment {

    ListView listview;
    View view;
    ArrayList<String> arr=new ArrayList<String>();
    static ArrayAdapter<String> adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.listview_frag, container, false);
        listview=(ListView)view.findViewById(R.id.listview);
        listview.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arr);

        adapter.add("신의 탑");
        adapter.add("노블레스");
        adapter.add("프리드로우");

    }
}