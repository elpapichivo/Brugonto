package com.example.yanina.mysong.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanina.mysong.Controller.ControllerArtista;
import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.R;
import com.example.yanina.mysong.Utils.ResultListener;
import com.example.yanina.mysong.View.Adaptadores.Adaptador;

import java.util.List;


public class FragmentLista extends Fragment {

    Adaptador adapterRecycler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista, container, false);

        ControllerArtista controllerArtista=new ControllerArtista();



        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewPeliculas);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        adapterRecycler = new Adaptador(getContext());

        controllerArtista.obtenerArtista(new ResultListener<List<Artista>>() {
            @Override
            public void finish(List<Artista> resultado) {
                adapterRecycler.agregarMusica(resultado);
                adapterRecycler.notifyDataSetChanged();

            }
        });

        recyclerView.setAdapter(adapterRecycler);

        return view;
    }


}


