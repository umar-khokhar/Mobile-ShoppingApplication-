package com.example.shoppingappnew;

import java.io.Serializable;


public class mcMapData implements Serializable {

// *********************************************
// Declare variables etc.
// *********************************************

    private int entryID;
    private String Storename;
    private float Latitude;
    private float Longitude;

    private static final long serialVersionUID = 0L;

// *********************************************
// Declare getters and setters etc.
// *********************************************


    public int getEntryID() {
        return entryID;
    }

    public void setEntryID(int entryID) {
        this.entryID = entryID;
    }

    public String getStorename() {
        return Storename;
    }

    public void setStorename(String storename) {
        this.Storename = storename;
    }

    public float getLatitude()
    {
        return Latitude;
    }

    public void setLatitude(float Lat)
    {
        this.Latitude = Lat;
    }

    public float getLongitude()
    {
        return Longitude;
    }

    public void setLongitude(float fLongitude)
    {
        this.Longitude = fLongitude;
    }

    @Override
    public String toString() {
        String mapData;
        mapData = "mcStarSignsInfo [entryID=" + entryID;
        mapData = ", Storename=" + Storename;
        mapData = ", Latitude=" + Latitude;
        mapData = ", Longitude=" + Longitude +"]";
        return mapData;
    }

}
