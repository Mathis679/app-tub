package com.example.iem.apptub.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by iem on 03/02/2017.
 */

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)

public class MyDatabase {
    public static final String NAME = "MyDataBase";

    public static final int VERSION = 1;
}