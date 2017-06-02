package com.example.iem.apptub.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.iem.apptub.R;

public class SearchActivity extends AppCompatActivity {

    Button butSearch;
    EditText etlatorigin;
    EditText etlngorigin;
    EditText etlatend;
    EditText etlngend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        butSearch = (Button) findViewById(R.id.butsearch);
        etlatorigin = (EditText) findViewById(R.id.etlatorigin);
        etlngorigin = (EditText) findViewById(R.id.etlngorigin);
        etlatend = (EditText) findViewById(R.id.etlatend);
        etlngend = (EditText) findViewById(R.id.etlngend);

        butSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchActivity.this, MapsActivity.class);
                i.putExtra("latorigin",Double.valueOf(etlatorigin.getText().toString()));
                i.putExtra("lngorigin",Double.valueOf(etlngorigin.getText().toString()));
                i.putExtra("latend",Double.valueOf(etlatend.getText().toString()));
                i.putExtra("lngend",Double.valueOf(etlngend.getText().toString()));
                startActivity(i);
                finish();
            }
        });
    }
}
