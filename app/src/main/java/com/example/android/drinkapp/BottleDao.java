package com.example.android.drinkapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Data access object for the Bottle entity. Any cocktail object will be
 * referred to as a bottle :)
 *
 * Also it should be defined as interface, not class. Not exactly shure
 * what an interface is, ask joseph
 *
 */

@Dao
public interface BottleDao {

    //add single bottle to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertObject(Bottle bottle);

    //remove bottle from the database
    @Delete
    void delete(Bottle bottle);

    //update bottle
    @Update
    void updateBottle(Bottle bottle);

    //gets all bottles in the database
    @Query("SELECT * FROM barcabinet")
    List<Bottle> getAll();

    //returns all bottles of specified type
    @Query("SELECT * FROM barcabinet WHERE object_type LIKE :type")
    List<Bottle> getAllType(String type);

    //returns all bottles of specified producer
    @Query("SELECT * FROM barcabinet WHERE object_producer LIKE :type")
    List<Bottle> getAllProducer(String type);

    //returns all bottles of specified content flag
    @Query("SELECT * FROM barcabinet WHERE object_producer LIKE :flag")
    List<Bottle> getAllFlag(boolean flag);

    //order bottles
    @Query("SELECT * FROM barcabinet ORDER BY object_type ASC")
    List<Bottle> getAllBottlesSorted();


    /*--------------------------------------------------------------*/
    /*Perhaps more intuitive methods of accessing elements in the db*/
    @Query("SELECT * FROM barcabinet")
    LiveData<List<Bottle>> getAllLive();

    //get bottle by id
    @Query("SELECT * FROM barcabinet WHERE objectId = :id")
    Bottle getBottleById(int id);

    @Query("DELETE FROM barcabinet")
    public void nukeTable();
    /*--------------------------------------------------------------*/

}
