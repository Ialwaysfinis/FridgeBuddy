package com.example.administrator.helloworld;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class Search extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SQLiteDatabase db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Ingredients (id INT, name VARCHAR)");
        db.execSQL("INSERT INTO Ingredients VALUES('1', 'Cheese')" +
                "INSERT INTO Ingredients VALUES('2', 'Ham')");

        Cursor c = db.rawQuery("SELECT * from Ingredients", null);
                c.moveToFirst();
        Log.d("results", c.getString(c.getColumnIndex("name")));


        db.close();

    }
}