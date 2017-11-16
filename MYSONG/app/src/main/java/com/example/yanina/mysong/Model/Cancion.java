package com.example.yanina.mysong.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ma on 01/11/17.
 */

public class Cancion {

    @SerializedName("title")
    private String title;
    @SerializedName("artist")
    private Artista artista;
    private String preview;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}
