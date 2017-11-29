package com.digitalhouse.ultimoentrgable.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ma on 27/11/17.
 */

public class ContenedorObras {
    @SerializedName("data")
    private List<Obras> obrasList;

    public List<Obras> getObrasList() {
        return obrasList;
    }
}