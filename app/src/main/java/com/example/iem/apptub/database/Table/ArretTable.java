package com.example.iem.apptub.database.Table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iem.apptub.classes.Arret;
import com.example.iem.apptub.classes.Folder;


/**
 * Created by alexislp on 06/01/16.
 */
public class ArretTable extends AbstractTable<Arret> {

    /**
     * The name of the table
     */
    public static final String TABLE_NAME = "arret";

    /**
     * Represents the unique Id of a {@link Arret}
     */
    public static final String KEY_ID = "id";

    /**
     * Represents the name of a {@link Arret}
     */
    public static final String KEY_NAME = "name";


    private SQLiteDatabase db;






    /**
     * Represents the unique Id of a {@link Folder}
     */
    public static final String KEY_FOLDER = "id_folder";


    /**
     * The creation SQLite command of {@link Arret}
     */
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            KEY_ID + " integer primary key autoincrement," +
            KEY_NAME + TYPE_TEXT + ", " + TYPE_TEXT + ", " +
            TYPE_BOOLEAN +  " DEFAULT 0 , " +
            KEY_FOLDER + TYPE_SMALLTEXT + ")";

//    @Override
//    public ContentValues getContentValues(Child object) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(KEY_ID, object.getId());
//        contentValues.put(KEY_NAME, object.getName());
//        contentValues.put(KEY_PICTURE_PATH, object.getPicturePath());
//        contentValues.put(KEY_IS_FAVORITE, object.isFavorite());
//        contentValues.put(KEY_FOLDER, object.getFolderId());
//        return contentValues;
//    }

    @Override
    public ContentValues getContentValues(Arret object) {
        return null;
    }

    @Override
    public Arret fromContentValues(ContentValues contentValues) {
        return null;
    }

    @Override
    public Arret fromCursor(Cursor cursor) {
        return null;
    }

//    @Override
//    public Child fromContentValues(ContentValues contentValues) {
//        return null;
//    }
//
//    @Override
//    public Child fromCursor(Cursor cursor) {
//        Child child = new Child();
//        child.setId(cursor.getString(cursor.getColumnIndex(ArretTable.KEY_ID)));
//        child.setName(cursor.getString(cursor.getColumnIndex(ArretTable.KEY_NAME)));
//        child.setPicturePath(cursor.getString(cursor.getColumnIndex(ArretTable.KEY_PICTURE_PATH)));
//        child.setIsFavorite(cursor.getInt(cursor.getColumnIndex(ArretTable.KEY_IS_FAVORITE)));
//        child.setFolderId(cursor.getString(cursor.getColumnIndex(ArretTable.KEY_FOLDER)));
//        return child;
//    }


    public ArretTable(Context context)
    {

    }
}
