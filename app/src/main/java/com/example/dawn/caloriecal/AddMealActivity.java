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
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.dawn.caloriecal.database.DatabaseFetchActivity;
import com.example.dawn.caloriecal.database.DatabaseInitialize;

public class AddMealActivity extends AppCompatActivity {

    private String meal;
    private String food;
    private RadioGroup radioGroup;
    int radio_checked;
    int calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        final SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("CalorieCal", MODE_PRIVATE, null);
        DatabaseFetchActivity databaseFetchActivity = new DatabaseFetchActivity();


        // Spinner TypeDrop down elements

        String[] type = {"Breakfast", "Lunch", "Dinner"};

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);

        // Drop down layout style - Spinner
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.content_add_meal_spinner_meal_time);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                meal = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Get Cursor
        Cursor cursor = databaseFetchActivity.getFoodName(sqLiteDatabase);

        //Create String to Get Cursor Elements
        String[] array = new String[cursor.getCount()];
        int i = 0;

        //Move from Cursor to String
        cursor.moveToFirst();
        array[i] = cursor.getString(cursor.getColumnIndex("name"));

        while (cursor.moveToNext()) {
            i++;
            String categoryName = cursor.getString(cursor.getColumnIndex("name"));
            array[i] = categoryName;
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array);

        // Drop down layout style - Spinner
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner1 = (Spinner) findViewById(R.id.content_add_meal_spinner_meal_items);
        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                food = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        addListenerOnButton();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_meal(sqLiteDatabase);
                Intent intent = new Intent(AddMealActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


    public void addListenerOnButton() {
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radio_checked = radioGroup.getCheckedRadioButtonId();
    }

    private void add_meal(SQLiteDatabase sqLiteDatabase)
    {
        DatabaseFetchActivity databaseFetchActivity = new DatabaseFetchActivity();

        if(radio_checked==0)
        {
            calories = (int) databaseFetchActivity.getSpecificFood(sqLiteDatabase,food)/3;
        }
        else if(radio_checked==1)
        {
            calories = (int) databaseFetchActivity.getSpecificFood(sqLiteDatabase,food)/2;
        }
        else
        {
            calories = (int) databaseFetchActivity.getSpecificFood(sqLiteDatabase,food);
        }


        DatabaseInitialize databaseInitialize = new DatabaseInitialize();
        databaseInitialize.add_meal(sqLiteDatabase,food,meal,calories);

    }

}
