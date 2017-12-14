package com.example.yanina.mysong.View;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yanina.mysong.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private CallbackManager callbackManager;
    private FirebaseAuth mAuth;

    private View view;

    public LoginFragment() {
        // Required empty public constructor    private NavigationView navigationView;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_login, container, false);

//        Button botonIngresar = (Button) view.findViewById(R.id.ButtonIngresar);
//        botonIngresar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Boton Ingresar (En construccion)", Toast.LENGTH_SHORT).show();
//                contrasenia();
//            }
//        });
//
//        Button botonRegistrarse = (Button) view.findViewById(R.id.ButtonRegistrarse);
//        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Boton Registrarse (En construccion)", Toast.LENGTH_SHORT).show();
//            }
//        });

        mAuth = FirebaseAuth.getInstance();

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                Toast.makeText(getContext(), "onSuccess", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {

                Toast.makeText(getContext(), "onCancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getContext(), "onError", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

//    public void contrasenia (){
//
//        TextInputLayout editTextContrasenia;
//        editTextContrasenia = (TextInputLayout) view.findViewById(R.id.TextImputContrasenia);
//
//        EditText editText = (EditText) view.findViewById(R.id.EditTextContrasenia);
//
//        String contrasenia = editText.getText().toString();
//
//        if (contrasenia.length()<8){
//            editTextContrasenia.setError("La contraseña debe contener como minimo 8 caracteres");
//        }else {
//            editTextContrasenia.setError(null);
//        }
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Te logiaste", Toast.LENGTH_SHORT).show();
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Lisstener lisstener = (Lisstener) getActivity();
                            lisstener.updateLogin(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    public interface Lisstener {
        void updateLogin (FirebaseUser user);
    }

}
