package com.example.dawn.caloriecal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dawn.caloriecal.database.DatabaseFetchActivity;

public class MainActivity extends AppCompatActivity {

    private String name;
    private String totalCalories;
    private String caloriesConsumed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("CalorieCal",MODE_PRIVATE,null);
        DatabaseFetchActivity databaseFetchActivity = new DatabaseFetchActivity();

        name = databaseFetchActivity.getUserName(sqLiteDatabase);
        totalCalories = Integer.toString(databaseFetchActivity.getUserTotalCalories(sqLiteDatabase));
        caloriesConsumed = Integer.toString(databaseFetchActivity.getConsumedCalories(sqLiteDatabase));

        TextView textViewName = (TextView) findViewById(R.id.content_main_name_text);
        TextView textViewTotalCalories = (TextView) findViewById(R.id.content_main_total_calories_number);
        TextView textViewConsumedCalories = (TextView) findViewById(R.id.content_main_calories_taken_number);

        textViewName.setText(name);
        textViewConsumedCalories.setText(caloriesConsumed);
        textViewTotalCalories.setText(totalCalories);


        /*CardView cardView1 = (CardView) findViewById(R.id.content_breakfast_card);
        CardView cardView2 = (CardView) findViewById(R.id.content_lunch_card);
        CardView cardView3 = (CardView) findViewById(R.id.content_dinner_card);*/


        ListView list = (ListView) findViewById(R.id.content_breakfast_drawer);

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
                if(position==1)
                {
                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                if(position==2)
                {
                    Intent intent = new Intent(MainActivity.this,CuisineItemsActivity.class);
                    startActivity(intent);
                }
                if(position==3)
                {
                    Intent intent = new Intent(MainActivity.this,FoodItemsActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void breakfastIntent(View view)
    {
        Intent intent = new Intent(this,BreakfastActivity.class);
        startActivity(intent);
    }

    public void lunchIntent(View view)
    {
        Intent intent = new Intent(this,LunchActivity.class);
        startActivity(intent);
    }

    public void dinnerIntent(View view)
    {
        Intent intent = new Intent(this,DinnerActivity.class);
        startActivity(intent);
    }

}
