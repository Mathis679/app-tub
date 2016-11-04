package com.example.iem.apptub.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.iem.apptub.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.kml.KmlLayer;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public int test1;
    private DrawerLayout menuLayout;
    ActionBarDrawerToggle menuToogle;
    Context currCtx;
    private String[] horArret;
    private String[] testDom;
    List<String[]> csvLines = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                }

                else if (id == R.id.ligne6) {
                    chooseOneLayer(6);
                }

                else if (id == R.id.ligne7) {
                    chooseOneLayer(7);
                }


                else if (id == R.id.ligne21) {
                    chooseOneLayer(21);
                }else if (id == R.id.all) {
                    addAllLayer();
                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        currCtx = this;


        try {
            csvLines = readCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        for(int i=0;i<csvLines.size();i++){
//            testDom = new String[csvLines.get(i).length];
//            testDom[i] = csvLines.get(i);
//            System.out.println("testDom = " + testDom[i]);
//        }


//        horArret = new String[csvLines.get(1).length];
//        for(int i=0;i<csvLines.get(1).length;i++){
//            horArret[i] = csvLines.get(1)[i];
//        }


    }

    public String[] RechercheArret(String nomLigne,List<String[]> csvLines){
        String[] horForLine = null;
        for(int i = 0 ; i < csvLines.size() ; i++){
            if(csvLines.get(i)[0].equals(nomLigne)){
                horForLine = new String[csvLines.get(i).length];
                for(int j = 1; j < csvLines.get(i).length; j++){
                    horForLine[j-1] = csvLines.get(i)[j];
                }

            }
        }
        return horForLine;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        addAllLayer();

        LatLng latLng = new LatLng(46.2,5.2167 );
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 13);
        mMap.animateCamera(cameraUpdate);


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent i = new Intent(currCtx,HorairesActivity.class);
                i.putExtra("list",RechercheArret(marker.getTitle(),csvLines));
                i.putExtra("nom",marker.getTitle());
                startActivity(i);
                return true;
            }
        });



    }

    public void addAllLayer(){
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

    public void chooseOneLayer(int num){
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

    public void removeAllLayer(){

        mMap.clear();
    }

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




    public void onClickMenu(View view){

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);

    }

    public List<String[]> readCsv() throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(
                currCtx.getResources().openRawResource(R.raw.l1_oy_mol),
                Charset.forName("windows-1254")),';',
                CSVParser.DEFAULT_QUOTE_CHARACTER, 0);

        List<String[]> listRead = reader.readAll();
        return listRead;
    }

    public Bitmap resizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "mipmap", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    public void addMarkersForLine(int numLine){
        for(int i=0;i<AsyncArret.arrets.size();i++) {
            if(numLine == 1) {
                if(AsyncArret.arrets.get(i).getLigne().equals("Ligne 1 : Norelan <> Velaine")) {
                    LatLng mark = AsyncArret.arrets.get(i).getCoord();
                     mMap.addMarker(new MarkerOptions().position(mark).title(AsyncArret.arrets.get(i).getNom()).icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("logobus",100,100))));

                }
                //Seulement pour la ligne 1 pour l'instant
//                else if(numLine == 2){
//                    LatLng sydney = new LatLng(46.2, 5.2167);
//                    mMap.addMarker(new MarkerOptions().position(sydney).title("Arret").icon(BitmapDescriptorFactory.fromResource(R.mipmap.logobus)));
//                }else if(numLine == 3){
//                    LatLng sydney = new LatLng(46.2, 5.2167);
//                    mMap.addMarker(new MarkerOptions().position(sydney).title("Arret").icon(BitmapDescriptorFactory.fromResource(R.mipmap.logobus)));
//                }else if(numLine == 4){
//                    LatLng sydney = new LatLng(46.2, 5.2167);
//                    mMap.addMarker(new MarkerOptions().position(sydney).title("Arret").icon(BitmapDescriptorFactory.fromResource(R.mipmap.logobus)));
//                }else if(numLine == 5){
//                    LatLng sydney = new LatLng(46.2, 5.2167);
//                    mMap.addMarker(new MarkerOptions().position(sydney).title("Arret").icon(BitmapDescriptorFactory.fromResource(R.mipmap.logobus)));
//                }else if(numLine == 6){
//                    LatLng sydney = new LatLng(46.2, 5.2167);
//                    mMap.addMarker(new MarkerOptions().position(sydney).title("Arret").icon(BitmapDescriptorFactory.fromResource(R.mipmap.logobus)));
//                }else if(numLine == 7){
//                    LatLng sydney = new LatLng(46.2, 5.2167);
//                    mMap.addMarker(new MarkerOptions().position(sydney).title("Arret").icon(BitmapDescriptorFactory.fromResource(R.mipmap.logobus)));
//                }else if(numLine == 21){
//                    LatLng sydney = new LatLng(46.2, 5.2167);
//                    mMap.addMarker(new MarkerOptions().position(sydney).title("Arret").icon(BitmapDescriptorFactory.fromResource(R.mipmap.logobus)));
//                }
            }
        }
    }


}
