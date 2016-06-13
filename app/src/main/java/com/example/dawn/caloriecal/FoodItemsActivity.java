package com.example.dawn.caloriecal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.dawn.caloriecal.database.DatabaseFetchActivity;

public class FoodItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_items);


        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("CaloriesCal",MODE_PRIVATE,null);
        DatabaseFetchActivity databaseFetchActivity = new DatabaseFetchActivity();

        Cursor cursor = databaseFetchActivity.getFoodItems(sqLiteDatabase);

        String colName1="name";
        String colName2="calorie";

        String[] from = new String[]{colName1,colName2};
        int[] to = new int[] {R.id.food_list_name,R.id.food_list_calories};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.food_list,cursor,from,to,0);
        ListView list1 = (ListView) findViewById(R.id.content_breakfast_list);
        list1.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
