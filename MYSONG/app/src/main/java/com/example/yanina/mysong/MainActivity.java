package com.example.yanina.mysong;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements Adaptador.Comunicador{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instancio un fragment donde está el listview
        FragmentLista fragmentListView = new FragmentLista();
        //Pido el fragment manager
        FragmentManager manager = getSupportFragmentManager();
        //Creo una transaccion
        FragmentTransaction transaction = manager.beginTransaction();
        //Le digo a la transaccion que fragment poner y donde
        transaction.replace(R.id.mainContenedor, fragmentListView);
        //Ejecuto la transaccion
        transaction.commit();



    }

    @Override
    public void enviarInfo(Artista artista) {
        //Creo un intent
        Intent intent = new Intent(this, DetalleActivity.class);
        //Creo un bundle
        Bundle bundle = new Bundle();
        //Pongo en el bundle la informacion
        bundle.putInt(DetalleFragment.CLAVE_IMAGEN, artista.getFoto());
        bundle.putString(DetalleFragment.CLAVE_NOMBRE_ARTISTA, artista.getNombreArtista());
        bundle.putString(DetalleFragment.CLAVE_NOMBRE_CANCION, artista.getNombreCancion());
        bundle.putString(DetalleFragment.CLAVE_GENERO, artista.getGenero());
        //Pongo el bundle en el intent
        intent.putExtras(bundle);
        //Empiezo la actividad siguiente
        startActivity(intent);
    }
}
