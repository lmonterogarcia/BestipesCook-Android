package com.medac.bestipescook.view.noticias;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.noticias.NoticiaStore;
import com.medac.bestipescook.logic.IHostingData;
import com.squareup.picasso.Picasso;

public class Noticia_detalle extends Fragment {

    private View v;

    private TextView lblTitulo;
    private TextView lblSubTitulo;
    private TextView lblText;
    private ImageView imagen;

    public Noticia_detalle() {
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

        lblTitulo = v.findViewById(R.id.lblNoticiaDetalleTitulo);
        lblSubTitulo = v.findViewById(R.id.lblNoticiaDetalleSubtitulo);
        lblText = v.findViewById(R.id.lblNoticiaDetalleTexto);
        imagen = v.findViewById(R.id.ivNoticiaDetalle);

        lblTitulo.setText(NoticiaStore.lstNoticias.get(NoticiaStore.iNoticiaSeleccionada).getTituloNoticia());
        lblSubTitulo.setText(NoticiaStore.lstNoticias.get(NoticiaStore.iNoticiaSeleccionada).getSubtituloNoticia());
        lblText.setText(NoticiaStore.lstNoticias.get(NoticiaStore.iNoticiaSeleccionada).getTextoNoticia());
        Picasso.get().load(IHostingData.sHosting + IHostingData.sRutaImagenes +NoticiaStore.lstNoticias.get(NoticiaStore.iNoticiaSeleccionada).getImagen().getsRutaUrlImagen()).into(imagen);

        return v;
    }
}