package com.example.yanina.mysong.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanina.mysong.Controller.ControllerCancion;
import com.example.yanina.mysong.Model.Cancion;
import com.example.yanina.mysong.R;
import com.example.yanina.mysong.Utils.ResultListener;
import com.example.yanina.mysong.View.Adaptadores.AdaptadorDeFavoritos;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavoritos extends Fragment {



    AdaptadorDeFavoritos adaptadorDeFavoritos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ControllerCancion controllerCancion=new ControllerCancion();


        View view=inflater.inflate(R.layout.fragment_favoritos, container, false);

        RecyclerView recyclerView= (RecyclerView)view.findViewById(R.id.favoritosReciclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        adaptadorDeFavoritos = new AdaptadorDeFavoritos(getContext());
        controllerCancion.obtenerCancionOffline(getContext(), new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                adaptadorDeFavoritos.agregarCancion(resultado);
                adaptadorDeFavoritos.notifyDataSetChanged();
            }
        });



        recyclerView.setAdapter(adaptadorDeFavoritos);


        return view;
    }

}
