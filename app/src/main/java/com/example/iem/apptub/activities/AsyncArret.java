package com.example.iem.apptub.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.iem.apptub.R;
import com.example.iem.apptub.classes.Arret;
import com.example.iem.apptub.database.PointsData;
import com.google.android.gms.maps.model.LatLng;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by iem on 04/11/2016.
 */

public class AsyncArret extends AsyncTask<Object, Integer, String[]> {

    BufferedReader in;
    String Html="";
    String data = "";
    Context cont;
    ListView listView;
    ArrayList<Arret> arrets;
    private static String[]URL;
    boolean isConnected = true;


    @Override
    protected String[] doInBackground(Object[] urls) {

        URL tmpUrl=null;
        cont = (Context)urls[0];

        String[] datas = new String[2];

        //infoServeurs = (ArrayList<InfoServeur>)urls[3];

        ConnectivityManager conMgr = (ConnectivityManager)cont.getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {


            try {


                tmpUrl= new URL((String)urls[1]); // revoir
                HttpURLConnection urlConnection = (HttpURLConnection) tmpUrl.openConnection();
                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));


                    while ((data = in.readLine()) != null) {

                        Html += data;

                    }

                    in.close();
                    datas[0] = Html;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {

                Html = "";
                tmpUrl= new URL((String)urls[2]); // revoir
                HttpURLConnection urlConnection = (HttpURLConnection) tmpUrl.openConnection();
                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));


                    while ((data = in.readLine()) != null) {

                        Html += data;

                    }

                    in.close();
                    datas[1] = Html;
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            isConnected = false;
            //Toast.makeText(cont,"Vous semblez etre déconnecté", Toast.LENGTH_SHORT).show();
        }
        return datas;
    }

    protected void onProgressUpdate(Integer... progress) {

    }

    protected void onPostExecute(String[] datas) {

        if(!isConnected)
            Toast.makeText(cont,"Vous semblez etre déconnecté", Toast.LENGTH_SHORT).show();
        if(datas[0] == null || datas[1] == null){
            Toast.makeText(cont, "(Serveur axel) Les données n'ont pas été chargées correctement, veuillez réessayer plus tard",Toast.LENGTH_SHORT).show();
        }else{
            parseStopGroups(datas[0]);
            parseStops(datas[1]);
            fillBDD();
            Toast.makeText(cont, "(Serveur axel) Les données ont bien été chargées",Toast.LENGTH_SHORT).show();
        }




    }

    private void parseStopGroups(String data){
        arrets = new ArrayList<>();
        try {
            JSONObject reader2 = new JSONObject(data);
            System.out.println("reader2Dom = " + reader2);
            JSONArray jsonArray = reader2.optJSONArray("stopgroups");


            URL = new String[jsonArray.length()];

            for(int i = 0; i <jsonArray.length();i++){

                JSONObject jsObSG = jsonArray.getJSONObject(i);
                if(jsObSG.get("way").equals("O")){

                    Arret arret = new Arret(jsObSG.getInt("stop_id"),jsObSG.getInt("line_id"));
                    arrets.add(arret);
                }


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseStops(String data){

        try {
            JSONObject reader2 = new JSONObject(data);
            System.out.println("reader2Dom = " + reader2);
            JSONArray jsonArray = reader2.optJSONArray("stops");


            URL = new String[jsonArray.length()];

            for(int i = 0; i <jsonArray.length();i++){

                JSONObject jsObS = jsonArray.getJSONObject(i);

                for(int j=0; j<arrets.size(); j++){


                    if(jsObS.getInt("id") == arrets.get(j).getId()){

                        arrets.get(j).setNom(jsObS.getString("label"));
                        arrets.get(j).setLatitude(jsObS.getDouble("latitude"));
                        arrets.get(j).setLongitude(jsObS.getDouble("longitude"));

                    }

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void fillBDD(){
        List<PointsData> point = SQLite.select().from(PointsData.class).queryList();
        if(point!=null){
            for(int i=0; i<point.size();i++){
                point.get(i).delete();
            }
        }
        for(int i=0; i<arrets.size();i++){
            PointsData pointsData = new PointsData();
            pointsData.setId(i);
            pointsData.setLatitude(arrets.get(i).getLatitude());
            pointsData.setLongitude(arrets.get(i).getLongitude());
            pointsData.setNom(arrets.get(i).getNom());
            pointsData.setLigne(arrets.get(i).getNomLigne());
            pointsData.setAdresse("testadresse");
            pointsData.setIdLine(arrets.get(i).getIdLine());
            pointsData.save();
        }
    }

}
