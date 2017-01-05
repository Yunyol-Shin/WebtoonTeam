package com.example.q.webtoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by q on 2017-01-05.
 */

public class homeFragment extends Fragment {

    View view;
    private ViewPager viewPager;
    private myPagerAdapter myAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);

        myAdapter=new myPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager= (ViewPager) view.findViewById(R.id.container2);


        return view;

    }


    public static class ListViewFrag extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";


        public ListViewFrag() {
        }

        public static ListViewFrag newInstance(int sectionNumber) {
            ListViewFrag fragment = new ListViewFrag();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.listview_frag, container, false);

        }
    }

    public class myPagerAdapter extends FragmentPagerAdapter {
        public myPagerAdapter(FragmentManager fm){ super(fm);}

        @Override
        public Fragment getItem(int position){
            return ListViewFrag.newInstance(position);
        }

        @Override
        public int getCount(){
            return 3;
        }
    }






}