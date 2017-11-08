package com.example.yanina.mysong.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yanina.mysong.Model.Artista;
import com.example.yanina.mysong.Model.CancionFavorita;
import com.example.yanina.mysong.R;

public class MainActivity extends AppCompatActivity implements Adaptador.Comunicador, AdaptadorDeFavoritos.ComunicadorFavoritos{

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeFragment(new InicioFragment());

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutMain);
        navigationView = (NavigationView) findViewById(R.id.navigationViewMain);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Envio el item que se clickeo al metodo que analiza cual fue
                itemClickNavigation(item);
                //Cuando se hace click cierro el drawer
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

        private void itemClickNavigation(MenuItem item){
            //Comparo segun el id del item
            switch (item.getItemId()){
                case R.id.iconoArtista:
                    //Le paso al metodo que pone los fragment un nuevo fragment
                    placeFragment(new FragmentLista());
                    break;
                case R.id.iconoCancion:
                    //Le paso al metodo que pone los fragment un nuevo fragment
                    Toast.makeText(this, "En construccion", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.itemConfiguracion:
                    //Le paso al metodo que pone los fragment un nuevo fragment
                    Toast.makeText(this, "En construccion", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.favoritos:
                    placeFragment(new FragmentFavoritos());
                    break;
                case R.id.login:
                    placeFragment(new LoginFragment());
                default:
                    break;
            }
        }





    @Override
    public void enviarInfo(Integer position) {
        Intent intent = new Intent(this, DetalleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(FragmentDetalleViewPager.CLAVE_POSITION, position);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void placeFragment(Fragment fragment){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contenedorFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void enviarinfo(CancionFavorita cancionFavorita) {
        
    }

}
