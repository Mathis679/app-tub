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
    float latitude;

    @Column
    float longitude;

    @Column
    String nom;

    @Column
    String adresse;

    @Column
    String ligne;

    @Column
    int idLine;



    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(float latitude) {

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


}