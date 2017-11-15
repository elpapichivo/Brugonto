package com.example.yanina.mysong.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanina on 19/10/2017.
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.ArtistaViewHolder> {


    private Context context;
    private List<Artista> listaDeArtistas;
    private Comunicador comunicadorClick;

    public Adaptador(Context context) {
        this.context = context;
        this.listaDeArtistas = new ArrayList<>();
        comunicadorClick = (Comunicador) context;
    }

    public void setListaDeArtistas(List<Artista> listaDeArtistas) {
        this.listaDeArtistas = listaDeArtistas;
        notifyDataSetChanged();
    }

    @Override
    public ArtistaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Busco el inflador
        LayoutInflater inflater = LayoutInflater.from(context);
        //Inflo la vista de la celda que cree para mostrar las peliculas
        View view = inflater.inflate(R.layout.item_celda, parent, false);
        //Instancio un ViewHolder y le paso la vista inflada
        ArtistaViewHolder peliculaViewHolder = new ArtistaViewHolder(view);
        //Devuelvo la vista
        return peliculaViewHolder;



    }

    @Override
    public void onBindViewHolder(ArtistaViewHolder holder, final int position) {
        //Busco la pelicula en la posicion correspondiente
        final Artista artista = listaDeArtistas.get(position);
        //Le paso al viewHolder la pelicula
        holder.bindArtista(artista);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comunicadorClick.enviarInfo(position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return listaDeArtistas.size();
    }

    public class ArtistaViewHolder extends RecyclerView.ViewHolder {
        //Atributos: componentes del layout de la celda
        private ImageView imageViewPelicula;
        private LinearLayout layout;
        private TextView textViewNombre;
        private TextView textViewGenero;

        //Constructor
        public ArtistaViewHolder(View itemView) {
            super(itemView);
            //Busco los componentes correspondientes en la vista de la celda
            imageViewPelicula = (ImageView) itemView.findViewById(R.id.celda_lista_imagen);
            textViewNombre = (TextView) itemView.findViewById(R.id.celda_lista_nombre_artista);
            textViewGenero = (TextView) itemView.findViewById(R.id.celda_lista_genero);


        }

        public void bindArtista(Artista artista){
            //imageViewPelicula.setImageResource(artista.getFoto());
            textViewNombre.setText(artista.getNombreArtista());
            textViewGenero.setText(artista.getId().toString());


    }



    }
    public interface Comunicador{
        //Creo un método para enviar la informacion
        public void enviarInfo(Integer position);
    }

    public void agregarMusica(List<Artista>listaArtistas){
        listaDeArtistas.addAll(listaArtistas);

    }

}
