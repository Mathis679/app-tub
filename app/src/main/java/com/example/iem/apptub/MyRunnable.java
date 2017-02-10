package com.example.iem.apptub;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.iem.apptub.classes.Arret;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by iem on 03/02/2017.
 */

public class MyRunnable implements Runnable {
    Context cont;

    public MyRunnable(Context cont){
        this.cont = cont;
    }

    @Override
    public void run() {
        ConnectivityManager conMgr = (ConnectivityManager)cont.getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {
            TubAPI tubAPI = new RestAdapter.Builder().setEndpoint(TubAPI.ENDPOINT).build().create(TubAPI.class);
            List<Arret> arrets = tubAPI.listArret();
            System.out.println("arrets = " + arrets);
        }
    }
}
