package com.example.dawn.caloriecal.database;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Dawn on 6/13/2016.
 */
public class DatabaseInitialize extends AppCompatActivity {
    private static String INSERT = "INSERT INTO ";
    private static String CREATE = "CREATE TABLE IF NOT EXISTS ";

    public void databaseCreation(SQLiteDatabase myDatabase) {

        myDatabase.execSQL(CREATE+"Cuisine( _id INteger primary key autoincrement,cuisine_name varchar(20) not null");
        myDatabase.execSQL(CREATE + "Foodie( _id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(30) NOT NULL,cuisine VARCHAR(20) not null,calorie INTEGER not null);");
        myDatabase.execSQL(CREATE + "Meals( _id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(30) NOT NULL,cuisine VARCHAR(20) not null,calorie INTEGER not null);");
    }

    public void cuisineNameInitialize(SQLiteDatabase myDatabase, String[] cuisine_name) {
        for (String item : cuisine_name) {
            myDatabase.execSQL(INSERT + "Cuisine(cuisine_name) VALUES ('" + item + "');");
        }
    }

    public void foodInitialize(SQLiteDatabase myDatabase, String[] cuisine_name,String[] food_name,int[] calorie) {

        int size = cuisine_name.length,i;
        for (i=0;i<size;i++) {

            myDatabase.execSQL(INSERT + "Foodie(name,cuisine,calorie) VALUES ('" + food_name[i] + "','"+cuisine_name+"','"+calorie[i]+"');");
        }
    }
}
