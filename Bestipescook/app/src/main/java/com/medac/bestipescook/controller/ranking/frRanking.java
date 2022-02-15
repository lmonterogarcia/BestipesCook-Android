package com.medac.bestipescook.controller.ranking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
import com.medac.bestipescook.controller.recetas.RecetaStore;
import com.medac.bestipescook.controller.recetas.Receta_detalle;
import com.medac.bestipescook.logic.RankingCrud;

import java.util.ArrayList;


public class frRanking extends Fragment {

    private View v;
    public static RankingAdapter adaptador;
    private Spinner spinerCategoriaLugar;
    public static String query;
    public static ArrayList<String> list = RecetaStore.lstNombreCategoria;

    public frRanking() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_ranking, container, false);


        cargarRecetas();

        spinerCategoriaLugar = v.findViewById(R.id.spCategoria);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                list
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerCategoriaLugar.setAdapter(adapter);

        spinerCategoriaLugar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String categoria = (String) spinerCategoriaLugar.getSelectedItem();
                Log.d("Pruebas", categoria);
                spinerCategoriaLugar.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // No seleccionaron nada
            }
        });

        return v;
    }

    private void cargarRecetas()  {

        RecetaStore.lstRecetas.clear();
        RecetaStore.lstImagenes.clear();
        RecetaStore.lstPuntuacion.clear();
        RankingCrud.getAllRecetas(getContext());
        RankingCrud.getAllCategorias(getContext());
        mostrarRecetas();
    }

    private void mostrarRecetas() {

        RecyclerView rvRanking = v.findViewById(R.id.rvRanking);

        rvRanking.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptador = new RankingAdapter(getContext());
        rvRanking.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {
            RecetaStore.iRecetaSeleccionada = rvRanking.getChildAdapterPosition(v);
            Receta_detalle nextFrag= new Receta_detalle();
            if (!nextFrag.isAdded()){
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}