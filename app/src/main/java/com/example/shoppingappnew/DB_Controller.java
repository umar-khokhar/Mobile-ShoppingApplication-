package com.example.shoppingappnew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;


public class DB_Controller extends SQLiteOpenHelper {
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "TEST.db", factory, version);
    }



    @Override
    //Creates the Database for the Shopping list Items
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ITEMS(ID INTEGER PRIMARY KEY AUTOINCREMENT, ITEM TEXT UNIQUE);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS ITEMS");
        onCreate(db);
        db.close();
    }


    // add items to the Shopping list database
    public void insert_items(String item){
        ContentValues contentValues = new ContentValues();
        contentValues.put("ITEM", item);
        this.getWritableDatabase().insertOrThrow("ITEMS", "" ,contentValues);

    }
        //Remove items from Shopping List Database
        public void delete_items(String item){
            this.getWritableDatabase().delete("ITEMS", "ITEM='"+item+"'", null);
        }

        //View all items in Shopping List Database
        public void list_all_items(TextView textView){

            Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM ITEMS", null);
            while (cursor.moveToNext()) {
                textView.append(cursor.getString(1)+"\n");
            }

            if(cursor.getCount() == 0){
                textView.append("No items found");

            }
                cursor.close();


        }










}
