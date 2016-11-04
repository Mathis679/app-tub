package com.example.iem.apptub.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.iem.apptub.R;
import com.example.iem.apptub.classes.Arret;
import com.google.android.gms.maps.model.LatLng;

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

/**
 * Created by iem on 04/11/2016.
 */

public class AsyncArret extends AsyncTask<Object, Integer, String> {

    BufferedReader in;
    String Html="";
    String data = "";
    Context cont;
    ListView listView;
    static ArrayList<Arret> arrets;
    private static String[]URL;


    @Override
    protected String doInBackground(Object[] urls) {
        URL tmpUrl=null;
        cont = (Context)urls[0];

        //infoServeurs = (ArrayList<InfoServeur>)urls[3];


        try {


            tmpUrl= new URL((String)urls[1]); // revoir
            HttpURLConnection urlConnection = (HttpURLConnection) tmpUrl.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));


                while ((data = in.readLine()) != null) {

                    Html += data;

                }

                in.close();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Html;
    }

    protected void onProgressUpdate(Integer... progress) {

    }

    protected void onPostExecute(String Html) {
        arrets = new ArrayList<>();
        try {
            JSONObject reader2 = new JSONObject(Html);
            System.out.println("reader2Dom = " + reader2);
            JSONArray jsonArray = reader2.optJSONArray("stopgroups");

            ArrayList<HashMap<String,String>> listItem = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;
            URL = new String[jsonArray.length()];

            for(int i = 0; i <jsonArray.length();i++){

                JSONObject jsObSG = jsonArray.getJSONObject(i);
                if(jsObSG.get("way").equals("O")){
                    JSONObject jsObLine = jsObSG.getJSONObject("line");
                    JSONObject jsObStop = jsObSG.getJSONObject("stop");
                    Arret arret = new Arret(jsObStop.getString("name"),jsObLine.getString("label"), new LatLng(jsObStop.getDouble("latitude"),jsObStop.getDouble("longitude")));
                    arrets.add(arret);
                }

                //URL[i] = jsonObject.optString("name").toString();

                //map = new HashMap<String,String>();
                //map.put("name",URL[i]);
                //listItem.add(map);
            }

//            SimpleAdapter mSchedule = new SimpleAdapter(cont ,listItem, R.layout.activity_test,
//                    new String[]{"name"}, new int[]{R.id.textView5});
//            listView.setAdapter(mSchedule);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}