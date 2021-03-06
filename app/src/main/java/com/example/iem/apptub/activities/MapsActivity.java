package com.example.iem.apptub.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iem.apptub.async.AsyncArret;
import com.example.iem.apptub.async.MyRunnable;
import com.example.iem.apptub.R;
import com.example.iem.apptub.classes.Arret;

import com.example.iem.apptub.classes.DirectionsJSONParser;
import com.example.iem.apptub.classes.Horaire;
import com.example.iem.apptub.database.PointsData;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.kml.KmlLayer;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    /*Variables*/
    private GoogleMap mMap;
    private static int requestInt;
    Context currCtx;
    View mapView;
    List<Arret> list;
    RelativeLayout loader;
    RelativeLayout rlItin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        loader = (RelativeLayout) findViewById(R.id.loadingPanel);

        rlItin = (RelativeLayout) findViewById(R.id.itin);

        Thread thread = new Thread(new MyRunnable(this));
        thread.start();

        currCtx = this;

        fillListFromBDD();

        fillAllListHor();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        ImageButton showDrawer = (ImageButton) findViewById(R.id.show_drawer);
        showDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickMenu(v);
            }
        });

        ImageButton refresh = (ImageButton) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRefresh(v);
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Change la map suivant le choix de l'utilisateur
                int id = item.getItemId();

                if (id == R.id.ligne1) {
                    chooseOneLayer(1);
                } else if (id == R.id.ligne2) {
                    chooseOneLayer(2);
                } else if (id == R.id.ligne3) {
                    chooseOneLayer(3);
                } else if (id == R.id.ligne4) {
                    chooseOneLayer(4);
                } else if (id == R.id.ligne5) {
                    chooseOneLayer(5);
                } else if (id == R.id.ligne6) {
                    chooseOneLayer(6);
                } else if (id == R.id.ligne7) {
                    chooseOneLayer(7);
                } else if (id == R.id.ligne21) {
                    chooseOneLayer(21);
                } else if (id == R.id.all) {
                    addAllLayer();
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);



                return true;
            }
        });



        rlItin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapsActivity.this,SearchActivity.class);
                startActivity(i);
                finish();
            }
        });

        mapView = mapFragment.getView();



    }




    /*Initialisation de la map*/

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        addAllLayer();

        LatLng latLng = new LatLng(46.205104,5.22534);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 13);
        mMap.animateCamera(cameraUpdate);


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return true;
            }
        });

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                View v = getLayoutInflater().inflate(R.layout.window_marker, null);

                LatLng latLng = marker.getPosition();

                TextView tvTitle = (TextView) v.findViewById(R.id.title_window);

                TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);

                TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);

                final Marker copyMarker = marker;


                tvLat.setText("Latitude:" + latLng.latitude);

                tvLng.setText("Longitude:"+ latLng.longitude);

                tvTitle.setText(copyMarker.getTitle());

                return v;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                Arret a = getArretById(Integer.parseInt(marker.getSnippet()));

                Intent i = new Intent(currCtx,HorairesActivity.class);

                i.putExtra("nom",marker.getTitle());
                i.putExtra("arret",a);
                startActivity(i);
            }
        });

        if(checkPermission()) {

            mMap.setMyLocationEnabled(true);
        }
        else askPermission();


        if (mapView != null &&
                mapView.findViewById(Integer.parseInt("1")) != null) {
            // Get the button view
            View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            // and next place it, on bottom right (as Google Maps app)
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
            // position on right bottom
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams.setMargins(0, 0, 30, 30);
        }

        Intent i =getIntent();
        if(i.hasExtra("latorigin")){
            LatLng origin = new LatLng(i.getDoubleExtra("latorigin",46.2), i.getDoubleExtra("lngorigin",5.21667));
            LatLng dest = new LatLng(i.getDoubleExtra("latend",46.19813), i.getDoubleExtra("lngend",5.236));

// Getting URL to the Google Directions API
            String url = getDirectionsUrl(origin, dest);

            Log.d("url", url);
            DownloadTask downloadTask = new DownloadTask();

// Start downloading json data from Google Directions API
            downloadTask.execute(url);
        }

    }


    private String getDirectionsUrl(LatLng origin,LatLng dest){


        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }



    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception download url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String>{

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> > {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(5);
                lineOptions.color(Color.RED);
            }

            // Drawing polyline in the Google Map for the i-th route
            mMap.clear();
            mMap.addPolyline(lineOptions);
            LatLng latLng = lineOptions.getPoints().get(0);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 13);
            mMap.animateCamera(cameraUpdate);
        }

    }

    /*Gestion des actions sur le NavigationDrawer*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /*Gestion de la permission de localisation*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("lol", "onRequestPermissionsResult()");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == requestInt) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                if (checkPermission())
                    Log.d("permission", "location");
                mMap.setMyLocationEnabled(true);

            } else {
                // Permission denied
                Toast.makeText(this,"Permission de localisation refusée",Toast.LENGTH_LONG).show();

            }


        }

    }

    private boolean checkPermission() { //check si la permission de location est active
        Log.d("lol", "checkPermission()");
        // Ask for permission if it wasn't granted yet
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED );
    }

    private void askPermission() { //Demande la permission
        Log.d("lol", "askPermission()");
        ActivityCompat.requestPermissions(
                this,
                new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                requestInt
        );
    }



    /*Récupération/Traitements des données*/

    public List<String[]> readCsv(int r) throws IOException { //lis un csv et retourne une liste de tableau de string -> pour chaque ligne, un tableau de string
        CSVReader reader = null;


        reader = new CSVReader(new InputStreamReader(
                currCtx.getResources().openRawResource(r),
                Charset.forName("windows-1254")),';',
                CSVParser.DEFAULT_QUOTE_CHARACTER, 0);


        List<String[]> listRead = reader.readAll();

        return listRead;
    }

    public String[] RechercheArret(String nomArret,List<String[]> csvLines){ //Recherche un arret par son nom dans un csv et retourne les horaires associés
        String[] horForLine = new String[0];

        for(int i = 0 ; i < csvLines.size() ; i++){
            if(csvLines.get(i)[0].replace("Ã©","é").replace("Ã¨","è").replace("Ã´","ô").equalsIgnoreCase(nomArret)){
                horForLine = new String[csvLines.get(i).length];
                for(int j = 1, y=0; j < csvLines.get(i).length; j++){
                    if(csvLines.get(i)[j]!=""){
                        horForLine[y] = csvLines.get(i)[j];
                        y++;
                    }

                }

            }
        }
        return horForLine;
    }

    public void fillAllListHor(){ //Remplis pour chaque arret de chaque ligne les horaires
        List<String[]> listW1 = new ArrayList<>();
        List<String[]> listW2 = new ArrayList<>();
        String[] listh1;
        String[] listh2;
        String way1 = "test";
        String way2 = "test";
        for(int i=0; i<list.size(); i++){
            List<Horaire> Llist = new ArrayList<>();
            switch(list.get(i).getIdLine()){
                case 1:
                    try {
                        listW1=readCsv(R.raw.l1_mol_oy);
                        listW2=readCsv(R.raw.l1_oy_mol);
                        way1 = "Molière -> Oyards";
                        way2 = "Oyards -> Molière";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        listW1=readCsv(R.raw.l2_ain_nor);
                        listW2=readCsv(R.raw.l2_nor_ain);
                        way1 = "Ainterexpo -> Norelan";
                        way2 = "Norelan -> Ainterexpo";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        listW1=readCsv(R.raw.l3_ala_per);
                        listW2=readCsv(R.raw.l3_per_ala);
                        way1 = "Alagnier -> Peronnas";
                        way2 = "Peronnas -> Alagnier";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    try {
                        listW1=readCsv(R.raw.l1_mol_oy);
                        listW2=readCsv(R.raw.l1_oy_mol);
                        way1 = "Molière -> Oyards";
                        way2 = "Oyards -> Molière";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            listh1 = RechercheArret(list.get(i).getNom(),listW1);
            listh2 = RechercheArret(list.get(i).getNom(),listW2);

            for(int j=0;j<listh1.length;j++){
                Horaire h = new Horaire(way1,listh1[j],1,list.get(i).getNom());
                Llist.add(h);
            }
            for(int j=0;j<listh2.length;j++){
                Horaire h = new Horaire(way2,listh2[j],1,list.get(i).getNom());
                Llist.add(h);
            }
            list.get(i).setHoraires(Llist);
        }
    }

    public Arret getArretById(int id){ //retourne un arret de la liste d'arret suivant son id
        for(int i=0;i<list.size();i++){
            if(list.get(i).getId() == id){
                return list.get(i);
            }
        }
        return null;
    }

    public void addMarkersForLine(int numLine){ //ajoute les marqueurs pour une ligne donnée
        for(int i=0;i<list.size();i++) {

            if (list.get(i).getIdLine() == numLine) {
                LatLng mark = new LatLng(list.get(i).getLatitude(),list.get(i).getLongitude());
                mMap.addMarker(new MarkerOptions().position(mark)
                        .title(list.get(i).getNom())
                        .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("logobus", 100, 100))))
                        .setSnippet(Integer.toString(list.get(i).getId()));

            }


        }
    }

    public void fillListFromBDD(){ //Remplis la liste des arrets depuis la BDD locale
        List<PointsData> point = SQLite.select().from(PointsData.class).queryList();
        list = new ArrayList<>();
        for(int i = 0; i<point.size();i++){
            Arret arret = new Arret(point.get(i).getId(),point.get(i).getIdLine());
            arret.setNom(point.get(i).getNom());
            arret.setLatitude(point.get(i).getLatitude());
            arret.setLongitude(point.get(i).getLongitude());
            list.add(arret);
        }
    }

    public Bitmap resizeMapIcons(String iconName, int width, int height){ //retaille l'icon de bus
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "mipmap", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }



    /*Gestion des kml*/

    public void addAllLayer(){ //ajoute tout les kml (lignes) sur la map
        removeAllLayer();
        KmlLayer layerline1 = null;
        KmlLayer layerline2 = null;
        KmlLayer layerline3 = null;
        KmlLayer layerline4 = null;
        KmlLayer layerline5 = null;
        KmlLayer layerline6 = null;
        KmlLayer layerline7 = null;
        KmlLayer layerline21 = null;
        try {
            layerline1 = new KmlLayer(mMap, R.raw.ligne1, this);
            layerline1.addLayerToMap();
        }catch(Exception e){
            e.printStackTrace();

        }

        try {
            layerline2 = new KmlLayer(mMap, R.raw.ligne2, this);
            layerline2.addLayerToMap();
        }catch(Exception e){
            e.printStackTrace();

        }

        try {
            layerline3 = new KmlLayer(mMap, R.raw.ligne3, this);
            layerline3.addLayerToMap();
        }catch(Exception e){
            e.printStackTrace();

        }

        try {
            layerline4 = new KmlLayer(mMap, R.raw.ligne4, this);
            layerline4.addLayerToMap();
        }catch(Exception e){
            e.printStackTrace();

        }

        try {
            layerline5 = new KmlLayer(mMap, R.raw.ligne5, this);
            layerline5.addLayerToMap();
        }catch(Exception e){
            e.printStackTrace();

        }

        try {
            layerline6 = new KmlLayer(mMap, R.raw.ligne6, this);
            layerline6.addLayerToMap();
        }catch(Exception e){
            e.printStackTrace();

        }


        try {
            layerline7 = new KmlLayer(mMap, R.raw.ligne7, this);
            layerline7.addLayerToMap();
        }catch(Exception e){
            e.printStackTrace();

        }

        try {
            layerline21 = new KmlLayer(mMap, R.raw.ligne21, this);
            layerline21.addLayerToMap();
        }catch(Exception e){
            e.printStackTrace();

        }
    }

    public void chooseOneLayer(int num){ //supprime toutes les lignes et n'affiche que celle sélectionnée avec les points de cette dernière
        removeAllLayer();
        KmlLayer layerChosen;
        switch(num){
            case 1 :
                try {
                    layerChosen = new KmlLayer(mMap, R.raw.ligne1, this);
                    layerChosen.addLayerToMap();
                    addMarkersForLine(1);
                }catch(Exception e){
                    e.printStackTrace();

                }
                break;
            case 2 :
                try {
                    layerChosen = new KmlLayer(mMap, R.raw.ligne2, this);
                    layerChosen.addLayerToMap();
                    addMarkersForLine(2);
                }catch(Exception e){
                    e.printStackTrace();

                }
                break;
            case 3 :
                try {
                    layerChosen = new KmlLayer(mMap, R.raw.ligne3, this);
                    layerChosen.addLayerToMap();
                    addMarkersForLine(3);
                }catch(Exception e){
                    e.printStackTrace();

                }
                break;
            case 4 :
                try {
                    layerChosen = new KmlLayer(mMap, R.raw.ligne4, this);
                    layerChosen.addLayerToMap();
                    addMarkersForLine(4);
                }catch(Exception e){
                    e.printStackTrace();

                }
                break;
            case 5 :
                try {
                    layerChosen = new KmlLayer(mMap, R.raw.ligne5, this);
                    layerChosen.addLayerToMap();
                    addMarkersForLine(5);
                }catch(Exception e){
                    e.printStackTrace();

                }
                break;

            case 6 :
                try {
                    layerChosen = new KmlLayer(mMap, R.raw.ligne6, this);
                    layerChosen.addLayerToMap();
                    addMarkersForLine(6);
                }catch(Exception e){
                    e.printStackTrace();

                }
                break;

            case 7 :
                try {
                    layerChosen = new KmlLayer(mMap, R.raw.ligne7, this);
                    layerChosen.addLayerToMap();
                    addMarkersForLine(7);
                }catch(Exception e){
                    e.printStackTrace();

                }
                break;

            case 21 :
                try {
                    layerChosen = new KmlLayer(mMap, R.raw.ligne21, this);
                    layerChosen.addLayerToMap();
                    addMarkersForLine(21);
                }catch(Exception e){
                    e.printStackTrace();

                }
                break;
            default :
                addAllLayer();
                break;
        }
    }

    public void removeAllLayer(){ //supprime toutes les couches de la map (points, kml...)

        mMap.clear();
    }


    /*Gestion des clics sur les 2 boutons*/

    public void onClickMenu(View view){

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);

    }

    public void onClickRefresh(View view){ //recharge les données si elles ne s'affichent pas correctement

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MapsActivity.this);

        // set title
        alertDialogBuilder.setTitle("Rafraîchissement des données");

        // set dialog message
        alertDialogBuilder
                .setMessage("Souhaitez vous rafraîchir les données ?")
                .setCancelable(false)
                .setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        setVisibleLoader(true);
                        new AsyncArret().execute(MapsActivity.this,"https://tub.bourgmapper.fr/api/stopgroups","https://tub.bourgmapper.fr/api/stops");
                        dialog.cancel();
                        MapsActivity.this.onMapReady(mMap);
                    }
                })
                .setNegativeButton("Non",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public void setVisibleLoader(boolean visible){
        if(visible)
            loader.setVisibility(View.VISIBLE);
        else
            loader.setVisibility(View.GONE);
    }

}
