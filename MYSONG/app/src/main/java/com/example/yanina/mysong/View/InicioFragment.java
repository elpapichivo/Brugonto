package com.example.yanina.mysong.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.yanina.mysong.Controller.ControllerArtista;
import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.R;
import com.example.yanina.mysong.Utils.DeezerHelper;
import com.example.yanina.mysong.Utils.ResultListener;
import com.example.yanina.mysong.View.Adaptadores.Adaptador;

import java.util.ArrayList;
import java.util.List;


public class InicioFragment extends Fragment {

    RecyclerView recyclerView;
    Adaptador adapterRecycler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view=   inflater.inflate(R.layout.fragment_inicio, container, false);

        EditText editText = view.findViewById(R.id.buscar);

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filtrar(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewBuscar);

        return view;
    }



    public void filtrar(final CharSequence charSequence)
    {

        final ControllerArtista controllerArtista = new ControllerArtista();
        controllerArtista.obtenerArtistaNombre(getContext(), charSequence.toString(), new ResultListener<List<Artista>>() {
            @Override
            public void finish(List<Artista> resultado) {

                adapterRecycler = new Adaptador(getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(adapterRecycler);
                adapterRecycler.setListaDeArtistas(resultado);




            }
        });



    }

}
