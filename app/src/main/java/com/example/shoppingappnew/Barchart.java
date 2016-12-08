package com.example.shoppingappnew;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;



public class Barchart extends AppCompatActivity {

    //Declaring Variables
    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set View
        setContentView(R.layout.barchart);
        chart = (BarChart) findViewById(R.id.chart1);

        // Creating an Array for the Barchart Figures on the Y-axis
        BARENTRY = new ArrayList<>();

        //Creating a String based Arraylist for the associated Labels on the X-axis
        BarEntryLabels = new ArrayList<String>();

        //Adding Values to Arraylist
        AddValuesToBARENTRY();

        AddValuesToBarEntryLabels();

        //Explanation at the bottom left
        Bardataset = new BarDataSet(BARENTRY, "No. of customers(00's)");

        BARDATA = new BarData(BarEntryLabels, Bardataset);

        //Colours for each Barchart item
        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        chart.setData(BARDATA);

        // Time to animate the Barchart upon start
        chart.animateY(3500);

    }

    //Adding values to Arraylist responsible for the Y-axis
    public void AddValuesToBARENTRY(){

        BARENTRY.add(new BarEntry(3f, 0));
        BARENTRY.add(new BarEntry(4f, 1));
        BARENTRY.add(new BarEntry(7f, 2));
        BARENTRY.add(new BarEntry(9f, 3));
        BARENTRY.add(new BarEntry(7f, 4));
        BARENTRY.add(new BarEntry(3f, 5));

    }

    //Adding values to the Arraylist responsible for X-axis
    public void AddValuesToBarEntryLabels(){

        BarEntryLabels.add("Morning");
        BarEntryLabels.add("Mid-Morning");
        BarEntryLabels.add("Noon");
        BarEntryLabels.add("Afternoon");
        BarEntryLabels.add("Mid-Afternoon");
        BarEntryLabels.add("Evening");

    }
}




