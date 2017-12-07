package com.digitalhouse.firebaseivanentregable.DAO;

import android.os.AsyncTask;

import com.digitalhouse.firebaseivanentregable.Model.ContenedorObras;
import com.digitalhouse.firebaseivanentregable.Model.Obras;
import com.digitalhouse.firebaseivanentregable.Utils.DAOException;
import com.digitalhouse.firebaseivanentregable.Utils.HTTPConnectionManager;
import com.digitalhouse.firebaseivanentregable.Utils.ResultListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 27/11/17.
 */

public class DaoObras {

    public void obtenerObras(ResultListener<List<Obras>> listaDelController){
        TareaAsincronaObra tareaAsincronaCancion=new TareaAsincronaObra(listaDelController);
        tareaAsincronaCancion.execute("https://api.myjson.com/bins/x858r");

    }
    public class TareaAsincronaObra extends AsyncTask<String, Void,List<Obras>>{

        private ResultListener<List<Obras>> listenerDelController;

        public TareaAsincronaObra(ResultListener<List<Obras>> listenerDelController) {
            this.listenerDelController = listenerDelController;
        }

        @Override
        protected List<Obras> doInBackground(String... strings) {
            List<Obras> cancionList;
            String url=strings[0];
            HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
            try {
                // Establezco la conexión a Internet y obtengo en json
                String json = httpConnectionManager.getRequestString(url);

                // Creo una instancia de GSON
                Gson gson = new Gson();
                // Le pido a GSON que mapee el JSON
                ContenedorObras contenedorCancion = gson.fromJson(json, ContenedorObras.class);
                cancionList = contenedorCancion.getObrasList();
            }catch (DAOException e) {
                // En caso de que algo salga mal, devuelvo una lista vacia
                cancionList = new ArrayList<>();
                e.printStackTrace();
            }



            return cancionList;
        }

        @Override
        protected void onPostExecute(List<Obras> obrasList) {
            super.onPostExecute(obrasList);
            listenerDelController.finish(obrasList);
        }
    }
    }


