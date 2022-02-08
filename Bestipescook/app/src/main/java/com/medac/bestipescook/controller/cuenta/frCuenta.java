package com.medac.bestipescook.controller.cuenta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.noticias.Noticia_detalle;
import com.medac.bestipescook.logic.CuentaCrud;

public class frCuenta extends Fragment {
    private View v;
    public frCuenta() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_cuenta, container, false);
        if(CuentaCrud.preferencias == null) {
            frLogIn nextFrag = new frLogIn();
            if (!nextFrag.isAdded()) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        }
        return v;
    }
}