package com.digitalhouse.firebaseivanentregable.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.digitalhouse.firebaseivanentregable.Controller.ControllerObras;
import com.digitalhouse.firebaseivanentregable.Model.Obras;
import com.digitalhouse.firebaseivanentregable.Utils.ResultListener;
import com.digitalhouse.firebaseivanentregable.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Adaptador.Comunicar{
    Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adaptador=new Adaptador();

        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adaptador);


        ControllerObras controllerObras=new ControllerObras();
        controllerObras.obtenerObras(new ResultListener<List<Obras>>() {
            @Override
            public void finish(List<Obras> resultado) {
                adaptador.agregarObras(resultado);
                adaptador.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void enviarInfo(Integer position) {
        Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show();
    }
    Intent intent=getIntent();

}
