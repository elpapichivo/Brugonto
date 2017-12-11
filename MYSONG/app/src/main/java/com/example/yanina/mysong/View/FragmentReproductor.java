package com.example.yanina.mysong.View;


import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
    public static final String CLAVE_PREVIEW = "clavePreview";
    MediaPlayer mediaPlayer;
    int posicion = 0;
    public static final String CLAVE_ID_ALBUM = "claveIdAlbum";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reproductor, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.reproductorImagen);
        TextView textViewArtista = (TextView) view.findViewById(R.id.reproductorNombreArtista);
        TextView textViewNombreCancion = (TextView) view.findViewById(R.id.reproductorNombreCancion);

        final Bundle bundle = getArguments();
        ControllerCancion controllerCancion = new ControllerCancion();
        String urlFoto = controllerCancion.obtenerImagen(getContext(),bundle.getInt(CLAVE_ID_ALBUM));
        //imageView.setImageResource(bundle.getString(CLAVE_IMAGEN));
        textViewArtista.setText(bundle.getString(CLAVE_NOMBRE_ARTISTA));
        textViewNombreCancion.setText(bundle.getString(CLAVE_NOMBRE_CANCION));

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.error);
        Glide.with(getContext()).load(urlFoto).apply(requestOptions).into(imageView);

        ImageView botonFavorito = (ImageView) view.findViewById(R.id.agregarFavorito);
        botonFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerCancion controllerCancion = new ControllerCancion();
                controllerCancion.setiarLasCancionesFavoritas(getContext(), bundle.getString(CLAVE_ID_CANCION));
                Toast.makeText(getContext(), "Se agrego correctamente", Toast.LENGTH_SHORT).show();

            }
        });

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fabShare);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();

                shareIntent.setAction(Intent.ACTION_SEND);

                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "BruGonTo");

                shareIntent.putExtra(Intent.EXTRA_TEXT, "Te comparto esta cancion: "+ bundle.getString(CLAVE_NOMBRE_CANCION) + "\n" + bundle.getString(CLAVE_IMAGEN));

                shareIntent.setType("text/html");

                startActivity(Intent.createChooser(shareIntent, "Compartir en:"));
            }
        });

        String song = bundle.getString(CLAVE_PREVIEW);

        Uri uri = Uri.parse(song);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(getContext(), uri);

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
                mediaPlayer.start();

            }
        });

        ImageView botonSiguiente = (ImageView) view.findViewById(R.id.siguiente);
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Boton Siguiente (En construccion)", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView botonPause= (ImageView) view.findViewById(R.id.pausa);
        botonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausar(v);
            }
        });
        return view;


    }





    public void pausar(View v) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            posicion = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }
    }






    public static FragmentReproductor factoryReproducor(Cancion cancion) {


        FragmentReproductor fragmentReproductor = new FragmentReproductor();
        Bundle bundle = new Bundle();
        bundle.putString(CLAVE_IMAGEN, cancion.getArtista().getFoto());
        bundle.putString(CLAVE_NOMBRE_ARTISTA, cancion.getArtista().getNombreArtista());
        bundle.putString(CLAVE_NOMBRE_CANCION, cancion.getTitle());
        bundle.putString(CLAVE_ID_CANCION, cancion.getId());
        bundle.putString(CLAVE_PREVIEW, cancion.getPreview());
        bundle.putInt(CLAVE_ID_ALBUM, cancion.getIdAlbum());

        fragmentReproductor.setArguments(bundle);
        return fragmentReproductor;

    }

  //  public void agregarFavorito(View view){
  //      ImageView  agregar = ImageView view.findViewById(R.id.artistaFavorito);

  //  }


}

