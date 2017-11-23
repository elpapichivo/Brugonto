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
    private Integer id;
    @SerializedName("tracklist")
    private String tracks;


    public Album(String foto, String nombreAlbum, Integer id) {
        this.foto = foto;
        this.nombreAlbum = nombreAlbum;
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public Integer getId() {
        return id;
    }
}
