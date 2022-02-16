package com.medac.bestipescook.controller.cuenta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.medac.bestipescook.R;
import com.medac.bestipescook.logic.CuentaCrud;

public class frCrearCuenta extends Fragment {
    private View v;

    public frCrearCuenta() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_crear_cuenta, container, false);
        v.findViewById(R.id.btnCrearCuenta).setOnClickListener(e ->{

            TextInputEditText textMail = v.findViewById(R.id.txtMail);
            TextInputEditText textUsuario = v.findViewById(R.id.txtUser);
            TextInputEditText textPass = v.findViewById(R.id.txtPass);

            CuentaCrud.insertUsuario(getContext(), textMail.getEditableText().toString(), textUsuario.getEditableText().toString(),textPass.getEditableText().toString(),() -> abrirFragmentEditarPerfil());
        });

        v.findViewById(R.id.btnLogIn).setOnClickListener(e ->{
            frLogIn nextFrag = new frLogIn();
            if (!nextFrag.isAdded()) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }

    private void abrirFragmentEditarPerfil() {
        frEditarPerfil nextFrag = new frEditarPerfil();
        if (!nextFrag.isAdded()) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, nextFrag, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }
    }
}