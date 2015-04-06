package com.example.administrator.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class Browse extends Activity {

    protected EditText searchText;
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected ListAdapter adapter;
    protected ListView recipeList;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        db = (new DatabaseHelper(this)).getWritableDatabase();
        searchText = (EditText) findViewById(R.id.searchText);
        recipeList = (ListView) findViewById(R.id.list);
        registerListClickCallBack();


        }


    //CREATES AND POPULATES THE LIST FROM THE DATABASE
    public void search(View view) {
        // || is the concatenation operation in SQLite
        cursor = db.rawQuery("SELECT _id, Name, Ingredients, Recipe FROM recipe WHERE Name || ' ' || Ingredients LIKE ?",
                new String[]{"%" + searchText.getText().toString() + "%"});
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.recipe_list_item,
                cursor,
                new String[]{"Name"},
                new int[]{R.id.Name});
        recipeList.setAdapter(adapter);

    }
//--------THIS IS THE METHOD FOR THE ONCLICK FUNCTION FOR THE LIST.-----------------------------
 private void registerListClickCallBack() {
    recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
            Intent intent = new Intent(Browse.this, recipe.class);
            Cursor cursor = (Cursor) adapter.getItem(position);
            intent.putExtra("RECIPE_ID", cursor.getInt(cursor.getColumnIndex("_id")));
            startActivity(intent);
        }
    });




}


}