package com.example.administrator.helloworld;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;


public class Search extends Activity {


    protected Cursor cursor2;
    protected SQLiteDatabase db;
    protected ListView ingList;
    protected ListView recView;


    /** Called when the activity is first created. */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        db = (new DatabaseHelper(this)).getWritableDatabase();
        ingList = (ListView) findViewById(R.id.inglistView);
        recView = (ListView) findViewById(R.id.reclistView);

        //SETS UP STRING VALUES FOR INGREDIENT LIST
        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("Eggs");
        your_array_list.add("Milk");
        your_array_list.add("Bread");

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                your_array_list );
        ingList.setAdapter(arrayAdapter);


        //THIS SETS THE LIST ITEMS TO BE CHECKED/UNCHECKED
        ingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // change the checkbox state
                CheckedTextView checkedTextView = ((CheckedTextView)view);
                checkedTextView.setChecked(!checkedTextView.isChecked());





        }
    });

}
                //Method GenerateRecipe calls for the listView "recView" to populate

    public void populate()

    {
        String selectedFromList = (ingList.getItemAtPosition(position));

        cursor2 = db.rawQuery("SELECT _id, Name, Ingredients, Recipe FROM recipe WHERE Name || ' ' || Ingredients LIKE ?",
                new String[]{"%" + selectedFromList.getText().toString() + "%"});

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                (List<String>) cursor2);
        recView.setAdapter(adapter);

    }

    public void GenerateRecipes(View view)

    {
        populate();

    }


}