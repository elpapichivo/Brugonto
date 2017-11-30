package com.example.yanina.mysong.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public static final String CLAVE_ID = "idCancion";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cancion, container, false);

          TextView textView = (TextView) view.findViewById(R.id.nombreAlbum);

        Bundle bundle=getArguments();

      //  textView.setText(bundle.getString(CLAVE_ALBUM));


        final RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.recyclerCanciones);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        adaptadorCancion= new AdaptadorCancion(getContext(),bundle.getInt(CLAVE_ALBUM) );
        ControllerCancion controllerCancion=new ControllerCancion();
        controllerCancion.obtenerCancionPorAlbum(getContext(), new ResultListener<List<Cancion>>() {
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
