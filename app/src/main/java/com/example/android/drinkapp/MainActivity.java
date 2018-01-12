package com.example.android.drinkapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //user checks inventory
    public void invClickFunction(View v){
        Intent intentINV = new Intent(getApplicationContext(), InventoryActivity.class);
        startActivity(intentINV);
    }

    public void changeInvClickFunction(View v){
        Intent intentChangeInv = new Intent(getApplicationContext(), ChangeInventoryActivity.class);
        //intentChangeInv.putExtra(barCabinet);
        startActivity(intentChangeInv);
    }
}
