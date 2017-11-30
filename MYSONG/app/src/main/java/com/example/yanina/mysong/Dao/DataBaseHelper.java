package com.example.yanina.mysong.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ma on 23/11/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, "BruGonTo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE "+ DaoCancion.TABLE_NAME +"(" +
                DaoCancion.COLUMNA_ID + " TEXT PRIMARY KEY, " +
                DaoCancion.COLUMNA_ARTISTA + " INTEGER NOT NULL, " +
                DaoCancion.COLUMNA_PREVIEW + " TEXT, " +
                DaoCancion.COLUMNA_TITULO + " TEXT," +
                DaoCancion.COLUMNA_FAV + " INTEGER);";

        String query2 = "CREATE TABLE "+ DaoArtistas.TABLE_NAME +"(" +
                DaoArtistas.COLUMNA_ID + " INTEGER PRIMARY KEY, " +
                DaoArtistas.COLUMNA_POSITION + " INTEGER, " +
                DaoArtistas.COLUMNA_NOMBRE + " TEXT NOT NULL, " +
                DaoArtistas.COLUMNA_FOTO + " TEXT);";

        String query3 = "CREATE TABLE "+ DaoAlbum.TABLE_NAME +"(" +
                DaoAlbum.COLUMNA_ID + " INTEGER PRIMARY KEY, " +
                DaoAlbum.COLUMNA_NOMBRE + " TEXT NOT NULL, " +
                DaoAlbum.COLUMNA_FOTO + " TEXT," +
                DaoAlbum.COLUMNA_TRACKLIST + " TEXT);";
        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
