package com.example.q.webtoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import static com.example.q.webtoon.hashable.days;

/**
 * Created by q on 2017-01-04.
 */

public class WebtoonFragment extends Fragment {

    RecyclerView mRecyclerView;
    WTAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    public static WebtoonFragment newInstance() {
        return new WebtoonFragment();
    }


    public void setUserVisibleHint(boolean is){

        super.setUserVisibleHint(is);

       /* if(is){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
            startActivity(browserIntent);
        }  */

    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // specify an adapter (see also next example)
        mAdapter=new WTAdapter(new ArrayList<Item>());

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.webtoonfragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv);


        // use a linear layout manager
        mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)

        mRecyclerView.setAdapter(mAdapter);

        rootView.findViewById(R.id.button1x).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        rootView.findViewById(R.id.button1x).setBackgroundResource(R.drawable.button_round2);
                        rootView.findViewById(R.id.button2x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button3x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button4x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button5x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button6x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button7x).setBackgroundResource(R.drawable.button_round);
                        mAdapter.setArrayList(days.get(0));
                        mAdapter.setArrayList(days.get(0));
                        mAdapter.notifyDataSetChanged();
                    }
                }
        );

        rootView.findViewById(R.id.button2x).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        rootView.findViewById(R.id.button1x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button2x).setBackgroundResource(R.drawable.button_round2);
                        rootView.findViewById(R.id.button3x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button4x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button5x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button6x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button7x).setBackgroundResource(R.drawable.button_round);
                        mAdapter.setArrayList(days.get(1));
                        mAdapter.notifyDataSetChanged();

                    }
                }
        );

        rootView.findViewById(R.id.button3x).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        rootView.findViewById(R.id.button1x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button2x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button3x).setBackgroundResource(R.drawable.button_round2);
                        rootView.findViewById(R.id.button4x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button5x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button6x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button7x).setBackgroundResource(R.drawable.button_round);
                        mAdapter.setArrayList(days.get(2));
                        mAdapter.notifyDataSetChanged();
                    }
                }
        );

        rootView.findViewById(R.id.button4x).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        rootView.findViewById(R.id.button1x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button2x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button3x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button4x).setBackgroundResource(R.drawable.button_round2);
                        rootView.findViewById(R.id.button5x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button6x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button7x).setBackgroundResource(R.drawable.button_round);
                        mAdapter.setArrayList(days.get(3));
                        mAdapter.notifyDataSetChanged();
                    }
                }
        );

        rootView.findViewById(R.id.button5x).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        rootView.findViewById(R.id.button1x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button2x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button3x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button4x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button5x).setBackgroundResource(R.drawable.button_round2);
                        rootView.findViewById(R.id.button6x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button7x).setBackgroundResource(R.drawable.button_round);
                        mAdapter.setArrayList(days.get(4));
                        mAdapter.notifyDataSetChanged();
                    }
                }
        );

        rootView.findViewById(R.id.button6x).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        rootView.findViewById(R.id.button1x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button2x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button3x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button4x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button5x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button6x).setBackgroundResource(R.drawable.button_round2);
                        rootView.findViewById(R.id.button7x).setBackgroundResource(R.drawable.button_round);
                        mAdapter.setArrayList(days.get(5));
                        mAdapter.notifyDataSetChanged();
                    }
                }
        );

        rootView.findViewById(R.id.button7x).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        rootView.findViewById(R.id.button1x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button2x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button3x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button4x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button5x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button6x).setBackgroundResource(R.drawable.button_round);
                        rootView.findViewById(R.id.button7x).setBackgroundResource(R.drawable.button_round2);
                        mAdapter.setArrayList(days.get(6));
                        mAdapter.notifyDataSetChanged();
                    }
                }
        );

        return rootView;
    }

}

