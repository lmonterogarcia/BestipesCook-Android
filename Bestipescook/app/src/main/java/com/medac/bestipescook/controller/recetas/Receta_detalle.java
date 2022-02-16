package com.medac.bestipescook.controller.recetas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.noticias.NoticiaStore;
import com.medac.bestipescook.logic.IHostingData;
import com.squareup.picasso.Picasso;


public class Receta_detalle extends Fragment {

    private View v;

    private ImageView imgUsuarioReceta;
    private TextView sTiempo;
    private TextView sComensales;
    private ImageView imgPralReceta;
    private TextView sCategoriaReceta;
    private TextView sTituloReceta;
    private TextView sDescripcionReceta;
    private TextView sIngredienteReceta;
    private TextView sFechaCreacionReceta;
    private Button btnEditar;

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
        v = inflater.inflate(R.layout.fragment_receta_detalle, container, false);

        imgUsuarioReceta = v.findViewById(R.id.ivUsuarioReceta);
        sTiempo = v.findViewById(R.id.lblTiempoReceta);
        sComensales = v.findViewById(R.id.lblComensalesReceta);
        imgPralReceta = v.findViewById(R.id.ivPralReceta);
        sCategoriaReceta = v.findViewById(R.id.lblCategoriaReceta);
        sTituloReceta = v.findViewById(R.id.lblTituloReceta);
        sDescripcionReceta = v.findViewById(R.id.lblDescripcionReceta);
        sIngredienteReceta = v.findViewById(R.id.lblIngredientesRecetas);
        sFechaCreacionReceta = v.findViewById(R.id.lblFechaCreacionReceta);
        btnEditar = v.findViewById(R.id.btnEditarReceta);

        sTiempo.setText(calcularTiempoStrin(RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getfDuracionReceta()));
        sComensales.setText(" - " + RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getShComensalesReceta() + " comensales");
        Picasso.get().load(IHostingData.sHosting + IHostingData.sRutaImagenes + RecetaStore.lstImagenes.get(RecetaStore.iRecetaSeleccionada).getsRutaUrlImagen()).into(imgPralReceta);
        // Estrellas y Me Gusta
        //sCategoriaReceta.setText(RecetaStore.lstCategorias.get(RecetaStore.iRecetaSeleccionada).getNombreCategoria());
        sTituloReceta.setText(RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getsTituloReceta());
        sDescripcionReceta.setText(RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getsTextoReceta());
        //Ingredientes
        //Pasos
        sFechaCreacionReceta.setText("Publicado el " + RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getFechaCreacionReceta().toString());

        return v;
    }

    private String calcularTiempoStrin(float fDuracionReceta) {
        String sTiempo;
        int iHoras = (int)fDuracionReceta;
        int iMinutos = (int)((fDuracionReceta - (int)fDuracionReceta) * 60);

        sTiempo = iHoras + "h " + iMinutos + "min";

        return sTiempo;
    }
}