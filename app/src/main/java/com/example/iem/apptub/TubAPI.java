package com.example.iem.apptub;

import com.example.iem.apptub.classes.Arret;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by iem on 03/02/2017.
 */



public interface TubAPI {


    public static final String ENDPOINT = "http://88f8509c.ngrok.io/index.php";

    @GET("/stops/all")
    List<Arret> listArret();

    /*@GET("/stops/search/{idstop}")
    List<Arret> searchArret(@Path("idstop") String idstop);*/
}
