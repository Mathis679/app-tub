package com.example.iem.apptub.classes;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iem on 21/10/2016.
 */

public class Arret {
    private String nom;
    private String adresse;
    private List<String> horaires;
    private LatLng coord;
    private String ligne;

    public Arret(String nom, String ligne, LatLng coord ){
        this.nom = nom;
        this.ligne = ligne;
        this.coord = coord;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }



    public String getNom(){
        return this.nom;
    }


    public void setNom(String nom){
        this.nom = nom;
    }

    public LatLng getCoord(){
        return this.coord;
    }


    public void setCoord(LatLng coord){
        this.coord = coord;
    }

    public String getAdresse(){
        return this.adresse;
    }


    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    public List<String> getHoraires(){
        return this.horaires;
    }

    public void setHoraires(List<String> horaires){
        if(this.horaires!=null)
            this.horaires.clear();
        this.horaires.addAll(horaires);
    }
}
