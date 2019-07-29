/*
//Adapter for Recycler view Gallery
//Created by: Tharuka Sandaru
//Date : 3/22/2019


package com.example.ensiz_softwarem.logindemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    //assing variables
    private ArrayList<Cell> galleryList;
    private Context context;

    public MyAdapter(Context context, ArrayList<Cell> galleryList){
            this.galleryList = galleryList;
            this.context = context;
    }

    //Implementing view Holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.title.setText(galleryList.get(i).getTitle());
        viewHolder.img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        viewHolder.img.setImageResource(galleryList.get(i).getImg());
        viewHolder.img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Toast.makeText(context, "" + galleryList.get(i).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount(){
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView img;
        public ViewHolder(View view){
            super(view);


            title = (TextView) view.findViewById(R.id.title);
            img = (ImageView) view.findViewById(R.id.img);


        }
    }


}

*/
