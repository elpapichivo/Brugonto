package com.example.yanina.mysong.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReproductor extends Fragment {

    private Artista artista;
    public static final String CLAVE_IMAGEN = "claveImagen";
    public static final String CLAVE_NOMBRE_CANCION = "claveNombreCancion";
    public static final String CLAVE_NOMBRE_ARTISTA = "claveNombreArtista";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

           View view= inflater.inflate(R.layout.fragment_reproductor, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.reproductorImagen);
        TextView textViewArtista = (TextView) view.findViewById(R.id.reproductorNombreArtista);
        TextView textViewNombreCancion = (TextView) view.findViewById(R.id.reproductorNombreCancion);

        Bundle bundle = getArguments();

        //imageView.setImageResource(bundle.getString(CLAVE_IMAGEN));
        textViewArtista.setText(bundle.getString(CLAVE_NOMBRE_ARTISTA));
        textViewNombreCancion.setText(bundle.getString(CLAVE_NOMBRE_CANCION));

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.error);
        Glide.with(getContext()).load(bundle.getString(CLAVE_IMAGEN)).apply(requestOptions).into(imageView);

        return view;
    }


    public static FragmentReproductor factoryReproducor(String imagen, String artista, Integer cancion, Integer id) {


        FragmentReproductor fragmentReproductor = new FragmentReproductor();
        Bundle bundle = new Bundle();
        bundle.putString(CLAVE_IMAGEN, imagen);
        bundle.putString(CLAVE_NOMBRE_ARTISTA, artista);
        bundle.putInt(CLAVE_NOMBRE_CANCION, cancion);
        fragmentReproductor.setArguments(bundle);
        return fragmentReproductor;
    }


}
