/*
package com.example.ensiz_softwarem.logindemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter1 extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<FishProduct1> fishqtyList1 = null;
    private ArrayList<FishProduct1> arraylist;

    public ListViewAdapter1(Context context,
                           List<FishProduct1> fishqtyList) {
        mContext = context;
        this.fishqtyList1 = fishqtyList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<FishProduct1>();
        this.arraylist.addAll(fishqtyList);
    }

    public class ViewHolder {
        TextView name;
        TextView price;
        TextView qty;
    }

    @Override
    public int getCount() {
        return fishqtyList1.size();
    }

    @Override
    public FishProduct1 getItem(int position) {
        return fishqtyList1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ListViewAdapter1.ViewHolder holder;
        if (view == null) {
            holder = new ListViewAdapter1.ViewHolder();
            view = inflater.inflate(R.layout.listview_item1, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.price = (TextView) view.findViewById(R.id.price);
            holder.qty = (TextView) view.findViewById(R.id.qty);
            // Locate the ImageView in listview_item.xml
            view.setTag(holder);
        } else {
            holder = (ListViewAdapter1.ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(fishqtyList1.get(position).getName());
        holder.price.setText(fishqtyList1.get(position).getPrice());
        holder.qty.setText(fishqtyList1.get(position).getQty());
        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("name",
                        (fishqtyList1.get(position).getName()));
                // Pass all data country
                intent.putExtra("price",
                        (fishqtyList1.get(position).getPrice()));
                // Pass all data population
                intent.putExtra("qty",
                        (fishqtyList1.get(position).getQty()));
                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });

        return view;

    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        fishqtyList1.clear();
        if (charText.length() == 0) {
            fishqtyList1.addAll(arraylist);
        } else {
            for (FishProduct1 wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    fishqtyList1.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
*/
