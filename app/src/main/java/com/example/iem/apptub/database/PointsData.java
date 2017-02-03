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

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(float latitude) {

        this.latitude = latitude;
    }
}