package com.example.yanina.mysong.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yanina on 18/10/2017.
 */

public class Artista {
    private Integer id;
    @SerializedName("name")
    private String nombreArtista;
    @SerializedName("position")
    private Integer nombreCancion;
    @SerializedName("picture_small")
    private String foto;




    public Integer getId() {
        return id;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public Integer getNombreCancion() {
        return nombreCancion;
    }

    public String getFoto() {
        return foto;
    }

    public Artista(Integer id, String nombreArtista, Integer nombreCancion, String foto) {
        this.id = id;
        this.nombreArtista = nombreArtista;
        this.nombreCancion = nombreCancion;
        this.foto = foto;
    }
}
