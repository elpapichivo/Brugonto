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

import com.example.yanina.mysong.Model.Cancion;
import com.example.yanina.mysong.R;
import com.example.yanina.mysong.View.Adaptadores.Adaptador;
import com.example.yanina.mysong.View.Adaptadores.AdaptadorDeFavoritos;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements Adaptador.Comunicador, AdaptadorDeFavoritos.ComunicadorFavoritos, LoginFragment.Lisstener{

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.miToolbar);
        setSupportActionBar(toolbar);

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
                case R.id.itemConfiguracion:
                    //Le paso al metodo que pone los fragment un nuevo fragment
                    Toast.makeText(this, "En construccion", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            Menu menuNav = navigationView.getMenu();
            MenuItem login = menuNav.findItem(R.id.login);
            MenuItem logout = menuNav.findItem(R.id.logout);
            login.setVisible(false);
            logout.setVisible(true);
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

    public void placeFragment(Fragment fragment){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contenedorFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
    public void updateLogin(FirebaseUser user) {
        placeFragment(new InicioFragment());

        Menu menuNav = navigationView.getMenu();
        MenuItem login = menuNav.findItem(R.id.login);
        MenuItem logout = menuNav.findItem(R.id.logout);

        if (user != null){

            login.setVisible(false);
            logout.setVisible(true);

        } else {
            login.setVisible(true);
            logout.setVisible(false);
        }
    }
}