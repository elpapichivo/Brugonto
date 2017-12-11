package com.example.yanina.mysong.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.yanina.mysong.Controller.ControllerAlbum;
import com.example.yanina.mysong.Controller.ControllerArtista;
import com.example.yanina.mysong.Model.Album;
import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.R;
import com.example.yanina.mysong.Utils.ResultListener;
import com.example.yanina.mysong.View.Adaptadores.AdaptadorAlbum;
import com.example.yanina.mysong.View.Adaptadores.AdaptadorViewPager;

import java.util.List;

public class FragmentDetalleViewPager extends Fragment {

    private List<Artista>artistaList;
    public static final String CLAVE_POSITION="clavePosition";
    public static final String CLAVE_ARTISTA="claveArtista";

    AdaptadorAlbum adaptadorAlbum;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_detalle, container, false);



        final ViewPager viewPager=(ViewPager) view.findViewById(R.id.viewPager);

        Bundle bundle=getArguments();
        final Integer position= bundle.getInt(CLAVE_POSITION);
        final Integer idArtista= bundle.getInt(CLAVE_ARTISTA);


        ControllerArtista controllerArtista=new ControllerArtista();
        controllerArtista.obtenerArtista(getContext(), new ResultListener<List<Artista>>() {
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
