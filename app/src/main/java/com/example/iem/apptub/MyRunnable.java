package com.example.iem.apptub;

import com.example.iem.apptub.classes.Arret;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by iem on 03/02/2017.
 */

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        TubAPI tubAPI = new RestAdapter.Builder().setEndpoint(TubAPI.ENDPOINT).build().create(TubAPI.class);
        List<Arret> arrets = tubAPI.listArret();
        System.out.println("arrets = " + arrets);
    }
}
