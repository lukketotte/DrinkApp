package com.example.android.drinkapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by lukas on 2018-01-12.
 */
@Database(entities = {Bottle.class}, version = 1)
public abstract class BottleRoomDatabase extends RoomDatabase{

    public abstract BottleDao bottleDao();
    private static BottleRoomDatabase cabinet;

    //To allow mainthread --> before .build() : .allowMainThreadQueries()
    static BottleRoomDatabase getDatabase(Context context){
        if(cabinet == null){
            synchronized (BottleRoomDatabase.class) {
                if(cabinet == null) {
                    cabinet = Room.databaseBuilder(context.getApplicationContext(),
                            BottleRoomDatabase.class, "bottle_database").build();
                }
            }
        }

        return cabinet;
    }
}
