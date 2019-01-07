package com.tabian.saveanddisplaysql;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jerrybananas on 6/1/2019.
 */

public class ListTimestampActivity extends AppCompatActivity {

    private static final String TAG = "ListTimestampActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;
    private String selectedUserId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timestamp_list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the intent extra from the ListUserIdActivity
        Intent receivedIntent = getIntent();
        //now get the name we passed as an extra
        selectedUserId = receivedIntent.getStringExtra("userid");
        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            if (data.getString(1).equals(selectedUserId)) {
                listData.add(data.getString(4));
            }
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String dt = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + dt);

                Cursor data = mDatabaseHelper.getData(); //get the id associated with that name
                int itemID = -1;
                while (data.moveToNext()) {
                    if(data.getString(1).equals(selectedUserId) && data.getString(4).equals(dt)){
                        Log.d(TAG, "onItemClick: The User Id is: " + selectedUserId +" and timestamp is: "+dt);
                        Intent editScreenIntent = new Intent(ListTimestampActivity.this, ViewDataActivity.class);
                        editScreenIntent.putExtra("userid", selectedUserId);
                        editScreenIntent.putExtra("longitude", data.getFloat(2));
                        editScreenIntent.putExtra("latitude", data.getFloat(3));
                        editScreenIntent.putExtra("dt", dt);
                        startActivity(editScreenIntent);

                    }
                }
            }
        });
    }

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
