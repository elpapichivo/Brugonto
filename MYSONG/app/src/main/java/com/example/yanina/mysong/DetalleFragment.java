package com.example.yanina.mysong;

        import android.content.Context;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;


public class DetalleFragment extends Fragment {

    public static final String CLAVE_IMAGEN="claveImagen";
    public static final String CLAVE_NOMBRE_CANCION="claveNombreCancion";
    public static final String CLAVE_NOMBRE_ARTISTA="claveNombreArtista";
    public static final String CLAVE_GENERO="claveGenero";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_detalle, container, false);


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




        // Inflate the layout for this fragment
        return view;
    }
}
