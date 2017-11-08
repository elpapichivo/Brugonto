package com.example.yanina.mysong.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yanina.mysong.Model.CancionFavorita;
import com.example.yanina.mysong.R;

import java.util.List;

/**
 * Created by ma on 01/11/17.
 */

public class AdaptadorDeFavoritos extends RecyclerView.Adapter<AdaptadorDeFavoritos.FavoritoViewHolder> {
    private Context context;
    private List<CancionFavorita>listaDeCancionesFavoritas;
    private  ComunicadorFavoritos comunicadorFavoritos;
    public AdaptadorDeFavoritos(Context context, List<CancionFavorita> listaDeCancionesFavoritas) {
        this.context=context;
        this.listaDeCancionesFavoritas=listaDeCancionesFavoritas;
        comunicadorFavoritos=(ComunicadorFavoritos) context;
    }

    @Override
    public FavoritoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_celda_favoritos,parent,false);
        FavoritoViewHolder favoritoViewHolder=new FavoritoViewHolder(view);
        return favoritoViewHolder;
    }

    @Override
    public void onBindViewHolder(FavoritoViewHolder holder, int position) {
        final CancionFavorita cancionFavorita=listaDeCancionesFavoritas.get(position);
        holder.bindFavorito(cancionFavorita);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comunicadorFavoritos.enviarinfo(cancionFavorita);
            }
        });
    }



    @Override
    public int getItemCount() {
        return listaDeCancionesFavoritas.size();
    }
    public class FavoritoViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewNombreFavorito;
        private TextView textViewArtistaFavorito;
        private LinearLayout linearLayout;

        public FavoritoViewHolder(View itemView) {
            super(itemView);

            textViewNombreFavorito=(TextView)itemView.findViewById(R.id.nombreFavorito);
            textViewArtistaFavorito=(TextView)itemView.findViewById(R.id.artistaFavorito);
        }
        public void bindFavorito(CancionFavorita cancionFavorita){
            textViewNombreFavorito.setText(cancionFavorita.getNombre());
            textViewArtistaFavorito.setText(cancionFavorita.getArtista());

        }
    }
    public interface ComunicadorFavoritos{
        public void enviarinfo(CancionFavorita cancionFavorita);
    }

}
