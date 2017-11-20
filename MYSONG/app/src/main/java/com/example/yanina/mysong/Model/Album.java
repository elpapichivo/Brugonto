package com.example.yanina.mysong.Model;

/**
 * Created by elpapichivo on 17/11/2017.
 */

public class Album {
    private String foto;
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
