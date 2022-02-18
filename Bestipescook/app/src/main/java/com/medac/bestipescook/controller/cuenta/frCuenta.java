package com.medac.bestipescook.controller.cuenta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.medac.bestipescook.R;
import com.medac.bestipescook.controller.noticias.Noticia_detalle;
import com.medac.bestipescook.controller.ranking.RankingStore;
import com.medac.bestipescook.controller.recetas.RecetaAdapter;
import com.medac.bestipescook.controller.recetas.RecetaStore;
import com.medac.bestipescook.controller.recetas.Receta_detalle;
import com.medac.bestipescook.logic.CuentaCrud;
import com.medac.bestipescook.logic.RankingCrud;
import com.medac.bestipescook.logic.RecetaCrud;
import com.medac.bestipescook.model.Imagen;
import com.medac.bestipescook.model.receta.Receta;
import com.medac.bestipescook.model.usuario.UsuarioRecetaEstrella;

import java.util.ArrayList;

public class frCuenta extends Fragment {
    private View v;
    //private Spinner spinerCategoria;
    public static CuentaAdapter adaptador;
    boolean toogle = false;
    public frCuenta() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_cuenta, container, false);
        //spinerCategoria = v.findViewById(R.id.spinner);

        if(CuentaCrud.preferencias == null) {
            frLogIn nextFrag = new frLogIn();
            if (!nextFrag.isAdded()) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        }else{
            //RankingCrud.getAllCategorias(getContext(),() -> cargarCategorias());
        }

        v.findViewById(R.id.btnEdit).setOnClickListener(e ->{
            frEditarPerfil nextFrag = new frEditarPerfil();
            if (!nextFrag.isAdded()) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
/*
        spinerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                cargarRecetas(selectedItem);
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

 */

        v.findViewById(R.id.btnMyReceta).setOnClickListener(e -> {
            cargarRecetas();
        });

        v.findViewById(R.id.btnFavorita).setOnClickListener(e -> {
            cargarRecetasFavoritas();
        });

        return v;
    }

    private void cargarRecetasFavoritas() {
        if(toogle) {
            toogle=false;
        }else{
            toogle = true;
        }
    }
/*
    private void cargarCategorias()  {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, RankingStore.lstCategorias);
        spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        spinerCategoria.setAdapter(spinnerArrayAdapter);
    }

 */

    private void cargarRecetas()  {
        if(!toogle) {
            CuentaRecetaStore.lstRecetas.clear();
            CuentaRecetaStore.lstImagenes.clear();
            CuentaRecetaStore.lstPuntuacion.clear();
            CuentaCrud.getAllRecetas(getContext());
            mostrarRecetas();
            toogle=true;
            Log.d("prueba8",CuentaRecetaStore.lstRecetas.toString());
        }else{
            CuentaRecetaStore.lstRecetas.clear();
            CuentaRecetaStore.lstImagenes.clear();
            CuentaRecetaStore.lstPuntuacion.clear();
            mostrarRecetas();
            toogle = false;
        }
    }

    private void mostrarRecetas() {
        RecyclerView rvRecetas = v.findViewById(R.id.rvCuenta);

        rvRecetas.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptador = new CuentaAdapter(getContext());
        rvRecetas.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {
            CuentaRecetaStore.iRecetaSeleccionada = rvRecetas.getChildAdapterPosition(v);
            Receta_detalle nextFrag= new Receta_detalle();
            if (!nextFrag.isAdded()){
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void cargarRecetas(String sCategoria)  {
        if(toogle) {
            CuentaRecetaStore.lstRecetas.clear();
            CuentaRecetaStore.lstImagenes.clear();
            CuentaRecetaStore.lstPuntuacion.clear();
            CuentaCrud.getAllRecetas(getContext());
            filtrarCategoria(sCategoria);
            mostrarRecetas();
        }
    }

    private void filtrarCategoria(String sCategoria) {
        ArrayList<Receta> lstRecetas = new ArrayList<Receta>();
        ArrayList<UsuarioRecetaEstrella> lstPuntuacion = new ArrayList<UsuarioRecetaEstrella>();
        ArrayList<Imagen> lstImagenes = new ArrayList<Imagen>();

        CuentaRecetaStore.lstRecetas.forEach(receta -> {
            if(receta.getUsuario().getsNombreUsuraio().equals(CuentaCrud.preferencias.getString("usuario",""))){
                lstRecetas.add(receta);
                //QUEDA RELLENAR IMAGENES Y PUNTUACION
            }
        });
    }
}