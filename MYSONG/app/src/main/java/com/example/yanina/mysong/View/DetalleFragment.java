package com.example.yanina.mysong.View;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.request.RequestOptions;
        import com.example.yanina.mysong.Controller.ControllerAlbum;
        import com.example.yanina.mysong.Model.Album;
        import com.example.yanina.mysong.Model.Artista;
        import com.example.yanina.mysong.Model.Cancion;
        import com.example.yanina.mysong.R;
        import com.example.yanina.mysong.Utils.ResultListener;
        import com.example.yanina.mysong.View.Adaptadores.AdaptadorAlbum;

        import java.util.List;


public class DetalleFragment extends Fragment {
    private List<Artista> listaDeArtistas;

    private Artista artista;
    public static final String CLAVE_IMAGEN = "claveImagen";
    public static final String CLAVE_NOMBRE_CANCION = "claveNombreCancion";
    public static final String CLAVE_NOMBRE_ARTISTA = "claveNombreArtista";
    public static final String CLAVE_ARISTA = "claveGenero";

    AdaptadorAlbum adaptadorAlbum;

    public static DetalleFragment factory(String imagen, String artista, Integer cancion, Integer id) {


            DetalleFragment detalleFragment = new DetalleFragment();
            Bundle bundle = new Bundle();
            bundle.putString(CLAVE_IMAGEN, imagen);
            bundle.putString(CLAVE_NOMBRE_ARTISTA, artista);
            bundle.putInt(CLAVE_NOMBRE_CANCION, cancion);
            bundle.putInt(CLAVE_ARISTA, id);
            detalleFragment.setArguments(bundle);
            return detalleFragment;
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_detalle_view_pager, container, false);

            RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.recyclerAlbum);

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            adaptadorAlbum= new AdaptadorAlbum(getContext());
            recyclerView.setAdapter(adaptadorAlbum);


            //ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            TextView textViewNombre = (TextView) view.findViewById(R.id.textViewNombre);
            //TextView textViewArtista = (TextView) view.findViewById(R.id.textViewArtista);
           // TextView textViewAlbum = (TextView) view.findViewById(R.id.textViewAlbum);

            //Recibir bundle
            Bundle bundle = getArguments();


            //imageView.setImageResource(bundle.getString(CLAVE_IMAGEN));
            textViewNombre.setText(bundle.getString(CLAVE_NOMBRE_ARTISTA));
            //textViewArtista.setText(bundle.getString(CLAVE_NOMBRE_CANCION));
            //textViewAlbum.setText(bundle.getString(CLAVE_ARISTA));

          RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.error);

            //Glide.with(getContext()).load(bundle.getString(CLAVE_IMAGEN)).apply(requestOptions).into(imageView);







            ControllerAlbum controllerAlbum= new ControllerAlbum();
            controllerAlbum.obtenerAlbumPorArtista(new ResultListener<List<Album>>() {
                @Override
                public void finish(List<Album> resultado) {
                    adaptadorAlbum.agregarAlbum(resultado);
                    adaptadorAlbum.notifyDataSetChanged();

                }
            }, bundle.getInt(CLAVE_ARISTA));




            // Inflate the layout for this fragment
            return view;
        }

    }
