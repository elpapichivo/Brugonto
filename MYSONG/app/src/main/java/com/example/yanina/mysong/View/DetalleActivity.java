package com.example.yanina.mysong.View;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yanina.mysong.R;

public class DetalleActivity extends AppCompatActivity {

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
}