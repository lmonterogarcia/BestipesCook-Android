package com.medac.bestipescook.view.noticias;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.noticias.NoticiaAdapter;
import com.medac.bestipescook.controller.noticias.NoticiaStore;
import com.medac.bestipescook.logic.NoticiasCrud;
import com.medac.bestipescook.model.noticia.Noticia;


public class frNoticias extends Fragment {

    private View v;
    public frNoticias() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_noticias, container, false);

        NoticiaStore.lstNoticias.clear();
        cargarNoticias();
        mostarNoticias();

        return v;
    }

    private void cargarNoticias() {
        NoticiasCrud.getAllCoche(getContext()); // ESTO ES UNA PRUEBA.
    }

    private void mostarNoticias() {

        RecyclerView rvNoticias = v.findViewById(R.id.rvNoticias);

        rvNoticias.setLayoutManager(new LinearLayoutManager(getContext())); // ESTO ES UNA PRUEBA
        NoticiaAdapter adaptador = new NoticiaAdapter(getContext()); // ESTO ES UNA PRUEBA
        rvNoticias.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {
            NoticiaStore.iNoticiaSeleccionada = rvNoticias.getChildAdapterPosition(v);
            //Intent i = new Intent(this, Noticia_detalle.class);
            //startActivity(i);

        });
    }

    /*private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction =getActivity().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }*/
}