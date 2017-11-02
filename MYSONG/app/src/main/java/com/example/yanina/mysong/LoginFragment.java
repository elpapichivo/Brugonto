package com.example.yanina.mysong;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private View view;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_login, container, false);

        Button botonIngresar = (Button) view.findViewById(R.id.ButtonIngresar);
        botonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Boton Ingresar (En construccion)", Toast.LENGTH_SHORT).show();
                contrasenia();
            }
        });

        Button botonRegistrarse = (Button) view.findViewById(R.id.ButtonRegistrarse);
        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Boton Registrarse (En construccion)", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void contrasenia (){

        TextInputLayout editTextContrasenia;
        editTextContrasenia = (TextInputLayout) view.findViewById(R.id.TextImputContrasenia);

        EditText editText = (EditText) view.findViewById(R.id.EditTextContrasenia);

        String contrasenia = editText.getText().toString();

        if (contrasenia.length()<8){
            editTextContrasenia.setError("La contraseña debe contener como minimo 8 caracteres");
        }else {
            editTextContrasenia.setError(null);
        }
    }


}
