package com.example.dawn.caloriecal;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.dawn.caloriecal.database.DatabaseInitialize;

import java.io.File;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check to see if Database present or not
        if (doesDatabaseExist(this) == false) {
            setContentView(R.layout.activity_intro);
        } else {
            //Calling Overview Activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_intro);
    }

    public void initializeMethod(View view)
    {
        //Open Database
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("CalorieCal", MODE_PRIVATE, null);

        DatabaseInitialize databaseInitialize = new DatabaseInitialize(); //New Java Class

        EditText text1 = (EditText) findViewById(R.id.activity_intro_your_name);
        EditText text2 = (EditText) findViewById(R.id.activity_intro_preferred_calories);

        String name = text1.getText().toString();
        int totalCalories = Integer.parseInt(text2.getText().toString());

        String[] cuisine = getResources().getStringArray(R.array.cuisine_name);
        String[] cuisine_list = getResources().getStringArray(R.array.cuisine_list);
        String[] foodItems = getResources().getStringArray(R.array.food_items);
        int[] calories = getResources().getIntArray(R.array.Calories);

        //Database Creation
        databaseInitialize.databaseCreation(sqLiteDatabase);

        databaseInitialize.cuisineNameInitialize(sqLiteDatabase, cuisine);
        databaseInitialize.foodInitialize(sqLiteDatabase, cuisine_list, foodItems, calories);
        databaseInitialize.individualInitialize(sqLiteDatabase,name,totalCalories);

        //Call Home Activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private static boolean doesDatabaseExist(Context context) {
        File dbFile = context.getDatabasePath("CalorieCal");
        return dbFile.exists();
    }
}

