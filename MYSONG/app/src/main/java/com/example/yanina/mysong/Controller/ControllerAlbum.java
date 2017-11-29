package com.example.yanina.mysong.Controller;

import android.content.Context;

import com.example.yanina.mysong.Dao.DaoAlbum;
import com.example.yanina.mysong.Model.Album;
import com.example.yanina.mysong.Utils.ResultListener;

import java.util.List;

/**
 * Created by elpapichivo on 20/11/2017.
 */

public class ControllerAlbum {
    public void obtenerAlbumPorArtista(Context context, final ResultListener<List<Album>> listResultListener, Integer IDartista){
        ResultListener <List<Album>>listaDelController = new ResultListener<List<Album>>() {
            @Override
            public void finish(List<Album> resultado) {
                listResultListener.finish(resultado);
            }
        };
        DaoAlbum daoAlbum = new DaoAlbum(context);
        daoAlbum.obtenerAlbum(listaDelController, IDartista);

    }
    }
