package com.example.yanina.mysong.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.Model.Cancion;
import com.example.yanina.mysong.Model.ContenedorCancion;
import com.example.yanina.mysong.Utils.DAOException;
import com.example.yanina.mysong.Utils.DeezerHelper;
import com.example.yanina.mysong.Utils.HTTPConnectionManager;
import com.example.yanina.mysong.Utils.ResultListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 16/11/17.
 */

public class DaoCancion extends  DataBaseHelper {
    public static final String COLUMNA_TITULO="titulo";
    public static final String COLUMNA_PREVIEW="preview";
    public static final String COLUMNA_ARTISTA="artista";
    public static final String TABLE_NAME="Canciones";
    public static final String COLUMNA_ID="id";
    public static final String COLUMNA_FAV="fav";
    public static final String COLUMNA_ID_ALBUM ="idalbum" ;

    private Context context;

    public DaoCancion(Context context) {
        super(context);
        this.context = context;
    }


    public void obtenerCancion(ResultListener<List<Cancion>>listaDelController){
        TareaAsincronaCancion tareaAsincronaCancion=new TareaAsincronaCancion(listaDelController);
        tareaAsincronaCancion.execute(DeezerHelper.chartTracksPorGenero(0));

    }



    public void obtenerCancionPorAlbum(ResultListener<List<Cancion>>listaDelController, Integer idAlbum) {
        TareaAsincronaCancion tareaAsincronaCancion = new TareaAsincronaCancion(listaDelController);
        tareaAsincronaCancion.execute(DeezerHelper.cancionPorAlbum(idAlbum));
    }

        public class TareaAsincronaCancion extends AsyncTask <String, Void, List<Cancion>>{
        private ResultListener<List<Cancion>> listenerDelController;

        public TareaAsincronaCancion(ResultListener<List<Cancion>> listenerDelController) {
            this.listenerDelController = listenerDelController;
        }

        @Override
        protected List<Cancion> doInBackground(String... params) {
            List<Cancion> cancionList;
            String url=params[0];
            HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
            try {
                // Establezco la conexión a Internet y obtengo en json
                String json = httpConnectionManager.getRequestString(url);

                // Creo una instancia de GSON
                Gson gson = new Gson();
                // Le pido a GSON que mapee el JSON
                ContenedorCancion contenedorCancion = gson.fromJson(json, ContenedorCancion.class);
                cancionList = contenedorCancion.getCancionList();
            }catch (DAOException e) {
                // En caso de que algo salga mal, devuelvo una lista vacia
                cancionList = new ArrayList<>();
                e.printStackTrace();
            }



            return cancionList;
        }

        @Override
        protected void onPostExecute(List<Cancion> cancionList) {
            super.onPostExecute(cancionList);
            listenerDelController.finish(cancionList);
        }
    }


    public void agregarCancion(Cancion cancion) {
        //Crear una conexion a la base de datos.

        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNA_TITULO, cancion.getTitle());
        contentValues.put(COLUMNA_ARTISTA, cancion.getArtista().getId());
        contentValues.put(COLUMNA_ID, cancion.getId());
        contentValues.put(COLUMNA_PREVIEW, cancion.getPreview());
        contentValues.put(COLUMNA_ID_ALBUM, cancion.getIdAlbum());
        contentValues.put(COLUMNA_FAV, 0);


        database.insert(TABLE_NAME, null, contentValues);

        database.close();
    }

    public void agregarCanciones(Context context, List<Cancion> list){
        DaoArtistas daoArtistas = new DaoArtistas(context);

        for (Cancion cancion : list){
            daoArtistas.agregarArtista(cancion.getArtista());
            agregarCancion(cancion);
        }
    }

    public List<Cancion> buscarCanciones(){
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + ", " + DaoArtistas.TABLE_NAME +
                        " WHERE " + TABLE_NAME + "." + COLUMNA_ARTISTA + " = " + DaoArtistas.TABLE_NAME + "." +  DaoArtistas.COLUMNA_ID, null);


        List<Cancion> noticiaList = new ArrayList<>();

        while(cursor.moveToNext()){

            String fotoArtista = cursor.getString(cursor.getColumnIndex(DaoArtistas.COLUMNA_FOTO));
            String nombreArtista = cursor.getString(cursor.getColumnIndex(DaoArtistas.COLUMNA_NOMBRE));
            Integer idArtista = cursor.getInt(cursor.getColumnIndex(DaoArtistas.COLUMNA_ID));

            Artista artista = new Artista(idArtista, nombreArtista, 0, fotoArtista);


            String id = cursor.getString(cursor.getColumnIndex(COLUMNA_ID));
            String preview = cursor.getString(cursor.getColumnIndex(COLUMNA_PREVIEW));
            String tituloCancion = cursor.getString(cursor.getColumnIndex(COLUMNA_TITULO));


            Cancion cancion = new Cancion(tituloCancion, artista, preview, id);
            noticiaList.add(cancion);


           

        }


        cursor.close();
        database.close();

        return noticiaList;
    }

    public List<Cancion> buscarFavoritos(){
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + ", " + DaoArtistas.TABLE_NAME +
                " WHERE " + TABLE_NAME + "." + COLUMNA_ARTISTA + " = " + DaoArtistas.TABLE_NAME + "." +  DaoArtistas.COLUMNA_ID +
                " AND " + COLUMNA_FAV + " = " + 1, null);

        List<Cancion> noticiaList = new ArrayList<>();

        while(cursor.moveToNext()){

            String fotoArtista = cursor.getString(cursor.getColumnIndex(DaoArtistas.COLUMNA_FOTO));
            String nombreArtista = cursor.getString(cursor.getColumnIndex(DaoArtistas.COLUMNA_NOMBRE));
            Integer position = cursor.getInt(cursor.getColumnIndex(DaoArtistas.COLUMNA_POSITION));
            Integer idArtista = cursor.getInt(cursor.getColumnIndex(DaoArtistas.COLUMNA_ID));

            Artista artista = new Artista(idArtista, nombreArtista, position, fotoArtista);


            String id = cursor.getString(cursor.getColumnIndex(COLUMNA_ID));
            String preview = cursor.getString(cursor.getColumnIndex(COLUMNA_PREVIEW));
            String tituloCancion = cursor.getString(cursor.getColumnIndex(COLUMNA_TITULO));
            Integer idAlbum = cursor.getInt(cursor.getColumnIndex(COLUMNA_ID_ALBUM));


            Cancion cancion = new Cancion(tituloCancion, artista, preview, id);
            cancion.setIdAlbum(idAlbum);
            noticiaList.add(cancion);

            //Toast.makeText(context, cancion.toString(), Toast.LENGTH_LONG).show();


        }


        cursor.close();
        database.close();

        return noticiaList;
    }

    public void setiarFavoritos(String idCancion){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMNA_FAV, 1);

        database.update(TABLE_NAME, contentValues, COLUMNA_ID + " = '" + idCancion +"'" , null );

        database.close();

    }

}
