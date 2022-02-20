package com.medac.bestipescook.controller.recetas;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.cuenta.frLogIn;
import com.medac.bestipescook.logic.CuentaCrud;
import com.medac.bestipescook.logic.IHostingData;
import com.medac.bestipescook.logic.RecetaCrud;
import com.medac.bestipescook.logic.VolleyCallBack;
import com.medac.bestipescook.model.IConstantes;
import com.medac.bestipescook.model.receta.IIngredienteReceta;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;


public class Receta_detalle extends Fragment implements IConstantes, IIngredienteReceta {

    private View v;
    public static RecetaPasosAdapter adaptador;

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
    private RatingBar rbRecetaDetalle;
    private ToggleButton btnMeGusta;

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
        rbRecetaDetalle = v.findViewById(R.id.rbRecetaDetalle);
        btnMeGusta = v.findViewById(R.id.btnLikesRecetaDetalle);

        Picasso.get().load(IHostingData.sHosting + IHostingData.sRutaImagenes + RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getUsuario().getImagen().getsRutaUrlImagen()).into(imgUsuarioReceta);
        sNombreUsuario.setText(RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getUsuario().getsNombreUsuraio());
        sTiempo.setText(calcularTiempoString(RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getfDuracionReceta()));
        sComensales.setText(" - " + RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getShComensalesReceta() + " comensales");
        Picasso.get().load(IHostingData.sHosting + IHostingData.sRutaImagenes + RecetaStore.lstImagenes.get(RecetaStore.iRecetaSeleccionada).getsRutaUrlImagen()).into(imgPralReceta);
        rbRecetaDetalle.setRating(RecetaStore.lstPuntuacion.get(RecetaStore.iRecetaSeleccionada));

        if(CuentaCrud.preferencias == null) {
            rbRecetaDetalle.setIsIndicator(true);
            btnMeGusta.setVisibility(View.GONE);
        }else{
            rbRecetaDetalle.setIsIndicator(false);
            RecetaCrud.usuarioGustaReceta(getContext(), CuentaCrud.preferencias.getString("usuario",""),RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getiIdReceta(), () -> btnMeGusta.setChecked(RecetaStore.booMeGusta));
        }

        sCategoriaReceta.setText("Comida " + RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getCategoria().getNombreCategoria());
        sTituloReceta.setText(RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getsTituloReceta());
        sDescripcionReceta.setText(RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getsTextoReceta());
        RecetaCrud.getIngredientes(getContext(), RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getiIdReceta(), () -> cargarIngredientes());
        cargarPasos();
        sFechaCreacionReceta.setText("Publicado el " + (RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getFechaCreacionReceta()).format(dateformatter));

        rbRecetaDetalle.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            RecetaCrud.puntuarReceta(getContext(), rating, CuentaCrud.preferencias.getString("usuario",""),RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getiIdReceta(), () -> {
                Toast.makeText(getContext(), "Puntuación registrada",Toast.LENGTH_SHORT).show();
            });
        });

        btnMeGusta.setOnClickListener(b ->{
            if (btnMeGusta.isChecked()){
                RecetaCrud.insMegusta(getContext(), CuentaCrud.preferencias.getString("usuario",""),RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getiIdReceta(), () -> {
                    Toast.makeText(getContext(), "Se ha añadido a tu lista de favoritas",Toast.LENGTH_SHORT).show();
                });
            } else {
                RecetaCrud.delMegusta(getContext(), CuentaCrud.preferencias.getString("usuario",""),RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getiIdReceta(), () -> {
                    Toast.makeText(getContext(), "Se ha eliminado de tu lista de favoritas",Toast.LENGTH_SHORT).show();
                });
            }
        });

        return v;
    }

    private void cargarIngredientes() {
        String sIngredientes = "";

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

    private void cargarPasos()  {

        RecetaStore.lstPasos.clear();;
        RecetaCrud.getAllPasos(getContext(), RecetaStore.lstRecetas.get(RecetaStore.iRecetaSeleccionada).getiIdReceta());
        mostrarRecetas();
    }

    private void mostrarRecetas() {

        RecyclerView rvRecetasPasos = v.findViewById(R.id.rvRecetaPasos);

        rvRecetasPasos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adaptador = new RecetaPasosAdapter(getContext());
        rvRecetasPasos.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {

        });
    }

    // LISTENERS


}