package com.example.yanina.mysong.Controller;

import android.content.Context;

import com.example.yanina.mysong.Dao.DaoAlbum;
import com.example.yanina.mysong.Dao.DaoArtistas;
import com.example.yanina.mysong.Dao.DaoCancion;
import com.example.yanina.mysong.Model.Cancion;
import com.example.yanina.mysong.Utils.HTTPConnectionManager;
import com.example.yanina.mysong.Utils.ResultListener;

import java.util.List;

/**
 * Created by ma on 16/11/17.
 */

public class ControllerCancion {
    public void obtenerCancionOffline(final Context context, final ResultListener<List<Cancion>> listResultListener) {
        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            ResultListener<List<Cancion>> listaDelController = new ResultListener<List<Cancion>>() {
                @Override
                public void finish(List<Cancion> resultado) {
                    DaoCancion daoCancion = new DaoCancion(context);
                    daoCancion.agregarCanciones(context, resultado);

                    listResultListener.finish(resultado);
                }
            };
            DaoCancion daoCancion = new DaoCancion(context);
            daoCancion.obtenerCancion(listaDelController);
        } else {
            DaoCancion daoCancion = new DaoCancion(context);
            List<Cancion> canciones = daoCancion.buscarCanciones();
            listResultListener.finish(canciones);
        }
    }


    public void obtenerCancion(final Context context, final ResultListener<List<Cancion>> listResultListener) {
        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            ResultListener<List<Cancion>> listaDelController = new ResultListener<List<Cancion>>() {
                @Override
                public void finish(List<Cancion> resultado) {
                    DaoCancion daoCancion = new DaoCancion(context);
                    daoCancion.agregarCanciones(context, resultado);
                    listResultListener.finish(resultado);
                }
            };
            DaoCancion daoCancion = new DaoCancion(context);
            daoCancion.obtenerCancion(listaDelController);
        } else {
            DaoCancion daoCancion = new DaoCancion(context);
            List<Cancion> cancionList = daoCancion.buscarCanciones();
            listResultListener.finish(cancionList);
        }
    }


    public void obtenerCancionPorAlbum(final Context context, final ResultListener<List<Cancion>> listResultListener, final Integer idAlbum) {
        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            ResultListener<List<Cancion>> listaDelController = new ResultListener<List<Cancion>>() {
                @Override
                public void finish(List<Cancion> resultado) {

                    DaoCancion daoCancion = new DaoCancion(context);
                    for (Cancion cancion:resultado) {
                        cancion.setIdAlbum(idAlbum);
                    }
                    daoCancion.agregarCanciones(context, resultado);
                    listResultListener.finish(resultado);
                }
            };
            DaoCancion daoCancion = new DaoCancion(context);
            daoCancion.obtenerCancionPorAlbum(listaDelController, idAlbum);
        } else {
            DaoCancion daoCancion = new DaoCancion(context);
            List<Cancion> cancionList = daoCancion.buscarCanciones();
            listResultListener.finish(cancionList);
        }
    }


    public void setiarLasCancionesFavoritas(Context context, String idCancion) {
        DaoCancion daoCancion = new DaoCancion(context);
        daoCancion.setiarFavoritos(idCancion);
    }

    public List<Cancion> buscarCancionFavoritas(Context context) {
        DaoCancion daoCancion = new DaoCancion(context);
        return daoCancion.buscarFavoritos();

    }

    public String obtenerImagen(Context context,Integer idAlbum){
        DaoAlbum daoALbum = new DaoAlbum(context);
        return daoALbum.obtenerImagenAlbum(idAlbum);
    }
}
