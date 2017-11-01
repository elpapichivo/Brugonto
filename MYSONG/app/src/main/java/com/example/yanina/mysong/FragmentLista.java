package com.example.yanina.mysong;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FragmentLista extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_lista, container, false);

        List<Artista> listaDeArtistas = new ArrayList<>();
        listaDeArtistas.add(new Artista("Foo Fighters","no hay","rock",R.drawable.foo_fighters));
        listaDeArtistas.add(new Artista("Guns n Rose ","no hay","rock",R.drawable.guns_n_roses_logo));
        listaDeArtistas.add(new Artista("Linkin Park","no hay","rock",R.drawable.linkin_park));
        listaDeArtistas.add(new Artista("Metallica","no hay","rock",R.drawable.metallica));
        listaDeArtistas.add(new Artista("Nirvana","no hay","rock",R.drawable.nirvana));
        listaDeArtistas.add(new Artista("Pink floyd","no hay","rock",R.drawable.pinkfloyd));
        listaDeArtistas.add(new Artista("The Rolling Stone","no hay","rock",R.drawable.the_rolling_stones));
        listaDeArtistas.add(new Artista("The Who","no hay","rock",R.drawable.the_who));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewPeliculas);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        Adaptador adapterRecycler = new Adaptador(getContext(), listaDeArtistas);

        recyclerView.setAdapter(adapterRecycler);

        return view;
    }


}


