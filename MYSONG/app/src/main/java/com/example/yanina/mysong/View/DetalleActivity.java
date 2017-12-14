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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yanina.mysong.Model.Album;
import com.example.yanina.mysong.R;

import com.example.yanina.mysong.View.Adaptadores.Adaptador;
import com.example.yanina.mysong.View.Adaptadores.AdaptadorCancion;
import com.example.yanina.mysong.View.Adaptadores.AdaptadorDeFavoritos;
import com.example.yanina.mysong.View.Adaptadores.AdaptadorAlbum;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class DetalleActivity extends AppCompatActivity implements AdaptadorAlbum.ComunicadorAlbumes, Adaptador.Comunicador, AdaptadorDeFavoritos.ComunicadorFavoritos, AdaptadorCancion.ComunicadorCancion{

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.miToolbar);
        setSupportActionBar(toolbar);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.artistasAppbar:
                placeFragment(new FragmentLista());
                return true;
            case R.id.favoritosAppbar:
                placeFragment(new FragmentFavoritos());
                return true;
            case R.id.loginAppbar:
                placeFragment(new LoginFragment());
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void itemClickNavigation(MenuItem item){
        //Comparo segun el id del item
        switch (item.getItemId()){
            case R.id.iconoArtista:
                //Le paso al metodo que pone los fragment un nuevo fragment
                placeFragment(new FragmentLista());
                break;
            case R.id.buscar:
                placeFragment(new InicioFragment());
                break;
            case R.id.favoritos:
                placeFragment(new FragmentFavoritos());
                break;
            case R.id.logout:
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();
                Menu menuNav = navigationView.getMenu();
                MenuItem login = menuNav.findItem(R.id.login);
                MenuItem logout = menuNav.findItem(R.id.logout);
                login.setVisible(true);
                logout.setVisible(false);
                break;
            case R.id.login:
                placeFragment(new LoginFragment());
            default:
                break;
        }
    }

    private void placeFragment(Fragment fragment){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.activity2Lay, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void enviarInfo(Integer position) {
        Intent intent = new Intent(this, DetalleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(FragmentDetalleViewPager.CLAVE_POSITION, position);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void enviarInfo(Integer position, String busqueda) {
        Intent intent = new Intent(this, DetalleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(FragmentDetalleViewPager.CLAVE_POSITION, position);
        bundle.putString(FragmentDetalleViewPager.CLAVE_BUSQUEDA, busqueda);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void enviarinfo(Integer position) {
        Bundle bundle = new Bundle();
        bundle.putInt(FragmentContenedorReproduccion.CLAVE_POSITION2,position);
        FragmentContenedorReproduccion fragmentContenedorReproduccion = new FragmentContenedorReproduccion();
        fragmentContenedorReproduccion.setArguments(bundle);
        placeFragment(fragmentContenedorReproduccion);
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

    @Override
    public void enviarInformacionCancion(Integer claveAlbum, Integer posicion) {
         Bundle bundle = new Bundle();
        bundle.putInt(FragmentContenedorReproduccion.CLAVE_POSITION2,posicion);
        bundle.putInt(FragmentContenedorReproduccion.CLAVE_ALBUM,claveAlbum);
         FragmentContenedorReproduccion fragmentContenedorReproduccion = new FragmentContenedorReproduccion();
        fragmentContenedorReproduccion.setArguments(bundle);
         placeFragment(fragmentContenedorReproduccion);
        //Toast.makeText(this, posicion.toString(), Toast.LENGTH_SHORT).show();
    }
}