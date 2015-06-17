package com.brandynburbank.cs246.androiddatastorage;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    int count = 0;
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydatabase = openOrCreateDatabase("count", MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS CountTable(Count INTEGER);");

        Cursor resultSet = mydatabase.rawQuery("Select * from CountTable", null);
        resultSet.moveToLast();
        count = resultSet.getInt(0);
        System.out.println(resultSet.getInt(0) + "**************************");

        /*
        SharedPreferences values = getPreferences(MODE_PRIVATE);
        count = values.getInt("count", 0);
        */

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(Integer.toString(count));
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

    public void advance(View v) {
        count++;
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(Integer.toString(count));
    }

    public void saveCount(View v) {
        /*
        SharedPreferences values = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = values.edit();

        editor.putInt("count", count);

        editor.commit();
        */

        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS CountTable(Count INTEGER);");
        mydatabase.execSQL("INSERT INTO CountTable VALUES(" + count + ");");


    }
}
