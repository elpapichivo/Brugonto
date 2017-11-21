package com.example.yanina.mysong.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by elpapichivo on 20/11/2017.
 */

public class ContenedorAlbum {
    @SerializedName("data")
    private List<Album> listaDeAlbum;

    public List<Album>getListaDeAlbum(){
        return listaDeAlbum;
    }
}


