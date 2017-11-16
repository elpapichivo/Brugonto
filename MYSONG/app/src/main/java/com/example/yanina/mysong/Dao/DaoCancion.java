package com.example.yanina.mysong.Dao;

import android.os.AsyncTask;

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

public class DaoCancion {
    public void obtenerCancion(ResultListener<List<Cancion>>listaDelController){
        TareaAsincronaCancion tareaAsincronaCancion=new TareaAsincronaCancion(listaDelController);
        tareaAsincronaCancion.execute(DeezerHelper.chartTracksPorGenero(0));

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

}
