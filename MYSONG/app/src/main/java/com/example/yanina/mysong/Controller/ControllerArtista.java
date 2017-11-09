package com.example.yanina.mysong.Controller;

import com.example.yanina.mysong.Dao.DaoArtistas;
import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.Utils.ResultListener;

import java.util.List;

/**
 * Created by ma on 08/11/17.
 */

public class ControllerArtista {
    public void obtenerArtista(final ResultListener<List<Artista>> listResultListener){
        ResultListener <List<Artista>>listaDelController=new ResultListener<List<Artista>>() {
            @Override
            public void finish(List<Artista> resultado) {
                listResultListener.finish(resultado);
            }
        };
        DaoArtistas daoArtistas=new DaoArtistas();
        daoArtistas.obtenerArtista(listaDelController);


    }
}
