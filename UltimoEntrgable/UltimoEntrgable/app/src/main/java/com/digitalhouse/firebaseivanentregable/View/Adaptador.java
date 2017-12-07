package com.digitalhouse.firebaseivanentregable.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.digitalhouse.firebaseivanentregable.Model.Obras;
import com.digitalhouse.firebaseivanentregable.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 30/11/17.
 */

public class Adaptador extends RecyclerView.Adapter <Adaptador.ObraViewHolder> {
    private List<Obras> listaDeObras = new ArrayList<>();

    @Override
    public ObraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //Inflo la vista de la celda que cree para mostrar las peliculas
        View view = inflater.inflate(R.layout.item, parent, false);
        //Instancio un ViewHolder y le paso la vista inflada
        ObraViewHolder obraViewHolder = new ObraViewHolder(view);
        //Devuelvo la vista
        return obraViewHolder;


    }

    @Override
    public void onBindViewHolder(ObraViewHolder holder, final int position) {

        Context context = holder.itemView.getContext();

        Obras obras = listaDeObras.get(position);
        holder.bindObra(obras, holder.itemView.getContext());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Comunicar) view.getContext()).enviarInfo(position);

            }
        });


    }

    @Override
    public int getItemCount() {
        Integer size = listaDeObras.size();
        return listaDeObras.size();
    }

    public class ObraViewHolder extends RecyclerView.ViewHolder {
        //Atributos: componentes del layout de la celda
        private ImageView imageViewPelicula;
        private LinearLayout layout;
        private TextView textViewNombre;


        //Constructor
        public ObraViewHolder(View itemView) {
            super(itemView);
            //Busco los componentes correspondientes en la vista de la celda
            imageViewPelicula = (ImageView) itemView.findViewById(R.id.imageView);
            textViewNombre = (TextView) itemView.findViewById(R.id.textView);


        }

        public void bindObra(Obras obras, Context context) {
            //imageViewPelicula.setImageResource(artista.getFoto());
            textViewNombre.setText(obras.getName());



        }
    }
    public interface Comunicar{
        public void enviarInfo(Integer position);
    }
    public void agregarObras(List<Obras>obrasList){
        listaDeObras.addAll(obrasList);

    }
}




            //Creo un método para enviar la informacion





