package com.example.shoppingappnew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set view
        setContentView(R.layout.activity_main);


        // Action Bar
        android.support.v7.app.ActionBar ccActionBar = getSupportActionBar();
        if (ccActionBar != null)
        {
            ccActionBar.setDisplayShowHomeEnabled(true);
            ccActionBar.setLogo(R.drawable.shopping);
            ccActionBar.setDisplayUseLogoEnabled(true);
        }

        //assigning variables
        Button sl = (Button) findViewById(R.id.list);
        Button ds = (Button) findViewById(R.id.deals);
        Button b = (Button) findViewById(R.id.busy);



        //starting new activity
        sl.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Start activity by using intent and android activity in manifest ref.
                Intent i = new Intent(MainActivity.this, ShoppingList.class);
                startActivity(i);


            }
        });

        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Start activity by using intent and android activity in manifest ref.
                Intent i = new Intent(MainActivity.this, Barchart.class);
                startActivity(i);


            }
        });





        ds.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Start activity by using intent and android activity in manifest ref.
                Intent i = new Intent(MainActivity.this, ParseFeed.class);
                startActivity(i);


            }
        });



        Button maps = (Button) findViewById(R.id.Maps);
        maps.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Start activity by using intent and android activity in manifest ref.
                Intent i = new Intent(MainActivity.this, mcMapActivity.class);
                startActivity(i);


            }
        });






    }

}
