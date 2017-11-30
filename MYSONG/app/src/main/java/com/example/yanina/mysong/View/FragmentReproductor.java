package com.example.yanina.mysong.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yanina.mysong.Controller.ControllerCancion;
import com.example.yanina.mysong.Dao.DaoCancion;
import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.Model.Cancion;
import com.example.yanina.mysong.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentReproductor extends Fragment {

    private Artista artista;
    private Integer id;
    public static final String CLAVE_IMAGEN = "claveImagen";
    public static final String CLAVE_NOMBRE_CANCION = "claveNombreCancion";
    public static final String CLAVE_NOMBRE_ARTISTA = "claveNombreArtista";
    public static final String CLAVE_ID_CANCION = "claveIdCancion";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

           View view= inflater.inflate(R.layout.fragment_reproductor, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.reproductorImagen);
        TextView textViewArtista = (TextView) view.findViewById(R.id.reproductorNombreArtista);
        TextView textViewNombreCancion = (TextView) view.findViewById(R.id.reproductorNombreCancion);

        final Bundle bundle = getArguments();

        //imageView.setImageResource(bundle.getString(CLAVE_IMAGEN));
        textViewArtista.setText(bundle.getString(CLAVE_NOMBRE_ARTISTA));
        textViewNombreCancion.setText(bundle.getString(CLAVE_NOMBRE_CANCION));

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.error);
        Glide.with(getContext()).load(bundle.getString(CLAVE_IMAGEN)).apply(requestOptions).into(imageView);

        ImageView botonFavorito = (ImageView) view.findViewById(R.id.agregarFavorito);
        botonFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerCancion controllerCancion =new ControllerCancion();
                controllerCancion.setiarLasCancionesFavoritas(getContext(), bundle.getString(CLAVE_ID_CANCION) );


            }
        });

        ImageView botonAnterior = (ImageView) view.findViewById(R.id.anterior);
        botonAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Boton Anterior (En construccion)", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView botonPlay = (ImageView) view.findViewById(R.id.play);
        botonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Boton Play (En construccion)", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView botonSiguiente = (ImageView) view.findViewById(R.id.siguiente);
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Boton Siguiente (En construccion)", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }




    public static FragmentReproductor factoryReproducor(Cancion cancion) {


        FragmentReproductor fragmentReproductor = new FragmentReproductor();
        Bundle bundle = new Bundle();
        bundle.putString(CLAVE_IMAGEN, cancion.getArtista().getFoto());
        bundle.putString(CLAVE_NOMBRE_ARTISTA, cancion.getArtista().getNombreArtista());
        bundle.putString(CLAVE_NOMBRE_CANCION, cancion.getTitle());
        bundle.putString(CLAVE_ID_CANCION, cancion.getId());
        fragmentReproductor.setArguments(bundle);
        return fragmentReproductor;

    }

  //  public void agregarFavorito(View view){
  //      ImageView  agregar = ImageView view.findViewById(R.id.artistaFavorito);

  //  }


}
