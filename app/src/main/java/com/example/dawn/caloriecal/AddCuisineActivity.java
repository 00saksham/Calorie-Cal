package com.example.dawn.caloriecal;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.dawn.caloriecal.database.DatabaseInitialize;

public class AddCuisineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cuisine);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add();
                Intent intent = new Intent(AddCuisineActivity.this,CuisineItemsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void add()
    {
        EditText editText = (EditText) findViewById(R.id.content_add_cuisine_cuisine);
        String cuisine = editText.getText().toString();

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("CalorieCal",MODE_PRIVATE,null);
        DatabaseInitialize databaseInitialize = new DatabaseInitialize();

        databaseInitialize.add_cuisine(sqLiteDatabase,cuisine);
    }
}
