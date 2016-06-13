package com.example.dawn.caloriecal;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
}
