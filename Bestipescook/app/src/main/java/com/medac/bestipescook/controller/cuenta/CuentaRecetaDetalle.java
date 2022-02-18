package com.medac.bestipescook.controller.cuenta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.cuenta.CuentaRecetaStore;
import com.medac.bestipescook.controller.recetas.RecetaStore;
import com.medac.bestipescook.logic.IHostingData;
import com.medac.bestipescook.logic.RecetaCrud;
import com.medac.bestipescook.model.IConstantes;
import com.medac.bestipescook.model.receta.IIngredienteReceta;
import com.squareup.picasso.Picasso;

public class CuentaRecetaDetalle extends Fragment implements IConstantes, IIngredienteReceta {

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

    public CuentaRecetaDetalle() {
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

        Picasso.get().load(IHostingData.sHosting + IHostingData.sRutaImagenes + CuentaRecetaStore.lstRecetas.get(CuentaRecetaStore.iRecetaSeleccionada).getUsuario().getImagen().getsRutaUrlImagen()).into(imgUsuarioReceta);
        sNombreUsuario.setText(CuentaRecetaStore.lstRecetas.get(CuentaRecetaStore.iRecetaSeleccionada).getUsuario().getsNombreUsuraio());
        sTiempo.setText(calcularTiempoString(CuentaRecetaStore.lstRecetas.get(CuentaRecetaStore.iRecetaSeleccionada).getfDuracionReceta()));
        sComensales.setText(" - " + CuentaRecetaStore.lstRecetas.get(CuentaRecetaStore.iRecetaSeleccionada).getShComensalesReceta() + " comensales");
        Picasso.get().load(IHostingData.sHosting + IHostingData.sRutaImagenes + CuentaRecetaStore.lstImagenes.get(CuentaRecetaStore.iRecetaSeleccionada).getsRutaUrlImagen()).into(imgPralReceta);
        // Estrellas y Me Gusta
        sCategoriaReceta.setText("Categoria: Comida " + CuentaRecetaStore.lstRecetas.get(CuentaRecetaStore.iRecetaSeleccionada).getCategoria().getNombreCategoria());
        sTituloReceta.setText(CuentaRecetaStore.lstRecetas.get(CuentaRecetaStore.iRecetaSeleccionada).getsTituloReceta());
        sDescripcionReceta.setText(CuentaRecetaStore.lstRecetas.get(CuentaRecetaStore.iRecetaSeleccionada).getsTextoReceta());
        RecetaCrud.getIngredientes(getContext(), CuentaRecetaStore.lstRecetas.get(CuentaRecetaStore.iRecetaSeleccionada).getiIdReceta(), () -> cargarIngredientes());
        CuentaRecetaStore.lstIngredientes = RecetaStore.lstIngredientes;
        //Pasos
        sFechaCreacionReceta.setText("Publicado el " + (CuentaRecetaStore.lstRecetas.get(CuentaRecetaStore.iRecetaSeleccionada).getFechaCreacionReceta()).format(dateformatter));

        return v;
    }

    private void cargarIngredientes() {
        String sIngredientes = "Ingredientes:";

        for (int i = 0; i < CuentaRecetaStore.lstIngredientes.size(); i++) {
            sIngredientes += "\n" + CuentaRecetaStore.lstIngredientes.get(i).getoIngrediente().getsNombreIngrediente() + " - " + CuentaRecetaStore.lstIngredientes.get(i).getiCantidadIngrediente() + AMEDIDASDIMINUTIVO[CuentaRecetaStore.lstIngredientes.get(i).getiMedida()];
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
