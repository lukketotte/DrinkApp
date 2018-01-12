package com.example.android.drinkapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Repo for data, will probably come in handy when adding the db for recipies
 */

public class BottleRepo {
    private BottleDao mBottleDao;
    private LiveData<List<Bottle>> mAllBottles;

    BottleRepo(Application application) {
        BottleRoomDatabase db = BottleRoomDatabase.getDatabase(application);
        mBottleDao = db.bottleDao();
        //in the tutorial this function returns List<> rather than LiveData<List<>>
        mAllBottles = mBottleDao.getAllLive();
    }

    LiveData<List<Bottle>> getAllBottles(){
        return mAllBottles;
    }

    public void insert(Bottle bottle){
        new insertAsyncTask(mBottleDao).execute(bottle);
    }

    private static class insertAsyncTask extends AsyncTask<Bottle, Void, Void> {
        private BottleDao mAsyncBottleDao;

        insertAsyncTask(BottleDao dao){
            mAsyncBottleDao = dao;
        }

        @Override
        protected Void doInBackground(final Bottle... params) {
            mAsyncBottleDao.insertObject(params[0]);
            return null;
        }
    }

}
