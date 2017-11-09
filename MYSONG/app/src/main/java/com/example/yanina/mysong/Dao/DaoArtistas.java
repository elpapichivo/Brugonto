package com.example.yanina.mysong.Dao;

import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.R;
import com.example.yanina.mysong.Utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 08/11/17.
 */

public class DaoArtistas {
    public void obtenerArtista(ResultListener<List<Artista>> listResultListener){
        List<Artista> listaDeArtistas = new ArrayList<>();
        listaDeArtistas.add(new Artista("Foo Fighters","detalles del artista","rock", R.drawable.foo_fighters));
        listaDeArtistas.add(new Artista("Guns n Rose ","detalles del artista","rock",R.drawable.guns_n_roses_logo));
        listaDeArtistas.add(new Artista("Linkin Park","detalles del artista","rock",R.drawable.linkin_park));
        listaDeArtistas.add(new Artista("Metallica","detalles del artista","rock",R.drawable.metallica));
        listaDeArtistas.add(new Artista("Nirvana","detalles del artista","rock",R.drawable.nirvana));
        listaDeArtistas.add(new Artista("Pink floyd","detalles del artista","rock",R.drawable.pinkfloyd));
        listaDeArtistas.add(new Artista("The Rolling Stone","detalles del artista","rock",R.drawable.the_rolling_stones));
        listaDeArtistas.add(new Artista("The Who","detalles del artista","rock",R.drawable.the_who));
        listResultListener.finish(listaDeArtistas);

    }
}
