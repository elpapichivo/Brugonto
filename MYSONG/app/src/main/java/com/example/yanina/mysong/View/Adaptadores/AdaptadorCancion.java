package com.example.yanina.mysong.View.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yanina.mysong.Controller.ControllerCancion;
import com.example.yanina.mysong.Model.Cancion;
import com.example.yanina.mysong.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 22/11/17.
 */

public class AdaptadorCancion extends RecyclerView.Adapter<AdaptadorCancion.ViewHolderCancion> {
    private List<Cancion>cancionList=new ArrayList<>();
    private Context context;
    private ComunicadorCancion comunicadorCancion;
    private Integer claveAlbum;

    public AdaptadorCancion(Context context, Integer claveAlbum) {
        this.context = context;
        this.claveAlbum = claveAlbum;
        comunicadorCancion = (ComunicadorCancion) context;
    }

    @Override
    public AdaptadorCancion.ViewHolderCancion onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.item_canciones, parent, false);
        ViewHolderCancion viewHolderCancion=new ViewHolderCancion(view);


        return viewHolderCancion;
    }

    @Override
    public void onBindViewHolder(ViewHolderCancion holder, final int position) {
        Cancion cancion=cancionList.get(position);
        holder.bindCancion(cancion, context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comunicadorCancion.enviarInformacionCancion(claveAlbum, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cancionList.size();
    }

    public class ViewHolderCancion extends RecyclerView.ViewHolder {
        private TextView titulo;
        public ViewHolderCancion(View itemView) {
            super(itemView);
            titulo=(TextView)itemView.findViewById(R.id.cancionTextView);
        }
        public void bindCancion(Cancion cancion, Context context){
            titulo.setText(cancion.getTitle());

        }
    }
    public interface ComunicadorCancion{
        public void enviarInformacionCancion(Integer claveAlbum, Integer posicion);

    }
    public void agregarCancion(List<Cancion>listaDeCanciones){
        cancionList.addAll(listaDeCanciones);

    }
}
