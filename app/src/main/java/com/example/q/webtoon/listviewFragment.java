package com.example.q.webtoon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.q.webtoon.MainActivity.dbinfo;
import static com.example.q.webtoon.MainActivity.key;

/**
 * Created by q on 2017-01-05.
 */

public class listviewFragment extends Fragment {

    ListView listview;
    View view;
    ArrayList<webStruct> arr;
    ListArrayAdapter adapter;
    int position;

    public class ListArrayAdapter extends ArrayAdapter{

        public ListArrayAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
        }

        @Override
        public webStruct getItem(int position){
            return arr.get(position);
        }

        @Override
        public int getCount(){
            return arr.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View viewLayout=inflater.inflate(R.layout.list_item_revise, parent, false);
            final webStruct webstruct=(webStruct)getItem(position);
            final int pos=position;
            arr.get(pos).view=viewLayout;
            ImageButton imageButton1=(ImageButton)viewLayout.findViewById(R.id.imageButton2);
            imageButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getContext(), WebViewer.class);
                    i.putExtra("name", webstruct.name);
                    i.putExtra("position", webstruct.position);
                    startActivity(i);
                }
            });
            ImageButton imageButton=(ImageButton)viewLayout.findViewById(R.id.imageButton);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(getContext());
                    alertDialog.setMessage("정말로 목록에서 지우시겠습니까?");
                    alertDialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {




                            int j;
                            for(j=0;j<dbinfo.size();j++){
                                if(dbinfo.get(j).getTitle().equals(arr.get(pos).getName())){
                                    break;
                                }
                            }

                            key.get(j).removeValue();


















                            dialog.dismiss();
                        }
                    });
                    alertDialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.create().show();
                }
            });

            hashable.Info info=hashable.getToonMap(arr.get(pos).position).get(webstruct.name);

            if(webstruct.name!=null) {//set name
                TextView tv1 = (TextView) viewLayout.findViewById(R.id.textView2);
                tv1.setText(webstruct.name);
            }

            if(info!=null) {   //load thumbnail image
                WebView wb = (WebView) viewLayout.findViewById(R.id.webView);
                wb.getSettings().setJavaScriptEnabled(true);
                wb.getSettings().setLoadWithOverviewMode(true);
                wb.getSettings().setUseWideViewPort(true);
                wb.loadUrl(info.thumb);
                TextView tv2 = (TextView) viewLayout.findViewById(R.id.textView3);
                tv2.setText(info.writer);
                arr.get(pos).tx=(TextView)viewLayout.findViewById(R.id.textView4);
                if(arr.get(pos).position==0) {
                    new LongOperation().execute(hashable.siteMap[0]+hashable.NaverToonMap.get(webstruct.name).url, new Integer(pos).toString());
                }
                //else if(arr.get(pos).position==1){
        //            new LongOperation().execute(hashable.siteMap[1]+hashable.LezhinToonMap.get(webstruct.name).url, new Integer(pos).toString());
           //     }
                else if(arr.get(pos).position==2){
                    final WebView webview = new WebView(getContext());
                    webview.getSettings().setJavaScriptEnabled(true);
                    webview.addJavascriptInterface(new MyJavaScriptInterface(position), "HtmlViewer");
                    webview.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            webview.loadUrl("javascript:HtmlViewer.showHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                        }
                    });

                    webview.loadUrl("http://webtoon.daum.net/m/webtoon/view/"+hashable.DaumToonMap.get(webstruct.name).url);
                }
            }




            return viewLayout;
        }
        class MyJavaScriptInterface extends WebViewClient {
           int position;
            MyJavaScriptInterface(int position) {
                this.position=position;
            }
            @JavascriptInterface
            public void showHTML(String html) {
                int index=html.indexOf("list_comm person");
                String sub=html.substring(index+45, index+100);
                int index2=sub.indexOf("\"");
                String daumurl="http://webtoon.daum.net"+(sub.substring(0, index2));
                arr.get(position).view.setOnClickListener(new MyClickListener(daumurl));

            }
        }
        public class MyClickListener implements View.OnClickListener{
            String url;
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), LatestUpdate.class);
                i.putExtra("url", url);
                startActivity(i);
            }
            public MyClickListener(String url){
                this.url=url;
            }
        }

        public class LongOperation extends AsyncTask<String, Void, String> {
            int position;

            @Override
            protected String doInBackground(String... params) {
                String whole = "";
                String inputLine = "";
                position=Integer.parseInt(params[1]);
                try {
                    URL oracle = new URL(params[0]);
                    BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.contains("no=")) {
                            whole += inputLine + "\n";
                            for(int i=0;i<9;i++)
                                in.readLine();
                            whole+= in.readLine() + "\n";
                            break;
                        }
                    }
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return whole;
            }

            @Override
            protected void onPostExecute(String result) {
                if(result.length()==0)
                    return;
                int index = result.indexOf("/");
                int newline= result.indexOf("\n");
                int i;
                for(i=newline+1;i<result.length();i++){
                    if(result.charAt(i)!='\t')
                        break;
                }



                //////////////////////////////////////////////////

                arr.get(position).view.setOnClickListener(new MyClickListener("http://m.comic.naver.com" + result.substring(index, newline - 2)));
                arr.get(position).tx.setText(result.substring(i, result.length()-1));

            }


            class MyJavaScriptInterface extends WebViewClient {
                private Context ctx;
                MyJavaScriptInterface(Context ctx) {
                    this.ctx = ctx;
                }
                @JavascriptInterface
                public void showHTML(String html) {
                    int index=html.indexOf("list_comm person");
                    String sub=html.substring(index+45, index+100);
                    int index2=sub.indexOf("\"");
                    System.out.println(sub.substring(0, index2));
                }
            }
        }





    }

    public FrameLayout fm;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.listview_frag, container, false);
        listview=(ListView)view.findViewById(R.id.listview);
        listview.setAdapter(adapter);

        fm=(FrameLayout)view.findViewById(R.id.frameLayout);
        final FloatingActionButton fab= (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater= (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View searchView=inflater.inflate(R.layout.search_view, null);
                final EditText ed= (EditText)searchView.findViewById(R.id.editText);
                ImageButton ib=(ImageButton)searchView.findViewById(R.id.imageButton4);
                ib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String query=ed.getText().toString();
                        if(query.length()!=0) {
                            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
                            builderSingle.setIcon(R.drawable.logo2);
                            builderSingle.setTitle("찾으시는 웹툰을 고르세요");

                            ArrayList<String> arraylist=new ArrayList<String>();

                            Set<Map.Entry<String, hashable.Info>> set=hashable.getToonMap(position).entrySet();
                            for(Map.Entry<String, hashable.Info> entry: set)
                            {
                                if(entry.getKey().contains(query))
                                    if(!arraylist.contains(entry.getKey())) {
                                        String key=entry.getKey();
                                        arraylist.add(key);
                                    }
                            }
                            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arraylist);


                            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                    //view
                                    final String strName = arrayAdapter.getItem(which);


                                    AlertDialog.Builder builderInner = new AlertDialog.Builder(getContext());

                                    LayoutInflater inflater=getActivity().getLayoutInflater();
                                    builderInner.setTitle(strName);


                                    builderInner.setMessage("이 웹툰을 목록에 추가하시겠습니까?");







                           /*

                                    builderInner.setMessage(strName);
                                    builderInner.setTitle("Your Selected Item is");

                  */

                                    builderInner.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });

                                    builderInner.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            boolean check=true;
                                            for(int j=0;j<dbinfo.size();j++){
                                                if(dbinfo.get(j).getTitle().equals(strName)) {
                                                    check = false;
                                                    break;
                                                }
                                            }
                                            if(check)
                                            {
                                                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
                                                databaseReference.child("MyWebtoon").child(MainActivity.unique).push().setValue(new Item(strName, hashable.getSite(strName)));
                                                Toast.makeText(getContext(), "Upload successful", Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                Toast.makeText(getContext(), "Existing Entry", Toast.LENGTH_SHORT).show();
                                            }


                                            dialog.dismiss();
                                        }
                                    });
                                    builderInner.show();




                                }
                            });
                            builderSingle.show();


                        }



                        fm.removeView(searchView);
                        fab.setVisibility(View.VISIBLE);
                    }
                });
                fm.addView(searchView, 2);
                fab.setVisibility(View.GONE);

            }
        });
        return view;
    }



    public class webStruct{
        String name;
        int position;
        TextView tx;
        View view;
        public webStruct(String name, int position){
            this.name=name;
            this.position=position;
        }
        public webStruct(String name){
            this.name=name;
        }
        public String getName(){
            return name;
        }
    }

    public static listviewFragment instantiate(int position){
        listviewFragment fragment=new listviewFragment();
        fragment.position=position;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        MainActivity.webtoon[position]=this;
        super.onCreate(savedInstanceState);
        arr=new ArrayList<webStruct>();
        adapter=new ListArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arr);


        ArrayList<Item> dbinfo=MainActivity.dbinfo;


        for(Item item:dbinfo){
            if(item.getSite()==position){
                adapter.add(new webStruct(item.getTitle(), item.getSite()));
            }
        }

    }


    public void addList(Item item){
        arr.add(new webStruct(item.getTitle(), item.getSite()));
        adapter.notifyDataSetChanged();
    }

    public void removeList(Item item){
        for(int i=0;i<adapter.getCount();i++){
            webStruct webs=adapter.getItem(i);
            if(webs.getName().equals(item.getTitle())){
                adapter.remove(webs);
            }
            adapter.notifyDataSetChanged();
        }
    }



}