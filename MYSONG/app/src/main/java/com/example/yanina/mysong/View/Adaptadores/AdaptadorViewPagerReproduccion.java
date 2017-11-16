package com.example.yanina.mysong.View.Adaptadores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.View.FragmentContenedorReproduccion;
import com.example.yanina.mysong.View.FragmentReproductor;

import java.util.ArrayList;
import java.util.List;


public class AdaptadorViewPagerReproduccion  extends FragmentPagerAdapter {

    private List<FragmentReproductor>listaDeFragmentReproduccion;

    public AdaptadorViewPagerReproduccion(FragmentManager fm, List<Artista> listaDeArtista) {
        super(fm);
        this.listaDeFragmentReproduccion = new ArrayList<>();

        for (Artista artista: listaDeArtista){
            listaDeFragmentReproduccion.add(FragmentReproductor.factoryReproducor(artista.getFoto(),artista.getNombreArtista(),artista.getNombreCancion(),artista.getId()));


        }
    }


    @Override
    public Fragment getItem(int position) {
        return listaDeFragmentReproduccion.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragmentReproduccion.size();
    }
}
