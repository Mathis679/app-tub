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
import com.example.iem.apptub.classes.Arret;

import java.util.ArrayList;

public class HorairesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaires);


        Intent i = this.getIntent();

        String nom = i.getStringExtra("nom");


        Arret a = (Arret) i.getSerializableExtra("arret");


        LinearLayout lla = (LinearLayout) findViewById(R.id.linlayhora);

        TextView tvAller = (TextView) findViewById(R.id.tv_aller);

        tvAller.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout llr = (LinearLayout) findViewById(R.id.linlayhorr);

        TextView tvRetour = (TextView) findViewById(R.id.tv_ret);

        tvRetour.setGravity(Gravity.CENTER_HORIZONTAL);

        if(nom == null || a == null || a.getHoraires()==null || a.getHoraires().size() == 0){
            setTitle("Pas de nom");
            TextView tv = new TextView(this);
            tv.setText("Pas d'horaires renseigné pour cet arret");
            lla.addView(tv);
        }else {

            tvAller.setText(a.getHoraires().get(0).getWay());

            for(int y=0; y<a.getHoraires().size(); y++){
                if(!a.getHoraires().get(y).getWay().equals(tvAller.getText())){
                    tvRetour.setText(a.getHoraires().get(y).getWay());
                    break;
                }
            }


            setTitle(nom);

            int aller = 0;
            int retour = 0;

            for (int j = 0; j < a.getHoraires().size() ; j++) {

                if(a.getHoraires().get(j) != null && a.getHoraires().get(j).getHour() != null){
                    LinearLayout llTv = new LinearLayout(this);
                    llTv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                    llTv.setGravity(Gravity.CENTER);


                    TextView tv = new TextView(this);
                    tv.setText(a.getHoraires().get(j).getHour());
                    tv.setGravity(Gravity.CENTER_HORIZONTAL);

                    tv.setTextColor(Color.BLACK);

                    if(a.getHoraires().get(j).getWay().equals(tvAller.getText())){
                        if(aller%2 == 0) {
                            llTv.setBackgroundColor(Color.rgb(181,179,175));
                        }


                        llTv.addView(tv);

                        lla.addView(llTv);
                        aller++;
                    }else{
                        if(retour%2 == 0) {
                            llTv.setBackgroundColor(Color.rgb(181,179,175));
                        }


                        llTv.addView(tv);

                        llr.addView(llTv);
                        retour++;
                    }


                }

            }
        }

    }
}
