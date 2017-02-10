package com.example.iem.apptub.database;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by iem on 03/02/2017.
 */


@Table(database = MyDatabase.class)

public class PointsData extends BaseModel {

    @Column
    @PrimaryKey
    int id;

    @Column
    double latitude;

    @Column
    double longitude;

    @Column
    String nom;

    @Column
    String adresse;

    @Column
    String ligne;

    @Column
    int idLine;

    public void setId(int id) {
        this.id = id;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {

        this.latitude = latitude;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    public void setLigne(String ligne){
        this.ligne = ligne;
    }

    public void setIdLine(int idLine)
    {
        this.idLine = idLine;
    }

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getLigne() {
        return ligne;
    }

    public int getIdLine() {
        return idLine;
    }
}