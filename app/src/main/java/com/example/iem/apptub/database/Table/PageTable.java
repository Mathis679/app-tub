package com.example.iem.apptub.database.Table;

import android.content.ContentValues;
import android.database.Cursor;


/**
 * Created by alexislp on 06/01/16.
 */
public class PageTable  {

//    /**
//     * The name of the table
//     */
//    public static final String TABLE_NAME = "page";
//
//    /**
//     * Represents the unique Id of a {@link Page}
//     */
//    public static final String KEY_ID = "id";
//
//    /**
//     * Represents the name of a {@link Page}
//     */
//    public static final String KEY_NAME = "name";
//
//    /**
//     * To know if the page was a favorite
//     */
//    public static final String KEY_IS_FAVORITE = "is_favorite";
//
//    /**
//     * Id of child owner of this page
//     */
//    public static final String KEY_CHILD_ID = "child_id";
//
//    /**
//     * The creation SQLite command of {@link Page}
//     */
//    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
//            KEY_ID + " integer primary key autoincrement," +
//            KEY_NAME + TYPE_TEXT + ", " +
//            KEY_IS_FAVORITE + TYPE_BOOLEAN + ", " +
//            KEY_CHILD_ID + TYPE_SMALLTEXT + ")";
//
//    @Override
//    public ContentValues getContentValues(Page object) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(KEY_ID, object.getId());
//        contentValues.put(KEY_NAME, object.getName());
//        contentValues.put(KEY_IS_FAVORITE, object.isFavorite());
//        contentValues.put(KEY_CHILD_ID, object.getChildId());
//        return contentValues;
//    }
//
//    @Override
//    public Page fromContentValues(ContentValues contentValues) {
//        return null;
//    }
//
//    @Override
//    public Page fromCursor(Cursor cursor) {
//        Page page = new Page();
//        page.setId(cursor.getString(cursor.getColumnIndex(PageTable.KEY_ID)));
//        page.setName(cursor.getString(cursor.getColumnIndex(PageTable.KEY_NAME)));
//        page.setIsFavorite(cursor.getInt(cursor.getColumnIndex(PageTable.KEY_IS_FAVORITE)));
//        page.setChildId(cursor.getString(cursor.getColumnIndex(PageTable.KEY_CHILD_ID)));
//        return page;
//    }
}
