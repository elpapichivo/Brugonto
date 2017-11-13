package com.example.yanina.mysong.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ma on 09/11/17.
 */

public class ContenedorArtista {
    @SerializedName("data")
    private List<Artista>listaDeArtista;

    public List<Artista>getListaDeArtista(){
        return listaDeArtista;
    }
}
