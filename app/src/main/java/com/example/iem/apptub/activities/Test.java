package com.example.iem.apptub.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.iem.apptub.R;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        new AsyncArret().execute(this,"http://tub.lebot.xyz/api/stopgroups");
    }
}
