package com.example.iem.apptub.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iem.apptub.R;
import com.example.iem.apptub.classes.Arret;
import com.example.iem.apptub.database.Table.ArretManager;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
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
    View mapView;

    private static int requestInt;

    //int permissionCheck = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_CALENDAR);

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;




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

        ArretManager m = new ArretManager(this);
        m.open();
        m.addArret(new Arret(0,"arret 1"));

        //Arret a=m.getArret(1);
        //a.setNom("toto");
        //m.modArret(a);
        //m.supAnimal(a);

        Cursor c = m.getArret();
        if (c.moveToFirst())
        {
            do {
                Log.d("test",
                        c.getInt(c.getColumnIndex(ArretManager.KEY_ID_ARRET)) + "," +
                                c.getString(c.getColumnIndex(ArretManager.KEY_NOM_ARRET))
                );
            }
            while (c.moveToNext());
        }
        c.close(); // fermeture du curseur

// fermeture du gestionnaire
        m.close();



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

        currCtx = this;


        try {
            csvLines = readCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }



        mapView = mapFragment.getView();

    }

    public String[] RechercheArret(String nomLigne,List<String[]> csvLines){
        String[] horForLine = new String[200];

        for(int i = 0 ; i < csvLines.size() ; i++){
            if(csvLines.get(i)[0].replace("Ã©","é").replace("Ã¨","è").replace("Ã´","ô").equalsIgnoreCase(nomLigne)){

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

                TextView tvHor = (TextView) v.findViewById(R.id.tv_show_hor);

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
                Intent i = new Intent(currCtx,HorairesActivity.class);
                i.putExtra("list",RechercheArret(marker.getTitle(),csvLines));
                i.putExtra("nom",marker.getTitle());
                i.putExtra("sens",marker.getSnippet());
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

    public void onClickRefresh(View view){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MapsActivity.this);

        // set title
        alertDialogBuilder.setTitle("Rafraîchissement des données");

        // set dialog message
        alertDialogBuilder
                .setMessage("Souhaitez vous rafraîchir les données")
                .setCancelable(false)
                .setPositiveButton("Oui",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        new AsyncArret().execute(MapsActivity.this,"https://dev.tub.bourgmapper.fr/api/stopgroups","https://dev.tub.bourgmapper.fr/api/stops");
                        Toast.makeText(MapsActivity.this, "Les données ont été rechargées.", Toast.LENGTH_SHORT).show();
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


    public Bitmap resizeMapIcons(String iconName, int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "mipmap", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    public void addMarkersForLine(int numLine){
        for(int i=0;i<AsyncArret.arrets.size();i++) {
            if(numLine == 1) {
                if (AsyncArret.arrets.get(i).getLigne().equals("Ligne 1 : Norelan <> Velaine")) {
                    LatLng mark = AsyncArret.arrets.get(i).getCoord();
                    mMap.addMarker(new MarkerOptions().position(mark)
                            .title(AsyncArret.arrets.get(i).getNom())
                            .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("logobus", 100, 100))))
                            .setSnippet("Ligne 1 : Norelan <> Velaine");

                }
            }else if(numLine == 2){
                if (AsyncArret.arrets.get(i).getLigne().equals("Ligne 2 : Norelan <> Ainterexpo")) {
                    LatLng mark = AsyncArret.arrets.get(i).getCoord();
                    mMap.addMarker(new MarkerOptions().position(mark)
                            .title(AsyncArret.arrets.get(i).getNom())
                            .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("logobus", 100, 100))))
                            .setSnippet("Ligne 2 : Norelan <> Ainterexpo");

                }
            }else if(numLine == 3){
                if (AsyncArret.arrets.get(i).getLigne().equals("Ligne 3 : Péronnas Blés d'Or <> Alagnier")) {
                    LatLng mark = AsyncArret.arrets.get(i).getCoord();
                    mMap.addMarker(new MarkerOptions().position(mark)
                            .title(AsyncArret.arrets.get(i).getNom())
                            .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("logobus", 100, 100))))
                            .setSnippet("Ligne 3 : Péronnas Blés d'Or <> Alagnier");

                }
            }else if(numLine == 4){
                if (AsyncArret.arrets.get(i).getLigne().equals("Ligne 4 : St Denis Collège <> Clinique Convert/EREA La Chagne")) {
                    LatLng mark = AsyncArret.arrets.get(i).getCoord();
                    mMap.addMarker(new MarkerOptions().position(mark)
                            .title(AsyncArret.arrets.get(i).getNom())
                            .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("logobus", 100, 100))))
                            .setSnippet("Ligne 4 : St Denis Collège <> Clinique Convert/EREA La Chagne");

                }
            }else if(numLine == 5){
                if (AsyncArret.arrets.get(i).getLigne().equals("Ligne 5 : St Denis Collège <> St Denis Collège")) {
                    LatLng mark = AsyncArret.arrets.get(i).getCoord();
                    mMap.addMarker(new MarkerOptions().position(mark)
                            .title(AsyncArret.arrets.get(i).getNom())
                            .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("logobus", 100, 100))))
                            .setSnippet("Ligne 5 : St Denis Collège <> St Denis Collège");

                }
            }else if(numLine == 6){
                if (AsyncArret.arrets.get(i).getLigne().equals("Ligne 6 : Viriat Caronniers <> Ainterexpo")) {
                    LatLng mark = AsyncArret.arrets.get(i).getCoord();
                    mMap.addMarker(new MarkerOptions().position(mark)
                            .title(AsyncArret.arrets.get(i).getNom())
                            .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("logobus", 100, 100))))
                            .setSnippet("Ligne 6 : Viriat Caronniers <> Ainterexpo");

                }
            }else if(numLine == 7){
                if (AsyncArret.arrets.get(i).getLigne().equals("Ligne 7 : Viriat Caronniers <> Carré Amiot")) {
                    LatLng mark = AsyncArret.arrets.get(i).getCoord();
                    mMap.addMarker(new MarkerOptions().position(mark)
                            .title(AsyncArret.arrets.get(i).getNom())
                            .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("logobus", 100, 100))))
                            .setSnippet("Ligne 7 : Viriat Caronniers <> Carré Amiot");

                }
            }else if(numLine == 21){
                if (AsyncArret.arrets.get(i).getLigne().equals("Ligne 21 : Peloux Gare <> Sources")) {
                    LatLng mark = AsyncArret.arrets.get(i).getCoord();
                    mMap.addMarker(new MarkerOptions().position(mark)
                            .title(AsyncArret.arrets.get(i).getNom())
                            .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("logobus", 100, 100))))
                            .setSnippet("Ligne 21 : Peloux Gare <> Sources");

                }
            }

        }
    }

    private boolean checkPermission() {
        Log.d("lol", "checkPermission()");
        // Ask for permission if it wasn't granted yet
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED );
    }
    // Asks for permission
    private void askPermission() {
        Log.d("lol", "askPermission()");
        ActivityCompat.requestPermissions(
                this,
                new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                requestInt
        );
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("lol", "onRequestPermissionsResult()");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == requestInt) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                if (checkPermission())

                    mMap.setMyLocationEnabled(true);

            } else {
                // Permission denied
                Toast.makeText(this,"permission denied",Toast.LENGTH_LONG);

            }


        }

    }









}
