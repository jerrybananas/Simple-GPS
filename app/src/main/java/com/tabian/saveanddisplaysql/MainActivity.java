package com.tabian.saveanddisplaysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Created by jerrybananas on 6/1/2019.
 */

//A big part of the backend was created by https://github.com/mitchtabian/SaveReadWriteDeleteSQLite

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText editText, editText2, editText3, editText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat formatter = new SimpleDateFormat("HH:mm:ss-dd/MM/yyyy"); // Make sure user insert date into edittext in this format.
                if (editText.length() != 0) {
                    if (editText2.length() != 0) {
                        if (editText3.length() != 0) {
                            String userid = editText.getText().toString();
                            float longitude = Float.parseFloat(editText2.getText().toString());
                            float latitude = Float.parseFloat(editText3.getText().toString());
                            if (editText4.length() != 0) {
                                String dt = editText4.getText().toString();
                                if (dt.matches("^\\d{2}:\\d{2}:\\d{2}-\\d{2}/\\d{2}/\\d{4}")) {
                                    AddData(userid, longitude, latitude, dt);
                                    editText.setText("");
                                    editText2.setText("");
                                    editText3.setText("");
                                    editText4.setText("");
                                } else {
                                    toastMessage("You must put enter timestamp as yyyy/MM/dd/HH/mm/ss!");
                                    editText4.setText("");
                                }
                            } else {
                                editText.setText("");
                                editText2.setText("");
                                editText3.setText("");
                                editText4.setText("");
                                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                                String dateObject = formatter.format(timestamp);
                                AddData(userid, longitude, latitude, dateObject);
                            }
                        } else {
                            toastMessage("You must put something in the Latitude field!");
                        }
                    } else {
                        toastMessage("You must put something in the Longitude field!");
                    }
                } else {
                    toastMessage("You must put something in the ID field!");
                }

            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListUserIdActivity.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(String userid, float longitude, float latitude, String dt) {
        boolean insertData = mDatabaseHelper.addData(userid, longitude, latitude, dt);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
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
