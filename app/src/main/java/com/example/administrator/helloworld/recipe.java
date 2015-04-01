package com.example.administrator.helloworld;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class recipe extends ActionBarActivity {

    protected TextView Name;
    protected TextView Recipe;
    protected int recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recipeId = getIntent().getIntExtra("RECIPE_ID", 0);
        SQLiteDatabase db = (new DatabaseHelper(this)).getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT rec._id, rec.Name, rec.Recipe, FROM recipe WHERE rec._id = ?",
                new String[]{""+recipeId});

        if (cursor.getCount() == 1)
        {
            cursor.moveToFirst();

            Name = (TextView) findViewById(R.id.Name);
            Name.setText(cursor.getString(cursor.getColumnIndex("Name")));

            Recipe = (TextView) findViewById(R.id.Recipe);
            Recipe.setText(cursor.getString(cursor.getColumnIndex("Recipe")));



        }
    }

}
