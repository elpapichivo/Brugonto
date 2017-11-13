package com.example.yanina.mysong.View;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.yanina.mysong.Model.Artista;
        import com.example.yanina.mysong.R;

        import java.util.List;


public class DetalleFragment extends Fragment {
    private List<Artista>listaDeArtistas;

    public static final String CLAVE_IMAGEN="claveImagen";
    public static final String CLAVE_NOMBRE_CANCION="claveNombreCancion";
    public static final String CLAVE_NOMBRE_ARTISTA="claveNombreArtista";
    public static final String CLAVE_GENERO="claveGenero";
    public static DetalleFragment factory(Integer imagen, String artista, Integer cancion, Integer id){
        DetalleFragment detalleFragment=new DetalleFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(CLAVE_IMAGEN,imagen);
        bundle.putString(CLAVE_NOMBRE_ARTISTA,artista);
        bundle.putInt(CLAVE_NOMBRE_CANCION,cancion);
        bundle.putInt(CLAVE_GENERO,id);
        detalleFragment.setArguments(bundle);
        return detalleFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_detalle_view_pager, container, false);





        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textViewNombre = (TextView) view.findViewById(R.id.textViewNombre);
        TextView textViewArtista= (TextView) view.findViewById(R.id.textViewArtista);
        TextView textViewAlbum= (TextView) view.findViewById(R.id.textViewAlbum);

        //Recibir bundle
        Bundle bundle=getArguments();

        imageView.setImageResource(bundle.getInt (CLAVE_IMAGEN));
        textViewNombre.setText(bundle.getString(CLAVE_NOMBRE_ARTISTA));
        textViewArtista.setText(bundle.getString(CLAVE_NOMBRE_CANCION));
        textViewAlbum.setText(bundle.getString(CLAVE_GENERO));

        Button buttonFavoritos=(Button) view.findViewById(R.id.botonFavoritos);
        buttonFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "En construccion(favoritos)", Toast.LENGTH_SHORT).show();
            }
        });







        // Inflate the layout for this fragment
        return view;
    }
}
