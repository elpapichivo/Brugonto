package com.example.yanina.mysong;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        DetalleFragment detalleFragment=new DetalleFragment();


        //Recibir intent
        Intent intent=getIntent();
        //recibir bundle
        Bundle bundle=intent.getExtras();

        //Setear al fragment el bundle
        detalleFragment.setArguments(bundle);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity2Lay, detalleFragment);
        //fragmentTransaction.replace(R.id.);
        fragmentTransaction.commit();

    }
}