package com.example.yanina.mysong.Model;

/**
 * Created by yanina on 18/10/2017.
 */

public class Artista {

    private String nombreArtista;
    private String nombreCancion;
    private String genero;
    private Integer foto;

    public Artista(String nombreArtista, String nombreCancion, String genero, Integer foto) {
        this.nombreArtista = nombreArtista;
        this.nombreCancion = nombreCancion;
        this.genero = genero;
        this.foto = foto;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public String getGenero() {
        return genero;
    }

    public Integer getFoto() {
        return foto;
    }
}
