package com.example.iem.apptub.database.Table;

import android.content.ContentValues;
import android.database.Cursor;


/**
 * Created by alexislp on 06/01/16.
 */
public abstract class AbstractTable<T extends Object> {
    static final String PRIMARY = " PRIMARY KEY";

    static final String TYPE_SMALLTEXT = " VARCHAR(255)";
    static final String TYPE_TEXT = " TEXT";
    static final String TYPE_LONG = " INTEGER";
    static final String TYPE_BOOLEAN = " INTEGER NOT NULL";

    /**
     * Transform an T object in {@link ContentValues} for insertion in DB.
     *
     * @param object The Object to transform.
     * @return The {@link ContentValues}
     */
    public abstract ContentValues getContentValues(T object);

    /**
     * Transform a contentValues in an Object.
     *
     * @param contentValues The {@link ContentValues} to transform
     * @return The T object extract from the {@link ContentValues}
     */
    public abstract T fromContentValues(ContentValues contentValues);

    public abstract T fromCursor(Cursor cursor);
}
