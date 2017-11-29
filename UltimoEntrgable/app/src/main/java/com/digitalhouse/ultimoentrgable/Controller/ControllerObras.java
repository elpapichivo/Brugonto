package com.digitalhouse.ultimoentrgable.Controller;

import com.digitalhouse.ultimoentrgable.DAO.DaoObras;
import com.digitalhouse.ultimoentrgable.Model.Obras;
import com.digitalhouse.ultimoentrgable.Utils.ResultListener;

import java.util.List;

/**
 * Created by ma on 27/11/17.
 */

public class ControllerObras {
    public void obtenerObras(final ResultListener<List<Obras>> listResultListener) {
        ResultListener<List<Obras>> listaDelController = new ResultListener<List<Obras>>() {
            @Override
            public void finish(List<Obras> resultado) {
                listResultListener.finish(resultado);
            }
        };
        DaoObras daoObras = new DaoObras();
        daoObras.
    }
}
