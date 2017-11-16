package com.example.yanina.mysong.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.yanina.mysong.Controller.ControllerArtista;
import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.R;
import com.example.yanina.mysong.Utils.ResultListener;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleViewPager extends Fragment {
    private List<Artista>artistaList;
    public static final String CLAVE_POSITION="clavePosition";

    public FragmentDetalleViewPager() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_detalle, container, false);


        // Inflate the layout for this fragment


        final ViewPager viewPager=(ViewPager) view.findViewById(R.id.viewPager);
        Bundle bundle=getArguments();
        final Integer position= bundle.getInt(CLAVE_POSITION);

        ControllerArtista controllerArtista=new ControllerArtista();
        controllerArtista.obtenerArtista(new ResultListener<List<Artista>>() {
            @Override
            public void finish(List<Artista> resultado) {
                AdaptadorViewPager adaptadorViewPager=new AdaptadorViewPager(getChildFragmentManager(),resultado);
                viewPager.setAdapter(adaptadorViewPager);
                viewPager.setCurrentItem(position);


            }
        });

        return view;
    }



}
