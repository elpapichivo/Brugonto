package com.example.yanina.mysong.View.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yanina.mysong.Model.Album;
import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elpapichivo on 17/11/2017.
 */

public class AdaptadorAlbum extends RecyclerView.Adapter<AdaptadorAlbum.ViewHolderAlbum> {
    private List<Album> listaDeAlbum= new ArrayList<>();
    private ComunicadorAlbumes comunicadorAlbumes;
    private Context context;

    public AdaptadorAlbum(Context context) {
        this.context = context;
        comunicadorAlbumes=(ComunicadorAlbumes)context;
    }

    @Override
    public AdaptadorAlbum.ViewHolderAlbum onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.item_album, parent,false);
        ViewHolderAlbum viewHolderAlbum=new ViewHolderAlbum(view);

        return viewHolderAlbum;
    }

    @Override
    public void onBindViewHolder(ViewHolderAlbum holder, int position) {
        final Album album=listaDeAlbum.get(position);
        holder.bindAlbum(album, context );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comunicadorAlbumes.enviarIInfo(album);
            }
        });
    }
    

    @Override
    public int getItemCount() {
        return listaDeAlbum.size();
    }



    public class ViewHolderAlbum extends RecyclerView.ViewHolder {
        private ImageView imagenAlbum;
        private TextView nombreAlbum;

        public ViewHolderAlbum(View itemView) {
            super(itemView);
            imagenAlbum = (ImageView) itemView.findViewById(R.id.imagenAlbum);
            nombreAlbum = (TextView) itemView.findViewById(R.id.nombreAlbum);


        }

        public void bindAlbum(Album album, Context context) {
            //imageViewPelicula.setImageResource(artista.getFoto());
            nombreAlbum.setText(album.getNombreAlbum());
            RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.error);
            Glide.with(context).load(album.getFoto()).apply(requestOptions).into(imagenAlbum);
        }
    }


    public interface ComunicadorAlbumes{
        public void enviarIInfo(Album album);
    }
    public void agregarAlbum(List<Album>listaDeAlbumes){
        listaDeAlbum.addAll(listaDeAlbumes);
    }
    }

