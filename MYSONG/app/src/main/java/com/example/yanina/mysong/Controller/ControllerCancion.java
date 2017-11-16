package com.example.yanina.mysong.Controller;

import com.example.yanina.mysong.Dao.DaoCancion;
import com.example.yanina.mysong.Model.Cancion;
import com.example.yanina.mysong.Utils.ResultListener;

import java.util.List;

/**
 * Created by ma on 16/11/17.
 */

public class ControllerCancion {
    public void obtenerCancion(final ResultListener<List<Cancion>> listResultListener){
        ResultListener<List<Cancion>>listaDelController=new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                listResultListener.finish(resultado);
            }
        };
        DaoCancion daoCancion=new DaoCancion();
        daoCancion.obtenerCancion(listaDelController);
    }
}
