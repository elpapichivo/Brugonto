package com.example.yanina.mysong;

/**
 * Created by ma on 01/11/17.
 */

public class CancionFavorita {
    private String nombre;
    private String artista;

    public CancionFavorita(String nombre, String artista) {
        this.nombre = nombre;
        this.artista = artista;
    }

    public String getNombre() {
        return nombre;
    }

    public String getArtista() {
        return artista;
    }
}
