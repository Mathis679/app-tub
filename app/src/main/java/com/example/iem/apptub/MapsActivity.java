package com.example.iem.apptub;

import android.content.Intent;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.kml.KmlLayer;




public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    public int test1;
    private DrawerLayout menuLayout;
    ActionBarDrawerToggle menuToogle;


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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //addAllLayer();

        //Intent i = new Intent(this,HorairesActivity.class);
        //startActivity(i);

        chooseOneLayer(21);


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(46.2,5.2167);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Position Bourg-en-Bresse"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        float zoomLevel = 13; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,zoomLevel));
    }

    public void addAllLayer(){
        removeAllLayer();
        KmlLayer layerline1 = null;
        KmlLayer layerline2 = null;
        KmlLayer layerline3 = null;
        KmlLayer layerline4 = null;
        KmlLayer layerline5 = null;
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
                }catch(Exception e){
                    e.printStackTrace();

                }
                break;
            case 2 :
                try {
                    layerChosen = new KmlLayer(mMap, R.raw.ligne2, this);
                    layerChosen.addLayerToMap();
                }catch(Exception e){
                    e.printStackTrace();

                }
                break;
            case 3 :
                try {
                    layerChosen = new KmlLayer(mMap, R.raw.ligne3, this);
                    layerChosen.addLayerToMap();
                }catch(Exception e){
                    e.printStackTrace();

                }
                break;
            case 4 :
                try {
                    layerChosen = new KmlLayer(mMap, R.raw.ligne4, this);
                    layerChosen.addLayerToMap();
                }catch(Exception e){
                    e.printStackTrace();

                }
                break;
            case 5 :
                try {
                    layerChosen = new KmlLayer(mMap, R.raw.ligne5, this);
                    layerChosen.addLayerToMap();
                }catch(Exception e){
                    e.printStackTrace();

                }
                break;
            case 21 :
                try {
                    layerChosen = new KmlLayer(mMap, R.raw.ligne21, this);
                    layerChosen.addLayerToMap();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickMenu(View view){

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);

    }



    }
