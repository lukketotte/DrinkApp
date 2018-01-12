package com.example.android.drinkapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * ViewModels are entities that are free of activity life cycles. So they
 * can retain their state during orientationchanges. It does not contain
 * any data related to the UI. Preferred to store the DB instance in a
 * ViewModel rather than on any activity
 */

public class BottleViewModel extends AndroidViewModel {

    private BottleRepo mBottleRepo;
    private LiveData<List<Bottle>> mAllBottles;

    public BottleViewModel(Application application){
        super(application);
        mBottleRepo = new BottleRepo(application);
        mAllBottles = mBottleRepo.getAllBottles();
    }

    LiveData<List<Bottle>> getAllBottles(){
        return mAllBottles;
    }

    public void insert(Bottle bottle){
        mBottleRepo.insert(bottle);
    }
}
