package com.example.yanina.mysong.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanina.mysong.Controller.ControllerArtista;
import com.example.yanina.mysong.Controller.ControllerCancion;
import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.Model.Cancion;
import com.example.yanina.mysong.R;
import com.example.yanina.mysong.Utils.ResultListener;
import com.example.yanina.mysong.View.Adaptadores.AdaptadorViewPager;
import com.example.yanina.mysong.View.Adaptadores.AdaptadorViewPagerReproduccion;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentContenedorReproduccion extends Fragment {

    private List<Artista>artistaList;
    public static final String CLAVE_POSITION2="clavePosition";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View view=  inflater.inflate(R.layout.fragment_contenedor_reproduccion, container, false);

        final ViewPager viewPager=(ViewPager) view.findViewById(R.id.viewPagerReproductor);

        final Bundle bundle=getArguments();
        //final Integer position= bundle.getInt(CLAVE_POSITION2);

        ControllerCancion controllerCancion = new ControllerCancion();
        controllerCancion.obtenerCancion(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                AdaptadorViewPagerReproduccion adaptadorViewPager=new AdaptadorViewPagerReproduccion(getChildFragmentManager(),resultado);
                viewPager.setAdapter(adaptadorViewPager);
                viewPager.setCurrentItem(bundle.getInt(CLAVE_POSITION2));
            }
        });
        return view;
    }



    }


