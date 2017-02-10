package com.example.iem.apptub.classes;

import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iem on 21/10/2016.
 */

public class Arret implements Serializable{
    private String nom;
    private String adresse;
    private List<Horaire> horaires;
    private String ligne;
    private int id;
    private int idLine;
    private double longitude;
    private double latitude;
    private String nomLigne;

    public void setHoraires(List<Horaire> horaires) {
        this.horaires = horaires;
    }

    public List<Horaire> getHoraires() {
        return horaires;
    }



    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getNomLigne() {
        return nomLigne;
    }

    public void setNomLigne(String nomLigne) {
        this.nomLigne = nomLigne;
    }


    public Arret(int id, int idLine ){
        this.id = id;
        this.idLine = idLine;


        switch(idLine){
            case 1:
                this.ligne = "Ligne 1 : Norelan <> Velaine";
                break;
            case 2:
                this.ligne = "Ligne 2 : Norelan <> Ainterexpo";
                break;
            case 3:
                this.ligne = "Ligne 3 : Péronnas Blés d'Or <> Alagnier";
                break;
            case 4:
                this.ligne = "Ligne 4 : St Denis Collège <> Clinique Convert/EREA La Chagne";
                break;
            case 5:
                this.ligne = "Ligne 5 : St Denis Collège <> St Denis Collège";
                break;
            case 6:
                this.ligne = "Ligne 6 : Viriat Caronniers <> Ainterexpo";
                break;
            case 7:
                this.ligne = "Ligne 7 : Viriat Caronniers <> Carré Amiot";
                break;
            case 8:
                this.ligne = "Ligne 21 : Peloux Gare <> Sources";
                break;
        }
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


    public String getAdresse(){
        return this.adresse;
    }


    public void setAdresse(String adresse){
        this.adresse = adresse;
    }


    public int getId(){ return this.id; }

    public void setId(int id){ this.id = id; }

    public int getIdLine(){ return this.idLine; }
}
