package com.example.android.drinkapp;

/**
 * BarObject class
 */

public class BarObject {

    //COMPLETED (1) An object should be flagged for low contents
    // should have some type of validation of alcoholType
    private String alcoholType;
    private String maker;
    // description will be optional
    private String description;
    private boolean contentsLow;

    //this constructor will be used by the ChangeInventoryActivity
    public BarObject(){
        alcoholType = "";
        maker = "";
        description = "";
        contentsLow = false;
    }

    public BarObject(String alcoholType, String maker){
        this.alcoholType = alcoholType;
        this.maker = maker;
        contentsLow = true;
        this.description = "";
    }

    //set alcoholType
    public void setAlcoholType(String alcoholType){
        this.alcoholType = alcoholType;
    }

    //set maker
    public void setMaker(String maker){
        this.maker = maker;
    }

    //set description
    public void setDescription(String description){
        this.description = description;
    }

    //set contentsLow
    public void setContentsLow(boolean contentsLow) {
        this.contentsLow = contentsLow;
    }

    //get alcoholType
    public String getAlcoholType(){
        return alcoholType;
    }

    //get maker
    public String getMaker(){
        return maker;
    }

    //get description
    public String getDescription(){
        return description;
    }

    //get contentsLow
    public boolean getContentsLow(){
        return contentsLow;
    }

}
