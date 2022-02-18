package com.medac.bestipescook.controller.cuenta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.noticias.Noticia_detalle;
import com.medac.bestipescook.controller.ranking.RankingStore;
import com.medac.bestipescook.logic.CuentaCrud;
import com.medac.bestipescook.logic.RankingCrud;

public class frCuenta extends Fragment {
    private View v;
    private Spinner spinerCategoria;
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

        spinerCategoria = v.findViewById(R.id.spinner);
        RankingCrud.getAllCategorias(getContext(),() -> cargarCategorias());

        if(CuentaCrud.preferencias == null) {
            frLogIn nextFrag = new frLogIn();
            if (!nextFrag.isAdded()) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        }
        v.findViewById(R.id.btnEdit).setOnClickListener(e ->{
            frEditarPerfil nextFrag = new frEditarPerfil();
            if (!nextFrag.isAdded()) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }

    private void cargarCategorias()  {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, RankingStore.lstCategorias);
        spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        spinerCategoria.setAdapter(spinnerArrayAdapter);
    }
}