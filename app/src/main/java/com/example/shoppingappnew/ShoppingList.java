package com.example.shoppingappnew;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingList extends AppCompatActivity {

    //Declaring Variables
    ScrollView mScrollView;
    EditText item;
    TextView textView;
    DB_Controller controller;





    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View
        setContentView(R.layout.shoppinglist);
        item = (EditText) findViewById(R.id.item_input);
        textView = (TextView) findViewById(R.id.textView);
        mScrollView = (ScrollView) findViewById(R.id.SCROLLER_ID);
        Button b = (Button) findViewById(R.id.reset);
        controller = new DB_Controller(this, "", null, 1);

        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Start activity by using intent and android activity in manifest ref.
                textView.setText("");


            }
        });

    }



    //Used for each option on shopping list menu. Each action corresponds with a method
    //in the DB_Controller Class
    public void btn_click(View view) {
        switch(view.getId()) {
            case R.id.button_add:
                try {
                    controller.insert_items(item.getText().toString());
                }catch (SQLiteException e) {
                    Toast.makeText(ShoppingList.this, "Already Exists", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_delete:
                controller.delete_items(item.getText().toString());
                break;
            case R.id.list_students:
                controller.list_all_items(textView);
                break;

        }
    }






}
