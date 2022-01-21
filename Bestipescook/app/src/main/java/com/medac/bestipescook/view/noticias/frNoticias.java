package com.medac.bestipescook.view.noticias;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.noticias.NoticiaAdapter;
import com.medac.bestipescook.controller.noticias.NoticiaStore;
import com.medac.bestipescook.logic.NoticiasCrud;
import com.medac.bestipescook.model.Imagen;
import com.medac.bestipescook.model.noticia.Noticia;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
        //NoticiasCrud.getAllNoticias(getContext()); // ESTO ES UNA PRUEBA.
        String str = "2016-03-04 11:30"; DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        NoticiaStore.lstNoticias.add(new Noticia(1,dateTime, "a","a","a", new Imagen(1,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
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