/*
package com.example.ensiz_softwarem.logindemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<FishProduct> fishqtyList = null;
    private ArrayList<FishProduct> arraylist;

    public ListViewAdapter(Context context,
                           List<FishProduct> fishqtyList) {
        mContext = context;
        this.fishqtyList = fishqtyList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<FishProduct>();
        this.arraylist.addAll(fishqtyList);
    }

    public class ViewHolder {
        TextView name;
        TextView description;
        TextView price;
        ImageView fish;
    }

    @Override
    public int getCount() {
        return fishqtyList.size();
    }

    @Override
    public FishProduct getItem(int position) {
        return fishqtyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.description = (TextView) view.findViewById(R.id.description);
            holder.price = (TextView) view.findViewById(R.id.price);
            // Locate the ImageView in listview_item.xml
            holder.fish = (ImageView) view.findViewById(R.id.fish);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(fishqtyList.get(position).getName());
        holder.description.setText(fishqtyList.get(position).getDescription());
        holder.price.setText(fishqtyList.get(position).getPrice());
        // Set the results into ImageView
        holder.fish.setImageResource(fishqtyList.get(position).getFish());
        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("name",
                        (fishqtyList.get(position).getName()));
                // Pass all data country
                intent.putExtra("description",
                        (fishqtyList.get(position).getDescription()));
                // Pass all data population
                intent.putExtra("price",
                        (fishqtyList.get(position).getPrice()));
                // Pass all data flag
                intent.putExtra("fish",
                        (fishqtyList.get(position).getFish()));
                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });

        return view;

    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        fishqtyList.clear();
        if (charText.length() == 0) {
            fishqtyList.addAll(arraylist);
        } else {
            for (FishProduct wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    fishqtyList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}

*/
