package com.example.dawn.caloriecal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.dawn.caloriecal.database.DatabaseFetchActivity;
import com.example.dawn.caloriecal.database.DatabaseInitialize;

public class AddFoodItemsActivity extends AppCompatActivity {


    private String cuisine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_items);


        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("CalorieCal", MODE_PRIVATE, null);
        DatabaseFetchActivity databaseFetchActivity = new DatabaseFetchActivity();

        //Get Cursor
        Cursor cursor = databaseFetchActivity.getCuisine(sqLiteDatabase);

        //Create String to Get Cursor Elements
        String[] array = new String[cursor.getCount()];
        int i = 0;

        //Move from Cursor to String
        cursor.moveToFirst();
        array[i] = cursor.getString(cursor.getColumnIndex("cuisine_name"));

        while (cursor.moveToNext()) {
            i++;
            String categoryName = cursor.getString(cursor.getColumnIndex("cuisine_name"));
            array[i] = categoryName;
        }


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array);

        // Drop down layout style - Spinner
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner1 = (Spinner) findViewById(R.id.content_add_food_items_add_cuisine);
        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                             cuisine = parent.getItemAtPosition(position).toString();

                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add();
                Intent intent = new Intent(AddFoodItemsActivity.this,FoodItemsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void add()
    {

        EditText editText = (EditText) findViewById(R.id.content_add_food_items_food_name);
        String food = editText.getText().toString();

        EditText editText1 = (EditText) findViewById(R.id.content_add_food_itens_food_calories);
        int calories = Integer.parseInt(editText1.getText().toString());

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("CalorieCal",MODE_PRIVATE,null);
        DatabaseInitialize databaseInitialize = new DatabaseInitialize();

        databaseInitialize.add_food(sqLiteDatabase,food,cuisine,calories);
    }
}
