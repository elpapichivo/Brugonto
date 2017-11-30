package com.example.yanina.mysong.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.yanina.mysong.Model.Album;
import com.example.yanina.mysong.Model.ContenedorAlbum;
import com.example.yanina.mysong.Utils.DAOException;
import com.example.yanina.mysong.Utils.DeezerHelper;
import com.example.yanina.mysong.Utils.HTTPConnectionManager;
import com.example.yanina.mysong.Utils.ResultListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elpapichivo on 20/11/2017.
 */

public class DaoAlbum extends DataBaseHelper {
    public static final String TABLE_NAME="Albumes";
    public static final String COLUMNA_FOTO="foto";
    public static final String COLUMNA_NOMBRE="nombreAlbum";
    public static final String COLUMNA_ID="id";
    public static final String COLUMNA_TRACKLIST="tracks";

    public DaoAlbum(Context context) {
        super(context);
    }

    public void agregarAlbum (Album album){
        //Crear una conexion a la base de datos.
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMNA_FOTO, album.getFoto());
        contentValues.put(COLUMNA_NOMBRE, album.getNombreAlbum());
        contentValues.put(COLUMNA_ID, album.getId());
        contentValues.put(COLUMNA_TRACKLIST, album.getTracks());

        database.insert(TABLE_NAME, null, contentValues);

        database.close();
    }

    public void agregarAlbums (List<Album> albumList){
        for (Album album : albumList){
            agregarAlbum(album);
        }
    }

    public List<Album> buscarAlbums(){
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        List<Album> albumList = new ArrayList<>();

        while (cursor.moveToNext()){

            String nombreAlbum = cursor.getString(cursor.getColumnIndex(COLUMNA_NOMBRE));
            String foto = cursor.getString(cursor.getColumnIndex(COLUMNA_FOTO));
            String tracklist = cursor.getString(cursor.getColumnIndex(COLUMNA_TRACKLIST));
            Integer id = cursor.getInt(cursor.getColumnIndex(COLUMNA_ID));

            Album album = new Album(foto, nombreAlbum, id, tracklist);

            albumList.add(album);
        }

        cursor.close();
        database.close();

        return albumList;
    }


    public void obtenerAlbum(ResultListener<List<Album>> listaDelController,Integer idArtista){
        TareaAsincronaAlbum tareaAsincronaAlbum=new TareaAsincronaAlbum(listaDelController);
        tareaAsincronaAlbum.execute(DeezerHelper.albumesPorArtista(idArtista));
    }


    public class TareaAsincronaAlbum extends AsyncTask<String, Void,List<Album>> {
        private ResultListener<List<Album>> listenerDelController;

        public TareaAsincronaAlbum(ResultListener<List<Album>> listenerDelController) {
            this.listenerDelController = listenerDelController;
        }

        @Override
        protected List<Album> doInBackground(String... params) {

            List<Album> albumList;
            String url = params[0];

            HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
            try {
                // Establezco la conexión a Internet y obtengo en json
                String json = httpConnectionManager.getRequestString(url);

                // Creo una instancia de GSON
                Gson gson = new Gson();
                // Le pido a GSON que mapee el JSON
                ContenedorAlbum contenedorNoticia = gson.fromJson(json, ContenedorAlbum.class);
                // Obtengo la lista de noticias del contenedor noticias
                albumList = contenedorNoticia.getListaDeAlbum();
            } catch (DAOException e) {
                // En caso de que algo salga mal, devuelvo una lista vacia
                albumList = new ArrayList<>();
                e.printStackTrace();
            }
            return albumList;
        }

        @Override
        protected void onPostExecute(List<Album> alba) {
            super.onPostExecute(alba);
            listenerDelController.finish(alba);
        }
    }
    }
