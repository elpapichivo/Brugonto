package com.example.yanina.mysong.View;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.yanina.mysong.Model.Album;
import com.example.yanina.mysong.R;
import com.example.yanina.mysong.View.Adaptadores.AdaptadorAlbum;

public class DetalleActivity extends AppCompatActivity implements AdaptadorAlbum.ComunicadorAlbumes{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        FragmentDetalleViewPager fragmentDetalleViewPager=new FragmentDetalleViewPager();


        //Recibir intent
        Intent intent=getIntent();
        //recibir bundle
        Bundle bundle=intent.getExtras();

        //Setear al fragment el bundle
        fragmentDetalleViewPager.setArguments(bundle);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity2Lay, fragmentDetalleViewPager);
        //fragmentTransaction.replace(R.id.);
        fragmentTransaction.commit();

    }

    @Override
    public void enviarIInfo(Album album) {
        CancionFragment cancionfragm=new CancionFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(CancionFragment.CLAVE_ALBUM, album.getId());
        cancionfragm.setArguments(bundle);
        FragmentManager fragmentManage=getSupportFragmentManager();
        FragmentTransaction fragmentTransactionn=fragmentManage.beginTransaction();
        fragmentTransactionn.replace(R.id.activity2Lay, cancionfragm);
        fragmentTransactionn.commit();


    }
}