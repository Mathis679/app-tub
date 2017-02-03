package com.example.iem.apptub.database.Table;

/**
 * Created by iem on 29/11/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.iem.apptub.classes.Arret;


public class ArretManager {

    private static final String TABLE_NAME = "arret";
    public static final String KEY_ID_ARRET="id_arret";
    public static final String KEY_NOM_ARRET="nom_arret";
    public static final String CREATE_TABLE_ARRET = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_ARRET+" INTEGER primary key," +
            " "+KEY_NOM_ARRET+" TEXT" +
            ");";

    private MySQLite maBaseSQLite;
    private SQLiteDatabase db;

    public ArretManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }


    public long addArret(Arret arret) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_ARRET, arret.getNom());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);

    }

    public int modArret(Arret arret) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_ARRET, arret.getNom());

        String where = KEY_ID_ARRET+" = ?";
        String[] whereArgs = {arret.getId()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supAnimal(Arret arret) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_ARRET+" = ?";
        String[] whereArgs = {arret.getId()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Arret getArret(int id) {
        // Retourne l'animal dont l'id est passé en paramètre

        Arret a=new Arret(0," ");

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_ARRET+"="+id, null);
        if (c.moveToFirst()) {
            a.setId(c.getInt(c.getColumnIndex(KEY_ID_ARRET)));
            a.setNom(c.getString(c.getColumnIndex(KEY_NOM_ARRET)));
            c.close();
        }

        return a;
    }

    public Cursor getArret() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }


}
