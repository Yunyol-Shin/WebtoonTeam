package com.example.q.webtoon;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

import static com.example.q.webtoon.MainActivity.dbinfo;
import static com.example.q.webtoon.MainActivity.key;

public class WTAdapter extends RecyclerView.Adapter<WTAdapter.ViewHolder> {
    private ArrayList<Item> mItems;
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference=firebaseDatabase.getReference();

    public interface itemClick{
        public void onClick(View view,int position);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public WebView mWebView;
        public ImageButton mImageButton;

        public ViewHolder(View v) {
            super(v);

            mWebView=(WebView) v.findViewById(R.id.image);
            mTextView = (TextView) v.findViewById(R.id.imagetitle);
            mImageButton=(ImageButton) v.findViewById(R.id.imageButtonx);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public WTAdapter(ArrayList<Item> mDataset) {
        mItems=mDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent,
                                         int viewType) {
        // create a new view
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        // set the view's size, margins, paddings and layout parameters


        v.findViewById(R.id.imagetitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the RecyclerView current item serial and text
                // Display the RecyclerView clicked item serial and label
                TextView t=(TextView) view;
                String s=t.getText().toString();
                String url=hashable.siteMap[0]+hashable.NaverToonMap.get(s).url;

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                parent.getContext().startActivity(intent);
            }
        });

        v.findViewById(R.id.imageButtonx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the RecyclerView current item serial and text
                // Display the RecyclerView clicked item serial and label

                boolean isPressed=false;
                ImageButton i=(ImageButton) view;
                TextView t=(TextView) v.findViewById(R.id.imagetitle);
                String s=t.getText().toString();
                int index=0;

                for(int j=0;j<dbinfo.size();j++){
                    if(dbinfo.get(j).getTitle().equals(s)){
                        isPressed=true;
                        index=j;
                        break;
                    }
                }

                if(isPressed) {
                    i.setImageResource(R.drawable.ic_action_name2);

                    key.get(index).removeValue();
                   /* databaseReference.orderByChild("title").equalTo(s).addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    dataSnapshot.getRef().removeValue();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.w("TodoApp","getUser:onCancelled",databaseError.toException());
                                }
                            }
                    ); */
                }
                else {
                    i.setImageResource(R.drawable.ic_action_button2);
                    Item it;
                    if(hashable.NaverToonMap.containsKey(s))
                        it=new Item(s,0);
                    else if(hashable.LezhinToonMap.containsKey(s))
                        it=new Item(s,1);
                    else
                        it=new Item(s,2);





                    databaseReference.child("MyWebtoon").child(MainActivity.unique).push().setValue(it);
                }
            }
        });

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        //holder.imageView.setImageResource(mItems.get(position).image);

        Item i=mItems.get(position);
        holder.mTextView.setText(i.getTitle());
        Map<String, hashable.Info> map= hashable.getToonMap(i.getSite());
        String thumb=map.get(i.getTitle()).thumb;

        holder.mWebView.getSettings().setJavaScriptEnabled(true);
        holder.mWebView.getSettings().setLoadWithOverviewMode(true);
        holder.mWebView.getSettings().setUseWideViewPort(true);
        holder.mWebView.loadUrl(thumb);

        for(int j=0;j<dbinfo.size();j++){

            if(dbinfo.get(j).getTitle().equals(i.getTitle())){
                holder.mImageButton.setImageResource(R.drawable.ic_action_button2);
                return ;
            }

        }

        holder.mImageButton.setImageResource(R.drawable.ic_action_name2);

    }

    public void setArrayList(ArrayList<Item> items){
        this.mItems=items;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
