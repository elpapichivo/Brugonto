package com.example.yanina.mysong.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yanina.mysong.Model.Artista;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 08/11/17.
 */

public class AdaptadorViewPager extends FragmentPagerAdapter {
    private List<DetalleFragment>listaDeFragment;
    public AdaptadorViewPager(FragmentManager fm, List<Artista>listaDeArtista) {
        super(fm);
        listaDeFragment=new ArrayList<>();

        for (Artista artista: listaDeArtista){
            listaDeFragment.add(DetalleFragment.factory(artista.getFoto(),artista.getNombreArtista(),artista.getNombreCancion(),artista.getGenero()));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return listaDeFragment.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragment.size();
    }
}
