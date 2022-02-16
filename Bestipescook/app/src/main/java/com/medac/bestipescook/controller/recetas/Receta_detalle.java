package com.medac.bestipescook.controller.recetas;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.medac.bestipescook.R;
import com.medac.bestipescook.logic.IHostingData;
import com.medac.bestipescook.logic.RecetaCrud;
import com.medac.bestipescook.logic.VolleyCallBack;
import com.medac.bestipescook.model.IConstantes;
import com.medac.bestipescook.model.receta.IIngredienteReceta;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;


public class Receta_detalle extends Fragment implements IConstantes, IIngredienteReceta {

    private View v;

    private ImageView imgUsuarioReceta;
    private TextView sNombreUsuario;
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
        sNombreUsuario = v.findViewById(R.id.lblNombreUsuarioReceta);
        sTiempo = v.findViewById(R.id.lblTiempoReceta);
        sComensales = v.findViewById(R.id.lblComensalesReceta);
        imgPralReceta = v.findViewById(R.id.ivPralReceta);
        sCategoriaReceta = v.findViewById(R.id.lblCategoriaReceta);
        sTituloReceta = v.findViewById(R.id.lblTituloReceta);
        sDescripcionReceta = v.findViewById(R.id.lblDescripcionReceta);
        sIngredienteReceta = v.findViewById(R.id.lblIngredientesRecetas);
        sFechaCreacionReceta = v.findViewById(R.id.lblFechaCreacionReceta);
        btnEditar = v.findViewById(R.id.btnEditarReceta);

        Picasso.get().load(IHostingData.sHosting + IHostingData.sRutaImagenes + RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getUsuario().getImagen().getsRutaUrlImagen()).into(imgUsuarioReceta);
        sNombreUsuario.setText(RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getUsuario().getsNombreUsuraio());
        sTiempo.setText(calcularTiempoString(RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getfDuracionReceta()));
        sComensales.setText(" - " + RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getShComensalesReceta() + " comensales");
        Picasso.get().load(IHostingData.sHosting + IHostingData.sRutaImagenes + RecetaStore.lstImagenes.get(RecetaStore.iRecetaSeleccionada).getsRutaUrlImagen()).into(imgPralReceta);
        // Estrellas y Me Gusta
        sCategoriaReceta.setText("Categoria: Comida " + RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getCategoria().getNombreCategoria());
        sTituloReceta.setText(RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getsTituloReceta());
        sDescripcionReceta.setText(RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getsTextoReceta());
        RecetaCrud.getIngredientes(getContext(), RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getiIdReceta(), () -> cargarIngredientes());
        //Pasos
        sFechaCreacionReceta.setText("Publicado el " + (RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getFechaCreacionReceta()).format(dateformatter));

        return v;
    }

    private void cargarIngredientes() {
        String sIngredientes = "Ingredientes:";

        for (int i = 0; i < RecetaStore.lstIngredientes.size(); i++) {
            sIngredientes += "\n" + RecetaStore.lstIngredientes.get(i).getoIngrediente().getsNombreIngrediente() + " - " + RecetaStore.lstIngredientes.get(i).getiCantidadIngrediente() + AMEDIDASDIMINUTIVO[RecetaStore.lstIngredientes.get(i).getiMedida()];
        }
        sIngredienteReceta.setText(sIngredientes);
    }

    private String calcularTiempoString(float fDuracionReceta) {
        String sTiempo;
        int iHoras = (int)fDuracionReceta;
        int iMinutos = (int)((fDuracionReceta - (int)fDuracionReceta) * 60);

        sTiempo = iHoras + "h " + iMinutos + "min";

        return sTiempo;
    }
}