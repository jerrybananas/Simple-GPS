package com.tabian.saveanddisplaysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jerrybananas on 6/1/2019.
 */

public class ViewDataActivity extends AppCompatActivity {

    private static final String TAG = "ViewDataActivity";

    private EditText editable_userid, editable_longitude, editable_latitude, editable_dt;

    DatabaseHelper mDatabaseHelper;

    private String selectedDt,selectedUserId;
    private float selectedLongitude, selectedLatitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data_layout);
        editable_userid = (EditText) findViewById(R.id.userid);
        editable_longitude = (EditText) findViewById(R.id.longitude);
        editable_latitude = (EditText) findViewById(R.id.latitude);
        editable_dt = (EditText) findViewById(R.id.dt);


        //get the intent extra from the ListUserIdActivity
        Intent receivedIntent = getIntent();

        selectedUserId = receivedIntent.getStringExtra("userid");
        selectedLongitude = receivedIntent.getFloatExtra("longitude",0);
        selectedLatitude = receivedIntent.getFloatExtra("latitude",0);
        selectedDt = receivedIntent.getStringExtra("dt");


        //set the text to show the current selected name
        editable_userid.setText(selectedUserId);
        editable_longitude.setText(Float.toString(selectedLongitude));
        editable_latitude.setText(Float.toString(selectedLatitude));
        editable_dt.setText(selectedDt);


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
























