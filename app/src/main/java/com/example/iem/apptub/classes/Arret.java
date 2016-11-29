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
    private int id;
    private int idLine;

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

    public int getId(){ return this.id; }

    public int getIdLine(){ return this.idLine; }
}
