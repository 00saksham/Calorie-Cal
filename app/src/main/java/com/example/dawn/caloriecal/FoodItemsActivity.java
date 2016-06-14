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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.dawn.caloriecal.database.DatabaseFetchActivity;

public class FoodItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_items);


        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("CalorieCal",MODE_PRIVATE,null);
        DatabaseFetchActivity databaseFetchActivity = new DatabaseFetchActivity();

        Cursor cursor = databaseFetchActivity.getFoodItems(sqLiteDatabase);

        String colName1="name";
        String colName2="calorie";

        String[] from = new String[]{colName1,colName2};
        int[] to = new int[] {R.id.food_list_name,R.id.food_list_calories};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.food_list,cursor,from,to,0);
        ListView list1 = (ListView) findViewById(R.id.content_food_items_list);
        list1.setAdapter(adapter);


        ListView list = (ListView) findViewById(R.id.content_content_food_items_drawer);

        //Getting Strings to set In Navigation Drawer List
        String[] array=getResources().getStringArray(R.array.navigation_drawer);

        //Set the Navigation List
        ArrayAdapter<String> adapterNav = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, array);
        list.setAdapter(adapterNav);


        //If an Item is clicked inside the NavigationListView
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //Position Related Data
                if(position==0)
                {
                    Intent intent = new Intent(FoodItemsActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if(position==1)
                {
                    Intent intent = new Intent(FoodItemsActivity.this,CuisineItemsActivity.class);
                    startActivity(intent);
                }
                if(position==2)
                {
                    Intent intent = new Intent(FoodItemsActivity.this,FoodItemsActivity.class);
                    startActivity(intent);
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(FoodItemsActivity.this,AddFoodItemsActivity.class);
                startActivity(intent);
            }
        });
    }

}
