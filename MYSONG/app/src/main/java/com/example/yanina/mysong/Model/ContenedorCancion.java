package com.example.yanina.mysong.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ma on 16/11/17.
 */

public class ContenedorCancion {
    @SerializedName("data")
    private List<Cancion>cancionList;
    public List<Cancion>getCancionList(){
        return cancionList;
    }
}
