package com.example.iem.apptub.database.Table;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.iem.apptub.classes.Folder;
/**
 * Created by alexislp on 06/01/16.
 */
public class FolderTable extends AbstractTable<Folder> {

    /**
     * The name of the table
     */
    public static final String TABLE_NAME = "folder";

    /**
     * Represents the unique Id of a {@link Folder}
     */
    public static final String KEY_ID = "id";

    /**
     * Represents the name of a {@link Folder}
     */
    public static final String KEY_NAME = "name";

    /**
     * To know if the folder was a favorite
     */
    public static final String KEY_IS_FAVORITE = "is_favorite";


    /**
     * The creation SQLite command of {@link Folder}
     */
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            KEY_ID + TYPE_SMALLTEXT + PRIMARY + ", " +
            KEY_NAME + TYPE_TEXT + ", " +
            KEY_IS_FAVORITE + TYPE_BOOLEAN +")";

    @Override
    public ContentValues getContentValues(Folder object) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, object.getId());
        contentValues.put(KEY_NAME, object.getName());
        contentValues.put(KEY_IS_FAVORITE, object.isFavorite());
        return contentValues;
    }

    @Override
    public Folder fromContentValues(ContentValues contentValues) {
        return null;
    }

    @Override
    public Folder fromCursor(Cursor cursor) {
        Folder folder = new Folder();
        folder.setId(cursor.getString(cursor.getColumnIndex(FolderTable.KEY_ID)));
        folder.setName(cursor.getString(cursor.getColumnIndex(FolderTable.KEY_NAME)));
        folder.setIsFavorite(cursor.getInt(cursor.getColumnIndex(FolderTable.KEY_IS_FAVORITE)));
        return folder;
    }
}
