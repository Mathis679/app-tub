package com.example.iem.apptub;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.iem.apptub.classes.Arret;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by iem on 03/02/2017.
 */

public class MyRunnable implements Runnable {
    Context cont;
    List<Arret> arrets;

    public MyRunnable(Context cont){
        this.cont = cont;
    }

    @Override
    public void run() {
        ConnectivityManager conMgr = (ConnectivityManager)cont.getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {
            TubAPI tubAPI = new Retrofit.Builder().baseUrl(TubAPI.ENDPOINT).addConverterFactory(GsonConverterFactory.create()).build().create(TubAPI.class);


                tubAPI.listArret().enqueue(new Callback<List<Arret>>() {
                    @Override
                    public void onResponse(Call<List<Arret>> call, Response<List<Arret>> response) {
                        if(response.code() == 200){
                            arrets = response.body();
                        }else if(response.code() == 404){
                            Toast.makeText(cont,"(Serveur ngrok) Erreur 404 -> Non trouvé",Toast.LENGTH_SHORT).show();
                        }else if(response.code() == 301 || response.code() == 302){
                            Toast.makeText(cont,"(Serveur ngrok) Erreur 301/302 -> Redirection",Toast.LENGTH_SHORT).show();
                        }else if(response.code() == 403){
                            Toast.makeText(cont,"(Serveur ngrok) Erreur 403 -> Accès refusé",Toast.LENGTH_SHORT).show();
                        }else if(response.code() == 500 || response.code() == 503){
                            Toast.makeText(cont,"(Serveur ngrok) Erreur 500/503 -> Erreur serveur",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Arret>> call, Throwable t) {
                        Toast.makeText(cont,"(Serveur ngrok) Une erreur inconnue est survenue",Toast.LENGTH_SHORT);
                    }
                });

            System.out.println("arrets = " + arrets);

        }
    }
}
