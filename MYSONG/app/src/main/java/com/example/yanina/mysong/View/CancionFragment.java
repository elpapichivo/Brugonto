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
import com.example.yanina.mysong.View.Adaptadores.AdaptadorCancion;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CancionFragment extends Fragment {

    AdaptadorCancion adaptadorCancion;
    public static final String CLAVE_ALBUM="claveAlbum";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cancion, container, false);
        Bundle bundle=getArguments();

        final RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.recyclerCanciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        adaptadorCancion= new AdaptadorCancion(getContext());
        ControllerCancion controllerCancion=new ControllerCancion();
        controllerCancion.obtenerCancionPorAlbum(new ResultListener<List<Cancion>>() {
            @Override
            public void finish(List<Cancion> resultado) {
                recyclerView.setAdapter(adaptadorCancion);
                adaptadorCancion.agregarCancion(resultado);
                adaptadorCancion.notifyDataSetChanged();

            }
        }, bundle.getInt(CLAVE_ALBUM));





        return view;
    }

}
