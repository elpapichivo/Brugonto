package com.example.yanina.mysong.Dao;

import android.os.AsyncTask;

import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.Model.ContenedorArtista;
import com.example.yanina.mysong.R;
import com.example.yanina.mysong.Utils.DAOException;
import com.example.yanina.mysong.Utils.DeezerHelper;
import com.example.yanina.mysong.Utils.HTTPConnectionManager;
import com.example.yanina.mysong.Utils.ResultListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 08/11/17.
 */

public class DaoArtistas {
    public void obtenerArtista(ResultListener<List<Artista>> listaDelController){
        TareaAsincrona tareaAsincrona=new TareaAsincrona(listaDelController);
        tareaAsincrona.execute(DeezerHelper.chartArtistsPorGenero(0));
    }

    public void obtenerArtistaPorId(ResultListener<List<Artista>> listaDelController, Integer idArtista){
        TareaAsincrona tareaAsincrona=new TareaAsincrona(listaDelController);
        tareaAsincrona.execute(DeezerHelper.artistPorId(idArtista));
    }

    public class TareaAsincrona extends AsyncTask <String, Void,List<Artista>>{
        private ResultListener<List<Artista>> listenerDelController;

        public TareaAsincrona(ResultListener <List<Artista>>listenerDelController){
            this.listenerDelController=listenerDelController;
        }

        @Override
        protected List<Artista> doInBackground(String... params) {
            List<Artista> noticiaList;
            String url = params[0];

            HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
            try {
                // Establezco la conexión a Internet y obtengo en json
                String json = httpConnectionManager.getRequestString(url);

                // Creo una instancia de GSON
                Gson gson = new Gson();
                // Le pido a GSON que mapee el JSON
                ContenedorArtista contenedorNoticia = gson.fromJson(json, ContenedorArtista.class);
                // Obtengo la lista de noticias del contenedor noticias
                noticiaList = contenedorNoticia.getListaDeArtista();
            } catch (DAOException e) {
                // En caso de que algo salga mal, devuelvo una lista vacia
                noticiaList = new ArrayList<>();
                e.printStackTrace();
            }

            return noticiaList;

        }

        @Override
        protected void onPostExecute(List<Artista> artistas) {
            super.onPostExecute(artistas);
            listenerDelController.finish(artistas);
        }
    }

}
