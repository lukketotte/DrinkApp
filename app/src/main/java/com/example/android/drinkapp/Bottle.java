package com.example.android.drinkapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * This is the database entity, which will hold our BarObjects. Not even sure
 * that we need to BarObject class anymore. This will be stored locally on the
 * phone. Perhaps cached?
 *
 * Database will have user id, and some characteristics of the objects to be
 * displayed in the app
 */

@Entity(tableName = "barcabinet")
public class Bottle {

    //object id will be automatically generated
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "objectId")
    public int id;


    @ColumnInfo(name = "object_type")
    private String objectType;

    @ColumnInfo(name = "object_producer")
    private String objectProducer;

    @ColumnInfo(name = "object_contents_flag")
    private boolean objectContentFlag;

    @ColumnInfo(name = "object_description")
    private String objectDescription;

    //setters
    public void setObjectType(String objectType){
        this.objectType = objectType;
    }

    public void setObjectProducer(String objectProducer){
        this.objectProducer = objectProducer;
    }

    public void  setObjectContentFlag(boolean objectContentFlag){
        this.objectContentFlag = objectContentFlag;
    }

    public void setObjectDescription(String objectDescription){
        this.objectDescription = objectDescription;
    }

    //getters
    public String getObjectType(){
        return objectType;
    }

    public String getObjectProducer(){
        return objectProducer;
    }

    public String getObjectDescription(){
        return objectDescription;
    }

    public boolean getObjectContentFlag(){
        return objectContentFlag;
    }


}
