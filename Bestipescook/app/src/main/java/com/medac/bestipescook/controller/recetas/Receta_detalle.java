package com.medac.bestipescook.controller.recetas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.noticias.NoticiaStore;

public class Receta_detalle extends Fragment {

    private View v;

    private TextView lblTitulo;
    private TextView lblText;
    private ImageView imagen;

    public Receta_detalle() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_noticia_detalle, container, false);

        lblTitulo = v.findViewById(R.id.lblRecetaTituloRank);
        lblText = v.findViewById(R.id.lblNoticiaDetalleTexto);
        //imagen = v.findViewById(R.id.ivNoticiaDetalle);

        lblTitulo.setText(NoticiaStore.lstNoticias.get(NoticiaStore.iNoticiaSeleccionada).getsTituloNoticia());
        lblText.setText(NoticiaStore.lstNoticias.get(NoticiaStore.iNoticiaSeleccionada).getsTextoNoticia());
        //Picasso.get().load(IHostingData.sHosting + IHostingData.sRutaImagenes +NoticiaStore.lstNoticias.get(NoticiaStore.iNoticiaSeleccionada).getImagen().getsRutaUrlImagen()).into(imagen);

        return v;
    }
}