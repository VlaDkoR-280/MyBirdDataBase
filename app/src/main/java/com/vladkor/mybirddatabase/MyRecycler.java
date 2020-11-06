package com.vladkor.mybirddatabase;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.vladkor.mybirddatabase.Item.Item;

import java.net.URI;
import java.util.ArrayList;

public class MyRecycler extends RecyclerView.Adapter<MyRecycler.ViewHolder> {

    private ArrayList<Item> item;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View myView;
        public TextView title;
        public TextView description;
        public ImageView imageView;
        public TextView l_description;
        public ViewHolder(View v){
            super(v);
            myView = v;
            title = v.findViewById(R.id.titleTxt);
            description = v.findViewById(R.id.descriptTxt);
            imageView = v.findViewById(R.id.img);
        }

    }

    public MyRecycler(ArrayList<Item> item){
        this.item = item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Item myItem = item.get(position);
        holder.title.setText(myItem.getTitle());
        holder.description.setText(myItem.getDescription());
        try {
            Bitmap bm;
            if(myItem.getImg() != null){
                byte[] buffer = myItem.getImg().getBytes();
                bm = BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
                holder.imageView.setImageBitmap(Bitmap.createScaledBitmap(bm, holder.imageView.getWidth(), holder.imageView.getHeight(), false));
            }


        }catch (Exception e){

        }
        holder.myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(v, myItem.getL_description(), Snackbar.LENGTH_LONG).show();

                Intent i = new Intent(v.getContext(), my_item_layout.class);
                i.putExtra("TITLE", myItem.getTitle());
                i.putExtra("DESCRIPTION", myItem.getDescription());
                i.putExtra("LARGE_DESCRIPTION", myItem.getL_description());
                i.putExtra("IMAGE", myItem.getImg());
                v.getContext().startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return item.size();
    }
}
