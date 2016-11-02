package com.example.iem.apptub.database;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexislp on 07/01/16.
 */
public class LocalDataBase implements ILocalDataBase {

    private static final String TAG = LocalDataBase.class.getSimpleName();

    private SQLiteDatabase mSQLiteDatabase;
    private SharedPreferences mPreferences;

    public LocalDataBase(SQLiteDatabase SQLiteDatabase, SharedPreferences preferences) {
        mSQLiteDatabase = SQLiteDatabase;
        mPreferences = preferences;
    }

//    //region Request
//    @Override
//    public void requestChild(RequestCallback callback) {
//        Cursor cursor = mSQLiteDatabase.query(ChildTable.TABLE_NAME, null, null, null, null, null, null, null);
//        List<Child> childList = new ArrayList<>();
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                ChildTable childTable = new ChildTable();
//                do {
//                    childList.add(childTable.fromCursor(cursor));
//                } while (cursor.moveToNext());
//            }
//        }
//        cursor.close();
//        if (callback != null)
//            callback.onResult(childList);
//    }
//
//    @Override
//    public void requestChildById(String id, RequestCallback callback) {
//        if(id != "" && id != null) {
//            Cursor cursor = mSQLiteDatabase.query(ChildTable.TABLE_NAME, null, ChildTable.KEY_ID + "=?", new String[]{id}, null, null, null, null);
//            List<Child> childList = new ArrayList<>();
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    Child child = new ChildTable().fromCursor(cursor);
//                    childList.add(child);
//                    cursor.close();
//                    return;
//                }
//            }
//            cursor.close();
//            if (callback != null)
//                callback.onResult(childList);
//        }
//        if (callback != null) {
//            callback.onError(new IllegalArgumentException("id is null"));
//        }
//    }
//
//    @Override
//    public void requestChildByName(String name, RequestCallback callback) {
//        if(name != "" && name != null) {
//            Cursor cursor = mSQLiteDatabase.query(ChildTable.TABLE_NAME, null, ChildTable.KEY_NAME + "=?", new String[]{name}, null, null, null, null);
//            List<Child> childList = new ArrayList<>();
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    Child child = new ChildTable().fromCursor(cursor);
//                    childList.add(child);
//                    cursor.close();
//                    return;
//                }
//            }
//            cursor.close();
//            if (callback != null)
//                callback.onResult(childList);
//        }
//        if (callback != null) {
//            callback.onError(new IllegalArgumentException("name is null"));
//        }
//    }
//
//    @Override
//    public void requestPicture(RequestCallback callback) {
//        Cursor cursor = mSQLiteDatabase.query(PictureTable.TABLE_NAME, null, null, null, null, null, null, null);
//        List<Picture> pictureList = new ArrayList<>();
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                PictureTable pictureTable = new PictureTable();
//                do {
//                    pictureList.add(pictureTable.fromCursor(cursor));
//                } while (cursor.moveToNext());
//            }
//        }
//        cursor.close();
//        if (callback != null)
//            callback.onResult(pictureList);
//    }
//
//    @Override
//    public void requestPictureById(String id, RequestCallback callback) {
//        if(id != "" && id != null) {
//            Cursor cursor = mSQLiteDatabase.query(PictureTable.TABLE_NAME, null, PictureTable.KEY_ID + "=?", new String[]{id}, null, null, null, null);
//            List<Picture> pictureList = new ArrayList<>();
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    Picture picture = new PictureTable().fromCursor(cursor);
//                    pictureList.add(picture);
//                    cursor.close();
//                    return;
//                }
//            }
//            cursor.close();
//            if (callback != null)
//                callback.onResult(pictureList);
//        }
//        if (callback != null) {
//            callback.onError(new IllegalArgumentException("id is null"));
//        }
//    }
//
//    @Override
//    public void requestPictureByName(String name, RequestCallback callback) {
//        if(name != "" && name != null) {
//            Cursor cursor = mSQLiteDatabase.query(PictureTable.TABLE_NAME, null, PictureTable.KEY_NAME + "=?", new String[]{name}, null, null, null, null);
//            List<Picture> pictureList = new ArrayList<>();
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    Picture picture = new PictureTable().fromCursor(cursor);
//                    pictureList.add(picture);
//                    cursor.close();
//                    return;
//                }
//            }
//            cursor.close();
//            if (callback != null)
//                callback.onResult(pictureList);
//        }
//        if (callback != null) {
//            callback.onError(new IllegalArgumentException("name is null"));
//        }
//    }
//
//    @Override
//    public void requestPictureByFolder(String folderId, RequestCallback callback) {
//        if(folderId != "" && folderId != null) {
//            Cursor cursor = mSQLiteDatabase.query(PictureTable.TABLE_NAME, null, PictureTable.KEY_FOLDER_ID + "=?", new String[]{folderId}, null, null, null, null);
//            List<Picture> pictureList = new ArrayList<>();
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    Picture picture = new PictureTable().fromCursor(cursor);
//                    pictureList.add(picture);
//                    cursor.close();
//                    return;
//                }
//            }
//            cursor.close();
//            if (callback != null)
//                callback.onResult(pictureList);
//        }
//        if (callback != null) {
//            callback.onError(new IllegalArgumentException("folder is null"));
//        }
//    }
//
//    @Override
//    public void requestFolder(RequestCallback callback) {
//        Cursor cursor = mSQLiteDatabase.query(FolderTable.TABLE_NAME, null, null, null, null, null, null, null);
//        List<Folder> FolderList = new ArrayList<>();
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                Folder folder = new FolderTable().fromCursor(cursor);
//                FolderList.add(folder);
//                cursor.close();
//                return;
//            }
//        }
//        cursor.close();
//        if (callback != null)
//            callback.onResult(FolderList);
//    }
//
//    @Override
//    public void requestFolderById(String id, RequestCallback callback) {
//        if(id != "" && id != null) {
//            Cursor cursor = mSQLiteDatabase.query(FolderTable.TABLE_NAME, null, FolderTable.KEY_ID + "=?", new String[]{id}, null, null, null, null);
//            List<Folder> folderList = new ArrayList<>();
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    Folder folder = new FolderTable().fromCursor(cursor);
//                    folderList.add(folder);
//                    cursor.close();
//                    return;
//                }
//            }
//            cursor.close();
//            if (callback != null)
//                callback.onResult(folderList);
//        }
//        if (callback != null) {
//            callback.onError(new IllegalArgumentException("id is null"));
//        }
//    }
//
//    @Override
//    public void requestFolderByName(String name, RequestCallback callback) {
//        if(name != "" && name != null) {
//            Cursor cursor = mSQLiteDatabase.query(FolderTable.TABLE_NAME, null, FolderTable.KEY_NAME + "=?", new String[]{name}, null, null, null, null);
//            List<Folder> folderList = new ArrayList<>();
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    Folder folder = new FolderTable().fromCursor(cursor);
//                    folderList.add(folder);
//                    cursor.close();
//                    return;
//                }
//            }
//            cursor.close();
//            if (callback != null)
//                callback.onResult(folderList);
//        }
//        if (callback != null) {
//            callback.onError(new IllegalArgumentException("name is null"));
//        }
//    }
//
//    @Override
//    public void requestFolderByFolder(String folderId, RequestCallback callback) {
//        if(folderId != "" && folderId != null) {
//            Cursor cursor = mSQLiteDatabase.query(FolderTable.TABLE_NAME, null, FolderTable.KEY_ID + "=?", new String[]{folderId}, null, null, null, null);
//            List<Folder> folderList = new ArrayList<>();
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    Folder folder = new FolderTable().fromCursor(cursor);
//                    folderList.add(folder);
//                    cursor.close();
//                    return;
//                }
//            }
//            cursor.close();
//            if (callback != null)
//                callback.onResult(folderList);
//        }
//        if (callback != null) {
//            callback.onError(new IllegalArgumentException("name is null"));
//        }
//    }
//
//    @Override
//    public void requestPage(RequestCallback callback) {
//        Cursor cursor = mSQLiteDatabase.query(PageTable.TABLE_NAME, null, null, null, null, null, null, null);
//        List<Page> pageList = new ArrayList<>();
//        if (cursor != null) {
//            if (cursor.moveToFirst()) {
//                PageTable pageTable = new PageTable();
//                do {
//                    pageList.add(pageTable.fromCursor(cursor));
//                } while (cursor.moveToNext());
//            }
//        }
//        cursor.close();
//        if (callback != null)
//            callback.onResult(pageList);
//    }
//
//    @Override
//    public void requestPageById(String id, RequestCallback callback) {
//        if(id != "" && id != null) {
//            Cursor cursor = mSQLiteDatabase.query(PageTable.TABLE_NAME, null, PageTable.KEY_ID + "=?", new String[]{id}, null, null, null, null);
//            List<Page> pageList = new ArrayList<>();
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    PageTable pageTable = new PageTable();
//                    do {
//                        pageList.add(pageTable.fromCursor(cursor));
//                    } while (cursor.moveToNext());
//                }
//            }
//            cursor.close();
//            if (callback != null)
//                callback.onResult(pageList);
//        }
//        if (callback != null) {
//            callback.onError(new IllegalArgumentException("id is null"));
//        }
//    }
//
//    @Override
//    public void requestPageByName(String name, RequestCallback callback) {
//        if(name != "" && name != null) {
//            Cursor cursor = mSQLiteDatabase.query(PageTable.TABLE_NAME, null, PageTable.KEY_NAME + "=?", new String[]{name}, null, null, null, null);
//            List<Page> pageList = new ArrayList<>();
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    PageTable pageTable = new PageTable();
//                    do {
//                        pageList.add(pageTable.fromCursor(cursor));
//                    } while (cursor.moveToNext());
//                }
//            }
//            cursor.close();
//            if (callback != null)
//                callback.onResult(pageList);
//        }
//        if (callback != null) {
//            callback.onError(new IllegalArgumentException("name is null"));
//        }
//    }
//
//    @Override
//    public void requestPageByChild(String childId, RequestCallback callback) {
//        if(childId != "" && childId != null) {
//            Cursor cursor = mSQLiteDatabase.query(PageTable.TABLE_NAME, null, PageTable.KEY_CHILD_ID + "=?", new String[]{childId}, null, null, null, null);
//            List<Page> pageList = new ArrayList<>();
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    PageTable pageTable = new PageTable();
//                    do {
//                        pageList.add(pageTable.fromCursor(cursor));
//                    } while (cursor.moveToNext());
//                }
//            }
//            cursor.close();
//            if (callback != null)
//                callback.onResult(pageList);
//        }
//        if (callback != null) {
//            callback.onError(new IllegalArgumentException("childId is null"));
//        }
//    }
//
//    @Override
//    public void requestPictureFromPage(String idPage, RequestCallback callback) {
//        if (idPage != "" && !idPage.isEmpty()){
//            Cursor cursor = mSQLiteDatabase.query(PictureTable.TABLE_NAME,null, PictureTable.KEY_PAGE_ID + "=?", new String[]{idPage} ,null, null, null, null);
//            List<Picture> pagePictures = new ArrayList<>();
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    PictureTable pagePictureTable = new PictureTable();
//                    do {
//                        pagePictures.add(pagePictureTable.fromCursor(cursor));
//                    } while (cursor.moveToNext());
//                }
//            }
//            cursor.close();
//            if (callback != null)
//                callback.onResult(pagePictures);
//        }
//        if (callback != null) {
//            callback.onError(new IllegalArgumentException("Page id is null"));
//        }
//    }
//
//    //endregion
//
//    //region INSERT
//
//    @Override
//    public void insertChild(Child child, RequestCallback callback) {
//        long valueReturn = mSQLiteDatabase.insert(ChildTable.TABLE_NAME, null, new ChildTable().getContentValues(child));
//        if (valueReturn !=  -1){
//            //TODO
//            //callback.onResult();
//        }
//        else {
//            callback.onError(new Error("Creation child failed"));
//        }
//        return;
//    }
//
//    @Override
//    public long insertPicture(Picture picture, RequestCallback callback) {
//        long valueReturn = mSQLiteDatabase.insert(PictureTable.TABLE_NAME, null, new PictureTable().getContentValues(picture));
//        if (valueReturn != -1){
//            //TODO
//            //calback.onResult();
//        }
//        else {
//            callback.onError(new Error("Creation picture failed"));
//        }
//        return valueReturn;
//    }
//
//    @Override
//    public void insertFolder(Folder folder, RequestCallback callback) {
//        long valueReturn = mSQLiteDatabase.insert(FolderTable.TABLE_NAME, null, new FolderTable().getContentValues(folder));
//        if (valueReturn != -1){
//            //TODO
//            //callback.onResult();
//        }
//        else {
//            callback.onError(new Error("Creation folder failed"));
//        }
//    }
//
//    @Override
//    public long insertPage(Page page, RequestCallback callback) {
//        long valueReturn = mSQLiteDatabase.insert(PageTable.TABLE_NAME, null, new PageTable().getContentValues(page));
//        if (valueReturn != -1){
//            //TODO
//            //callback.onResult();
//        }
//        else {
//            callback.onError(new Error("Creation page failed"));
//        }
//        return valueReturn;
//    }
//
//    @Override
//    public void insertPictureInPage(Picture pp, RequestCallback callback) {
//        long valueReturn = mSQLiteDatabase.insert(PictureTable.TABLE_NAME,null, new PictureTable().getContentValues(pp));
//        if(valueReturn == -1)
//            Log.d("FUCK", "FUCK");
//    }
////endregion
//
//    //region DELETE
//
//    @Override
//    public void deleteChildById(String id, RequestCallback callback) {
//        int valueReturn = mSQLiteDatabase.delete(ChildTable.TABLE_NAME, ChildTable.KEY_ID + "=?", new String[]{id});
//        if (valueReturn != 0){
//            //TODO
//            //callback.onResult();
//        }
//        else {
//            callback.onError(new Error("Child not deleted, fail"));
//        }
//    }
//
//    @Override
//    public void deletePictureById(String id, RequestCallback callback) {
//        int valueReturn = mSQLiteDatabase.delete(PictureTable.TABLE_NAME, PictureTable.KEY_ID + "=?", new String[]{id});
//        if (valueReturn != 0){
//            //TODO
//            //callback.onResult();
//        }
//        else {
//            callback.onError(new Error("Picture not deleted, fail"));
//        }
//    }
//
//    @Override
//    public void deleteFolderById(String id, RequestCallback callback) {
//        int valueReturn = mSQLiteDatabase.delete(FolderTable.TABLE_NAME, FolderTable.KEY_ID + "=?", new String[]{id});
//        if (valueReturn != 0){
//            //TODO
//            //callback.onResult();
//        }
//        else {
//            callback.onError(new Error("Folder not deleted, fail"));
//        }
//    }
//
//    @Override
//    public void deletePageById(String id, RequestCallback callback) {
//        int valueReturn = mSQLiteDatabase.delete(PageTable.TABLE_NAME, PageTable.KEY_ID + "=?", new String[]{id});
//        if (valueReturn != 0){
//            //TODO
//            //callback.onResult();
//        }
//        else {
//            callback.onError(new Error("Page not deleted, fail"));
//        }
//    }
//
//    @Override
//    public void deletePageByChild(String idChild, RequestCallback callback) {
//        int valueReturn = mSQLiteDatabase.delete(PageTable.TABLE_NAME, PageTable.KEY_CHILD_ID + "=?", new String[]{idChild});
//        if(valueReturn != 0){
//            //TODO
//            //callback.onResult();
//        }
//        else {
//            //child without page
//            //callback.onError(new Error("Pages of child not remove"));
//        }
//    }
//
//    @Override
//    public void deletePictureInPage(String id, RequestCallback callback) {
//        //TODO
//    }
//
//    //endregion
}
