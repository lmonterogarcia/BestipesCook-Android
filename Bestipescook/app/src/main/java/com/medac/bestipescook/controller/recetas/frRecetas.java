package com.medac.bestipescook.controller.recetas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.medac.bestipescook.R;

import com.medac.bestipescook.logic.RecetaCrud;


public class frRecetas extends Fragment {

    private View v;
    public static RecetaAdapter adaptador;
    public static String query;

    public frRecetas() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_recetas, container, false);
        cargarRecetas();

        v.findViewById(R.id.btnBuscar).setOnClickListener(e -> {
            EditText txtquery = v.findViewById(R.id.txtquery);
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            if (!String.valueOf(txtquery.getText()).equals("")){
                query = null;
                query = String.valueOf(txtquery.getText());
                Log.d("Pruebas", query);
                cargarRecetasSearch();
            }else{
                Log.d("Pruebas", "No hay datos");
            }

        });
        return v;
    }

    private void cargarRecetas()  {

        RecetaStore.lstRecetas.clear();
        RecetaStore.lstImagenes.clear();
        RecetaStore.lstPuntuacion.clear();
        RecetaCrud.getAllRecetas(getContext());
        mostrarRecetas();
    }

    private void cargarRecetasSearch()  {

        RecetaStore.lstRecetas.clear();
        RecetaStore.lstImagenes.clear();
        RecetaStore.lstPuntuacion.clear();
        RecetaCrud.getAllRecetasSearch(getContext());
        mostrarRecetas();
    }

    private void mostrarRecetas() {

        RecyclerView rvRecetas = v.findViewById(R.id.rvRecetas);

        rvRecetas.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptador = new RecetaAdapter(getContext());
        rvRecetas.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {
            RecetaStore.iRecetaSeleccionada = rvRecetas.getChildAdapterPosition(v);
            Receta_detalle nextFrag= new Receta_detalle();
            if (!nextFrag.isAdded()){
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, nextFrag, "receta")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }



}