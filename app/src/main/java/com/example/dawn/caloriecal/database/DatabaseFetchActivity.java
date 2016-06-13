package com.example.dawn.caloriecal.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Dawn on 6/13/2016.
 */
public class DatabaseFetchActivity extends AppCompatActivity
{
    public Cursor getCuisine(SQLiteDatabase sqLiteDatabase)
    {
        Cursor cursor = sqLiteDatabase.rawQuery("select cuisine_name FROM cuisine;",null);
        return cursor;
    }

    public Cursor getMealItems(SQLiteDatabase sqLiteDatabase,String meal_time)
    {
        Cursor cursor = sqLiteDatabase.rawQuery("select name,calorie from meals WHERE meal_time='"+meal_time+"';",null);
        return cursor;
    }

    public int getConsumedCalories(SQLiteDatabase sqLiteDatabase)
    {
        int calories;
        Cursor cursor = sqLiteDatabase.rawQuery("select sum(calories) as total_calories FROM meals;",null);
        calories = cursor.getInt(cursor.getColumnIndex("total_calories"));
        return calories;
    }

    public String getUserName(SQLiteDatabase sqLiteDatabase)
    {
        String userName;
        Cursor cursor = sqLiteDatabase.rawQuery("select name from individual;",null);
        userName = cursor.getString(cursor.getColumnIndex("name"));
        return userName;
    }

    public int getUserTotalCalories(SQLiteDatabase sqLiteDatabase)
    {
        int calories;
        Cursor cursor = sqLiteDatabase.rawQuery("Select calories from individual;",null);
        calories=cursor.getInt(cursor.getColumnIndex("calories"));
        return calories;
    }

    public Cursor getFoodItems(SQLiteDatabase sqLiteDatabase)
    {
        Cursor cursor = sqLiteDatabase.rawQuery("select name,calories from foodie;",null);
        return cursor;
    }
}
