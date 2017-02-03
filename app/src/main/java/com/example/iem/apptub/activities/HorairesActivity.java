package com.example.iem.apptub.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
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

        tvAller.setGravity(Gravity.CENTER_HORIZONTAL);

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
                    LinearLayout llTv = new LinearLayout(this);
                    llTv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                    llTv.setGravity(Gravity.CENTER);


                    TextView tv = new TextView(this);
                    tv.setText(list[j]);
                    tv.setGravity(Gravity.CENTER_HORIZONTAL);

                    tv.setTextColor(Color.BLACK);

                    if(j%2 == 0) {
                        llTv.setBackgroundColor(Color.rgb(181,179,175));
                    }


                    llTv.addView(tv);
                    lla.addView(llTv);
                }

            }
        }

    }
}
