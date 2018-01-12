package com.example.android.drinkapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InventoryActivity extends AppCompatActivity {

   // create instance of ViewModel
   private BottleViewModel mBottleViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        RecyclerView invView = findViewById(R.id.inventory_recView);

        mBottleViewModel = ViewModelProviders.of(this).get(BottleViewModel.class);

        //the adapter takes List<Bottle>, use the Dao instance bottleDao() to access it
        final InventoryAdapter adapter = new InventoryAdapter(this);
        invView.setAdapter(adapter);
        invView.setLayoutManager(new LinearLayoutManager(this));
        /*TODO(ViewModel): why does the LiveData need to be recasted, and why does this (line 39) cause the app to crash?*/
        adapter.setBottles((List<Bottle>) mBottleViewModel.getAllBottles());


        mBottleViewModel.getAllBottles().observe(this, new Observer<List<Bottle>>(){
            @Override
            public void onChanged(@Nullable final List<Bottle> bottles) {
                adapter.setBottles(bottles);
            }
        });
    }

    //if an item has been added in the ChangeInventory
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == ChangeInventoryActivity.RESULT_OK){

                //use the extras from ChangeInventoryActivity to create a new Bottle object
                Bottle bottle = new Bottle();
                bottle.setObjectType(data.getStringExtra(ChangeInventoryActivity.EXTRA_TYPE));
                bottle.setObjectProducer(data.getStringExtra(ChangeInventoryActivity.EXTRA_PROD));
                bottle.setObjectDescription(data.getStringExtra(ChangeInventoryActivity.EXTRA_DESC));
                //TODO: why does getBooleanExtra need a default value?
                bottle.setObjectContentFlag(data.getBooleanExtra(ChangeInventoryActivity.EXTRA_FLAG, false));

                //insert into the db
                mBottleViewModel.insert(bottle);

                // now we need to add the bottle to ViewModel?
            }
        } if(resultCode == ChangeInventoryActivity.RESULT_CANCELED) {
            //something went wrong
        }


    }
}
