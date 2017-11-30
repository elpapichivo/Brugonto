package com.example.yanina.mysong.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.Model.Cancion;
import com.example.yanina.mysong.Model.ContenedorArtista;
import com.example.yanina.mysong.R;
import com.example.yanina.mysong.Utils.DAOException;
import com.example.yanina.mysong.Utils.DeezerHelper;
import com.example.yanina.mysong.Utils.HTTPConnectionManager;
import com.example.yanina.mysong.Utils.ResultListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 08/11/17.
 */

public class DaoArtistas extends DataBaseHelper {
        public static final String TABLE_NAME="Artistas";
        public static final String COLUMNA_FOTO="foto";
        public static final String COLUMNA_ID="id";
        public static final String COLUMNA_POSITION="position";
        public static final String COLUMNA_NOMBRE="nombreArtista";

    public DaoArtistas(Context context) {
        super(context);
    }


    public void obtenerArtista(ResultListener<List<Artista>> listaDelController){
        TareaAsincrona tareaAsincrona=new TareaAsincrona(listaDelController);
        tareaAsincrona.execute(DeezerHelper.chartArtistsPorGenero(0));
    }

    public void obtenerArtistaPorId(ResultListener<List<Artista>> listaDelController, Integer idArtista){
        TareaAsincrona tareaAsincrona=new TareaAsincrona(listaDelController);
        tareaAsincrona.execute(DeezerHelper.artistPorId(idArtista));
    }

    public class TareaAsincrona extends AsyncTask <String, Void,List<Artista>>{
        private ResultListener<List<Artista>> listenerDelController;

        public TareaAsincrona(ResultListener <List<Artista>>listenerDelController){
            this.listenerDelController=listenerDelController;
        }

        @Override
        protected List<Artista> doInBackground(String... params) {
            List<Artista> noticiaList;
            String url = params[0];

            HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
            try {
                // Establezco la conexión a Internet y obtengo en json
                String json = httpConnectionManager.getRequestString(url);

                // Creo una instancia de GSON
                Gson gson = new Gson();
                // Le pido a GSON que mapee el JSON
                ContenedorArtista contenedorNoticia = gson.fromJson(json, ContenedorArtista.class);
                // Obtengo la lista de noticias del contenedor noticias
                noticiaList = contenedorNoticia.getListaDeArtista();
            } catch (DAOException e) {
                // En caso de que algo salga mal, devuelvo una lista vacia
                noticiaList = new ArrayList<>();
                e.printStackTrace();
            }

            return noticiaList;

        }

        @Override
        protected void onPostExecute(List<Artista> artistas) {
            super.onPostExecute(artistas);
            listenerDelController.finish(artistas);
        }
    }


    public void agregarArtista(Artista artista) {
        //Crear una conexion a la base de datos.

        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValuesArtista = new ContentValues();
        contentValuesArtista.put(COLUMNA_FOTO, artista.getFoto());
        contentValuesArtista.put(COLUMNA_ID, artista.getId());
        contentValuesArtista.put(COLUMNA_POSITION, artista.getPosition());
        contentValuesArtista.put(COLUMNA_NOMBRE, artista.getNombreArtista());

        database.insert(TABLE_NAME, null, contentValuesArtista);


        database.close();
    }

    public void agregarArtistas (List<Artista> artistaList){
        for (Artista artista : artistaList){
            agregarArtista(artista);
        }
    }

    public List<Artista> buscarArtistas(){
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        List<Artista> artistaList = new ArrayList<>();

        while (cursor.moveToNext()){

            String foto = cursor.getString(cursor.getColumnIndex(COLUMNA_FOTO));
            Integer id = cursor.getInt(cursor.getColumnIndex(COLUMNA_ID));
            Integer position = cursor.getInt(cursor.getColumnIndex(COLUMNA_POSITION));
            String nombre = cursor.getString(cursor.getColumnIndex(COLUMNA_NOMBRE));

            Artista artista = new Artista(id, nombre, position, foto);

            artistaList.add(artista);
        }
        cursor.close();
        database.close();

        return artistaList;
    }

}
