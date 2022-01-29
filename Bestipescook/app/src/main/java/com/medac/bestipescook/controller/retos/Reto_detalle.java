package com.medac.bestipescook.controller.retos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.retos.RetoStore;
import com.medac.bestipescook.logic.IHostingData;
import com.medac.bestipescook.model.IConstantes;
import com.squareup.picasso.Picasso;

public class Reto_detalle extends Fragment implements IConstantes {

    private View v;

    private TextView lblTitulo;
    private TextView lblSubTitulo;
    private TextView lblText;
    private TextView lblFecha;
    private ImageView imagen;

    public Reto_detalle() {
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
        v = inflater.inflate(R.layout.fragment_reto_detalle, container, false);

        lblTitulo = v.findViewById(R.id.lblRetoDetalleTitulo);
        lblSubTitulo = v.findViewById(R.id.lblRetoDetalleSubtitulo);
        lblText = v.findViewById(R.id.lblRetoDetalleTexto);
        lblFecha = v.findViewById(R.id.lblRetoDetalleFechaFinalizacion);
        imagen = v.findViewById(R.id.ivRetoDetalle);

        lblTitulo.setText(RetoStore.lstRetos.get(RetoStore.iRetoSeleccionada).getsTituloReto());
        lblSubTitulo.setText(RetoStore.lstRetos.get(RetoStore.iRetoSeleccionada).getsSubtituloReto());
        lblText.setText(RetoStore.lstRetos.get(RetoStore.iRetoSeleccionada).getsTextoReto());
        lblFecha.setText(v.getContext().getString(R.string.texto_fecha_reto_detalle) + " " + RetoStore.lstRetos.get(RetoStore.iRetoSeleccionada).getFechaFinalizacionReto().format(dateformatter));
        Picasso.get().load(IHostingData.sHosting + IHostingData.sRutaImagenes +RetoStore.lstRetos.get(RetoStore.iRetoSeleccionada).getImagen().getsRutaUrlImagen()).into(imagen);

        return v;
    }
}