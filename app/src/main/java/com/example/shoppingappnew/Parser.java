package com.example.shoppingappnew;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Parser extends Activity {


    private TextView response;
    private TextView title, desc;
    private TextView errorText;
    private String result;
    //private String sourceListingURL = "http://open.live.bbc.co.uk/weather/feeds/en/2643123/3dayforecast.rss";
private String sourceListingURL = "https://queryfeed.net/twitter?q=%40ukdealshot&title-type=user-name-screen&geocode=";


    //ArrayList<WidgetClass> tItems = new ArrayList<WidgetClass>();
    //LinkedList<WidgetClass> tItems = null;






    public static ArrayList<WidgetClass> parseData(String dataToParse)
    {


        WidgetClass widget = null;
        ArrayList<WidgetClass> tItems = null;




        try {
            // Get the data from the XML stream as a string
            //result = sourceListingString(sourceListingURL);

            // Do some processing of the data to get the individual parts of the XML stream
            // At some point put this processing into a separate thread of execution

            // Display the string in the TextView object just to demonstrate this capability
            // This will need to be removed at some point
            //txt1.setText(result);



            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(dataToParse));
            int eventType = xpp.getEventType();
            boolean firstTitle = true;
            boolean firstDescription = true;
            int titlecount = 0;
            int descriptioncount = 0;
            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_TAG) {

                    if (xpp.getName().equalsIgnoreCase("channel")) {
                        tItems = new ArrayList<WidgetClass>();
                    } else if (xpp.getName().equalsIgnoreCase("item")) {
                        Log.e("MyTag", "item Start Tag found");
                        widget = new WidgetClass();
                    } else
                        // Check which Tag we have
                        if (xpp.getName().equalsIgnoreCase("title")) {
                            // Now just get the associated text
                            // Do something with text

                            if (titlecount < 2) {
                                titlecount++;
                            }
                            else {

                                String temp = xpp.nextText();
                                Log.e("MyTag", "Title is " + temp);

                                widget.setTitle(temp);
                                //Log.e("MyTag", "Widget title is " + widget.getTitle());
                            }

                        }

                        else
                        if (xpp.getName().equalsIgnoreCase("description"))
                        {

                            if (descriptioncount < 1) {
                                descriptioncount++;
                                // Update first description
                            }
                            else
                            {
                                // Now just get the associated text
                                String temp = xpp.nextText();
                                // Do something with text
                                Log.e("MyTag", "Description is " + temp);
                                widget.setDescription(temp);
                                // Get the text and put it in the appropriate place in the item object
                            }

                            // String temp = xpp.nextText();
                            //Log.e("MyTag", "Title is " + temp);
                            //widget.setTitle(temp);

                            // Now just get the associated text
                            // String temp = xpp.nextText();
                            // Do something with text
                            // Log.e("MyTag", "Description is " + temp);
                            //widget.setDescription(temp);
                        }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        Log.e("MyTag", "item is " + widget.toString());
                        tItems.add(widget);
                    }

                    else if (xpp.getName().equalsIgnoreCase("channel")) {
                        Log.e("MyTag", "channel is " + widget.toString());
                        //tItems.add(widget);

                    }
                    else if (xpp.getName().equalsIgnoreCase("rss")) {
                        int size;
                        size = tItems.size();
                        Log.e("MyTag", "rss size is " + size);
                    }

                }

                eventType = xpp.next();
            }



        } catch (XmlPullParserException ae1) {
            Log.e("MyTag", "Parsing error" + ae1.toString());
        } catch (IOException ae1) {
            Log.e("MyTag", "IO error during parsing");
        }

        Log.e("MyTag", "End document");

        return tItems;

    } // End of onCreate

    // Method to handle the reading of the data from the XML stream

    protected static String sourceListingString(String urlString) throws IOException {
        String result = "";
        InputStream anInStream = null;
        int response = -1;
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        // Check that the connection can be opened
        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try {
            // Open connection
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            // Check that connection is Ok
            if (response == HttpURLConnection.HTTP_OK) {
                // Connection is Ok so open a reader
                anInStream = httpConn.getInputStream();
                InputStreamReader in = new InputStreamReader(anInStream);
                BufferedReader bin = new BufferedReader(in);

                // Read in the data from the RSS stream
                String line = new String();
                // Read past the header
                bin.readLine();
                // Keep reading until there is no more data
                while (((line = bin.readLine())) != null) {
                    result = result + "\n" + line;
                }
            }
        } catch (Exception ex) {
            throw new IOException("Error connecting");
        }

        // Return result as a string for further processing
        return result;

    } // End of sourceListingString

} // End of Activity class



