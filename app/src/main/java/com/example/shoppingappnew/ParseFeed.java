// Mohammad Umar Khokhar - S1314549

package com.example.shoppingappnew;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;


public class ParseFeed extends Activity {

    private TextView response;
    private TextView title, desc;
    private TextView errorText;
    private String result;
    private String sourceListingURL = "http://open.live.bbc.co.uk/weather/feeds/en/2648579/3dayforecast.rss";

    ArrayList<WidgetClass> tItems = new ArrayList<WidgetClass>();


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_display);




        //Get the TextView object on which to display the results
        response = (TextView)findViewById(R.id.urlResponse);
        try
        {
            // Get the data from the XML stream as a string
            result = Parser.sourceListingString(sourceListingURL);

            // Do some processing of the data to get the individual parts of the XML stream
            // At some point put this processing into a separate thread of execution
            tItems = Parser.parseData(result);


            StringBuilder items = new StringBuilder();

            for (WidgetClass details : tItems) {
                items.append(details + " ");

            }
            // Display the string in the TextView object just to demonstrate this capability
            // This will need to be removed at some point
            //response.setText(result);
            response.setText(items.toString());


        }
        catch(IOException ae)
        {
            // Handle error
            response.setText("Error");
            // Add error info to log for diagnostics
            errorText.setText(ae.toString());
        }








    } // End of onCreate





} // End of






