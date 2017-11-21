package com.example.yanina.mysong.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by elpapichivo on 17/11/2017.
 */

public class Album {
    @SerializedName("cover_medium")
    private String foto;
    @SerializedName("title")
    private String nombreAlbum;

    public Album(String foto, String nombreAlbum) {
        this.foto = foto;
        this.nombreAlbum = nombreAlbum;
    }

    public String getFoto() {
        return foto;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }
}
