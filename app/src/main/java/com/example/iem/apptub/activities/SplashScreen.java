package com.example.iem.apptub.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.iem.apptub.R;
import com.example.iem.apptub.async.AsyncArret;


public class SplashScreen extends Activity {

    RelativeLayout loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        loader = (RelativeLayout) findViewById(R.id.loadingPanel);

        setVisibleLoader(true);


        new AsyncArret().execute(this,"https://tub.bourgmapper.fr/api/stopgroups","https://tub.bourgmapper.fr/api/stops");
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this,MapsActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

    public void setVisibleLoader(boolean visible){
        if(visible)
            loader.setVisibility(View.VISIBLE);
        else
            loader.setVisibility(View.GONE);
    }
}