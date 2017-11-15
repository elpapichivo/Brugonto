package com.example.yanina.mysong.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanina.mysong.Model.CancionFavorita;
import com.example.yanina.mysong.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavoritos extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<CancionFavorita>listaDeCancionesFavoritas=new ArrayList<>();
        listaDeCancionesFavoritas.add(new CancionFavorita("Bailalo","QCY"));
        listaDeCancionesFavoritas.add(new CancionFavorita("Metafisica","El Bordo"));
        listaDeCancionesFavoritas.add(new CancionFavorita("Shoot to Thrill", "ACDC"));
        listaDeCancionesFavoritas.add(new CancionFavorita("Killpop", "Slipknot"));

        View view=inflater.inflate(R.layout.fragment_favoritos, container, false);
        RecyclerView recyclerView= (RecyclerView)view.findViewById(R.id.favoritosReciclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        AdaptadorDeFavoritos adaptadorDeFavoritos=new AdaptadorDeFavoritos(getContext(),listaDeCancionesFavoritas);
        recyclerView.setAdapter(adaptadorDeFavoritos);


        return view;
    }

}
