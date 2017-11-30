package com.example.yanina.mysong.Controller;

import android.content.Context;

import com.example.yanina.mysong.Dao.DaoAlbum;
import com.example.yanina.mysong.Dao.DaoArtistas;
import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.Utils.HTTPConnectionManager;
import com.example.yanina.mysong.Utils.ResultListener;

import java.util.List;

/**
 * Created by ma on 08/11/17.
 */

public class ControllerArtista {
    public void obtenerArtista(final Context context, final ResultListener<List<Artista>> listResultListener){
        if (HTTPConnectionManager.isNetworkingOnline(context)){
            ResultListener <List<Artista>>listaDelController = new ResultListener<List<Artista>>() {
                @Override
                public void finish(List<Artista> resultado) {
                    DaoArtistas daoArtistas = new DaoArtistas(context);
                    daoArtistas.agregarArtistas(resultado);

                    listResultListener.finish(resultado);
                }
            };
            DaoArtistas daoArtistas = new DaoArtistas(context);
            daoArtistas.obtenerArtista(listaDelController);
        }else {
            DaoArtistas daoArtistas = new DaoArtistas(context);
            List<Artista> artistaList = daoArtistas.buscarArtistas();
            listResultListener.finish(artistaList);
        }

    }

    public void obtenerArtistaPorId(final Context context, final ResultListener<List<Artista>> listResultListener, Integer idArtista){
        if (HTTPConnectionManager.isNetworkingOnline(context)){
            ResultListener <List<Artista>>listaDelController = new ResultListener<List<Artista>>() {
                @Override
                public void finish(List<Artista> resultado) {
                    DaoArtistas daoArtistas = new DaoArtistas(context);
                    daoArtistas.agregarArtistas(resultado);
                    listResultListener.finish(resultado);
                }
            };
            DaoArtistas daoArtistas = new DaoArtistas(context);
            daoArtistas.obtenerArtistaPorId(listaDelController, idArtista);
        }else {
            DaoArtistas daoArtistas = new DaoArtistas(context);
            List<Artista> artistaList = daoArtistas.buscarArtistas();
            listResultListener.finish(artistaList);
        }

    }
}
