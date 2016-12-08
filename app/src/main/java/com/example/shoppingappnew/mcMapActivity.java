package com.example.shoppingappnew;


import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mcMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    FragmentManager fmAboutDialogue; // Lab 3 Dialogue fragment
    List<mcMapData> mapDataLst;
    private Marker[] mapDataMarkerList = new Marker[6];
    private GoogleMap mapStarSigns;        //Google Map variable
    private float markerColours[] = {210.0f, 120.0f, 300.0f, 330.0f, 270.0f, 320.0f,};

    private static final LatLng latlangEKCentre = new LatLng(55.864417, -4.255026);    //The Latitude and Longitude for the centre of Glasgow City Centre

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mc_map_view); // app main UI screen

        // Action Bar
        android.support.v7.app.ActionBar ccActionBar = getSupportActionBar();
        if (ccActionBar != null) {
            ccActionBar.setDisplayShowHomeEnabled(true);
            //ccActionBar.setLogo(R.drawable.shopping);
            ccActionBar.setDisplayUseLogoEnabled(true);
        }

        // Lab 3 Dialogue fragment
        fmAboutDialogue = this.getFragmentManager();


        mapDataLst = new ArrayList<mcMapData>();
        mcMapDataDBMgr mapDB = new mcMapDataDBMgr(this, "Store.s3db", null, 1);
        try {
            mapDB.dbCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mapDataLst = mapDB.allMapData();
        SetUpMap();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapStarSigns = googleMap;
        if (mapStarSigns != null) {
            mapStarSigns.moveCamera(CameraUpdateFactory.newLatLngZoom(latlangEKCentre, 12));        //Set the default position to EK
            mapStarSigns.getUiSettings().setCompassEnabled(true);                                   //Turn on the Compass
            mapStarSigns.getUiSettings().setMyLocationButtonEnabled(true);                          //Turn on the Location Buttons Functionality
            mapStarSigns.getUiSettings().setRotateGesturesEnabled(true);
            AddMarkers();
        }
    }

    public void SetUpMap() {
        MapFragment mapStarSignsEK = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapStarSignsEK.getMapAsync(this);    //Create the map and apply to the variable
    }

    public void AddMarkers() {
        MarkerOptions marker;
        mcMapData mapData;
        String mrkTitle;
        //String mrkText;

    	/* For all the marker options in dbList list */
        for (int i = 0; i < mapDataLst.size(); i++) {
            mapData = mapDataLst.get(i);
            mrkTitle = mapData.getStorename();
            marker = SetMarker(mrkTitle, null, new LatLng(mapData.getLatitude(), mapData.getLongitude()), markerColours[i], true);
            mapDataMarkerList[i] = mapStarSigns.addMarker(marker);    //create a maker and add to the venue markers list
        }
    }

    public MarkerOptions SetMarker(String title, String snippet, LatLng position, float markerColour, boolean centreAnchor) {
        float anchorX;    //Create anchorX
        float anchorY;    //Create anchorY

    	/* On the condition the anchor is to be centred */
        if (centreAnchor) {
            anchorX = 0.5f;    //Centre X
            anchorY = 0.5f;    //Centre Y
        } else {
            anchorX = 0.5f;    //Centre X
            anchorY = 1f;    //Bottom Y
        }

    	/* create marker options from the input variables */
        MarkerOptions marker = new MarkerOptions().title(title).snippet(snippet).icon(BitmapDescriptorFactory.defaultMarker(markerColour)).anchor(anchorX, anchorY).position(position);

        return marker;    //Return marker
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                // About Dialogue;
                DialogFragment mcAboutDlg = new mcAboutDialogue();
                mcAboutDlg.show(fmAboutDialogue, "menu");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

}

