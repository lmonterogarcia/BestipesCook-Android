package com.medac.bestipescook.view.noticias;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.noticias.NoticiaAdapter;
import com.medac.bestipescook.controller.noticias.NoticiaStore;
import com.medac.bestipescook.logic.NoticiasCrud;
import com.medac.bestipescook.model.Imagen;
import com.medac.bestipescook.model.noticia.Noticia;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;


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

        cargarNoticias();
        mostarNoticias();

        return v;
    }

    private void cargarNoticias() {
        NoticiaStore.lstNoticias.clear();
        NoticiasCrud.getAllNoticias(getContext()); // ESTO ES UNA PRUEBA.


        DateTimeFormatter dateTimeformatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .optionalStart()
                .appendPattern(".")
                .appendFraction(ChronoField.MICRO_OF_SECOND, 1, 6, false)
                .optionalEnd()
                .toFormatter(); LocalDateTime dateTime = LocalDateTime.parse("2021-12-12 09:25:20", dateTimeformatter);

        NoticiaStore.lstNoticias.add(new Noticia(1,dateTime, "a","a","a", new Imagen(1,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "b","b","b", new Imagen(2,dateTime,"fotovacia.png")));
        NoticiaStore.lstNoticias.add(new Noticia(2,dateTime, "z","z","z", new Imagen(2,dateTime,"fotovacia.png")));

    }

    private void mostarNoticias() {

        RecyclerView rvNoticias = v.findViewById(R.id.rvNoticias);

        rvNoticias.setLayoutManager(new LinearLayoutManager(getContext()));
        NoticiaAdapter adaptador = new NoticiaAdapter(getContext());
        rvNoticias.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {
            NoticiaStore.iNoticiaSeleccionada = rvNoticias.getChildAdapterPosition(v);
            Toast.makeText(getContext(), "" + NoticiaStore.iNoticiaSeleccionada, Toast.LENGTH_LONG).show();
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