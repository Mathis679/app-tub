package com.example.iem.apptub.interfaces;

import com.example.iem.apptub.classes.Arret;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by iem on 03/02/2017.
 */



public interface TubAPI {


    public static final String ENDPOINT = "http://e18cc850.ngrok.io/index.php/";

    @GET("stops/all")
    Call<List<Arret>> listArret();

}
