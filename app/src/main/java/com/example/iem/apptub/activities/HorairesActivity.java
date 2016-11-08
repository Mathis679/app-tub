package com.example.iem.apptub.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.iem.apptub.R;

import java.util.ArrayList;

public class HorairesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaires);

        Intent i = this.getIntent();

        String nom = i.getStringExtra("nom");

        String[] list = i.getStringArrayExtra("list");

        String sens = i.getStringExtra("sens");

        LinearLayout lla = (LinearLayout) findViewById(R.id.linlayhora);

        TextView tvAller = (TextView) findViewById(R.id.tv_aller);

        if(nom == null || list == null || list.length == 0){
            setTitle("Connais pas");
            TextView tv = new TextView(this);
            tv.setText("Pas d'horaires renseigné pour cet arret");
            lla.addView(tv);
        }else {
            tvAller.setText(sens);
            setTitle(nom);
            for (int j = 0; j < list.length; j++) {
                if(list[j] != null){
                    TextView tv = new TextView(this);
                    tv.setText(list[j]);
                    lla.addView(tv);
                }

            }
        }

    }
}
