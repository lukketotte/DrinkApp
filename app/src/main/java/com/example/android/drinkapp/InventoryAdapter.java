package com.example.android.drinkapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the adapter which
 * Populates the inventory RecyclerView
 *
 * This adapter should take a database from the InventoryActivity
 */

//create the basic adapter extending from RecyclerView.Adapter
public class InventoryAdapter extends
        RecyclerView.Adapter<InventoryAdapter.ViewHolder> {


    private List<Bottle> listBottle;
    private Context mContext;

    InventoryAdapter(Context context){
        mContext = context;
    }

    private Context getContext(){
        return mContext;
    }


    /*Provide direct reference to each of the views within a data item
    * Used to cache the views within the item Layout for fast access*/
    public class ViewHolder extends RecyclerView.ViewHolder{

        /*Holder should contain a member variable for
        * any view that will be set as you render a row*/
        public TextView invTextView;
        public TextView invTextViewMaker;
        public TextView InvTextViewCheckContents;

        /*Constructor that accepts the entire item row
        * and does the view lookups to find each subview*/
        public ViewHolder(View itemView){
            super(itemView);

            //no need for casting any more
            //give references to TextViews from item_inventory.xml
            /*
            * invTextView: alcohol type
            * invTextViewMaker: producer
            * InvTextViewCheckContents: shows whether contents are low or not*/
            invTextView = itemView.findViewById(R.id.alcohol_type);
            invTextViewMaker = itemView.findViewById(R.id.alcohol_maker);
            InvTextViewCheckContents = itemView.findViewById(R.id.lowContentsFlag);

        }
    }

    /*inflate item layout and create holder*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //inflate view layout
        View inventoryView = inflater.inflate(R.layout.item_inventory, parent, false);

        //return holder instance
        ViewHolder viewHolder = new ViewHolder(inventoryView);
        return viewHolder;
    }

    /*Populate data into the item through holder*/
    @Override
    public void onBindViewHolder(InventoryAdapter.ViewHolder viewHolder, int position) {
        if(listBottle != null){
            //get the data based on position from the list of bottles
            Bottle bottle = listBottle.get(position);

            //set alcohol type
            TextView textView = viewHolder.invTextView;
            textView.setText(bottle.getObjectType());

            //set maker of the bottle
            TextView textViewMaker = viewHolder.invTextViewMaker;
            textViewMaker.setText(bottle.getObjectProducer());

            //set contentsFlag
            TextView textViewFlag = viewHolder.InvTextViewCheckContents;
            //change the text color of contentsFlag depending on true or false
            boolean flag = bottle.getObjectContentFlag();
            if(flag){
                //set color to HighContent
                textViewFlag.setText("HIGH");
                textViewFlag.setTextColor(Color.parseColor("#31a354"));
            } else{
                textViewFlag.setText("LOW");
                textViewFlag.setTextColor(Color.parseColor("#f03b20"));
            }
        } else{
            TextView textView = viewHolder.invTextView;
            textView.setText("No Objects!");
        }
    }


    //returns number of objects in the arrayList of BarObjects
    @Override
    public int getItemCount() {
        if(listBottle != null){
            return listBottle.size();
        } else {
            return 0;
        }
    }

    //this is the method through which we set the list of bottles
    public void setBottles(List<Bottle> listBottle){
        this.listBottle = listBottle;
        notifyDataSetChanged();
    }
}
