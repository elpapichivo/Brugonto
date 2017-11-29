package com.example.yanina.mysong.Controller;

import android.content.Context;

import com.example.yanina.mysong.Dao.DaoArtistas;
import com.example.yanina.mysong.Dao.DaoCancion;
import com.example.yanina.mysong.Model.Cancion;
import com.example.yanina.mysong.Utils.ResultListener;

import java.util.List;

/**
 * Created by ma on 16/11/17.
 */

public class ControllerCancion {
    public void obtenerCancionOffline(final Context context, final ResultListener<List<Cancion>> listResultListener){
        DaoCancion daoCancion=new DaoCancion(context);
        List<Cancion> canciones = daoCancion.buscarCanciones();
        listResultListener.finish(canciones);
    }


    public void obtenerCancion(final Context context, final ResultListener<List<Cancion>> listResultListener){
        ResultListener<List<Cancion>>listaDelController=new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                DaoCancion daoCancion = new DaoCancion(context);
                daoCancion.agregarCanciones(context, resultado);
                listResultListener.finish(resultado);
            }
        };

        DaoCancion daoCancion=new DaoCancion(context);
        daoCancion.obtenerCancion(listaDelController);
    }


    public void obtenerCancionPorAlbum(final Context context, final ResultListener<List<Cancion>> listResultListener, Integer idAlbum){
        ResultListener<List<Cancion>>listaDelController=new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                DaoCancion daoCancion = new DaoCancion(context);
                daoCancion.agregarCanciones(context, resultado);
                listResultListener.finish(resultado);
            }
        };


        DaoCancion daoCancion=new DaoCancion(context);
        daoCancion.obtenerCancionPorAlbum(listaDelController, idAlbum);
    }


}
