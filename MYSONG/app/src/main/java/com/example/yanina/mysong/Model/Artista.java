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
    private Integer position;
    @SerializedName("picture_big")
    private String foto;




    public Integer getId() {
        return id;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public Integer getPosition() {
        return position;
    }

    public String getFoto() {
        return foto;
    }

    public Artista( String nombreArtista, Integer posicion, String foto) {
        this.id = id;
        this.nombreArtista = nombreArtista;
        this.position = posicion;
        this.foto = foto;
    }

    public Artista(Integer id, String nombreArtista, Integer position, String foto) {
        this.id = id;
        this.nombreArtista = nombreArtista;
        this.position = position;
        this.foto = foto;
    }


    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                ", nombreArtista='" + nombreArtista + '\'' +
                ", position=" + position +
                ", foto='" + foto + '\'' +
                '}';
    }
}
